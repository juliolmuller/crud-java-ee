package br.com.beibe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.TicketMessage;
import br.com.beibe.beans.User;
import br.com.beibe.beans.ValError;
import br.com.beibe.facade.ProductFacade;
import br.com.beibe.facade.TicketFacade;

@WebServlet(name = "TicketsAPI", urlPatterns = {"/api/tickets"})
public class ApiTicketsServlet extends HttpServlet {

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Ticket ticket;
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ticket = TicketFacade.find(id);
        } catch (NumberFormatException ex) {
            ticket = null;
        }
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            List<ValError> errors;
            switch (action) {
                case "message":
                    TicketMessage message = extractMessageData(request);
                    errors = message.validate();
                    if (errors.isEmpty()) {
                        TicketFacade.save(message);
                        out.print(json.toJson(message));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
                case "new":
                case "update":
                    ticket = extractTicketData(request);
                    errors = ticket.validate();
                    if (errors.isEmpty()) {
                        TicketFacade.save(ticket);
                        out.print(json.toJson(ticket));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
            }
        }
        response.sendError(404);
    }
    
    private TicketMessage extractMessageData(HttpServletRequest request) {
        TicketMessage message = new TicketMessage();
        message.setMessage(request.getParameter("message"));
        message.setSendDate(LocalDateTime.now());
        message.setSender((User) request.getSession().getAttribute("userCredentials"));
        message.setTicket(Long.parseLong(request.getParameter("id")));
        return message;
    }

    private Ticket extractTicketData(HttpServletRequest request) {
        Ticket ticket = new Ticket();
        ticket.setType(TicketFacade.findType(Integer.parseInt(request.getParameter("type"))));
        ticket.setStatus(TicketFacade.findStatus(Integer.parseInt(request.getParameter("status"))));
        ticket.setProduct(ProductFacade.find(Long.parseLong(request.getParameter("product"))));
        ticket.setOpeningDate(LocalDateTime.now());
        ticket.setOpenBy((User) request.getSession().getAttribute("userCredentials"));
        try {
            ticket.setId(Long.parseLong(request.getParameter("id")));
        } catch (NumberFormatException | NullPointerException ex) {
            ticket.setId(null);
        }
        Set<TicketMessage> messages = new TreeSet<>();
        messages.add(extractMessageData(request));
        ticket.setMessages(messages);
        return ticket;
    }
}
