package br.com.beibe.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.beans.FeedItem;
import br.com.beibe.beans.Hyperlink;
import br.com.beibe.beans.Product;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.TicketMessage;
import br.com.beibe.beans.TicketStatus;
import br.com.beibe.beans.TicketType;
import br.com.beibe.beans.User;
import br.com.beibe.dao.ProductDAO;
import br.com.beibe.dao.TicketTypeDAO;
import br.com.beibe.facade.TicketFacade;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente/*"})
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String baseUri = request.getContextPath() + "/cliente";
        request.setAttribute("baseUri", baseUri);
        String uri = request.getRequestURI().substring(baseUri.length());
        switch (uri) {
            case "":
            case "/":
                setHeaderLinks(request, 0);
                displayHome(request, response);
                return;
            case "/atendimentos":
                setHeaderLinks(request, 1);
                displayTickets(request, response);
                return;
            case "/atendimentos/novo":
                setHeaderLinks(request, 1);
                displayNewTicketForm(request, response);
                return;
            case "/atendimentos/acompanhar":
                setHeaderLinks(request, 1);
                displayExistingTicketForm(request, response);
                return;
        }
        response.sendError(404);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String baseUri = request.getContextPath() + "/cliente";
        String uri = request.getRequestURI().substring(baseUri.length());
        switch (uri) {
            case "/atendimentos/novo":
                processNewTicket(request, response);
                return;
        }
        response.sendError(404);
    }

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Meus Atendimentos", "/atendimentos"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

    public void displayHome(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        List<FeedItem> feed = new ArrayList<>();
        Integer ticketsCount = TicketFacade.listMyOpen((User) request.getSession().getAttribute("userCredentials")).size();
        String ticketsCountStr = ticketsCount.toString();
        feed.add(new FeedItem(
            null,
            ticketsCountStr,
            "atendimento(s) em aberto",
            "/atendimentos"
        ));
        feed.add(new FeedItem(
            "<i class=\"fas fa-plus\"></i>",
            null,
            "Solicitar Atendimento",
            "/atendimentos/novo",
            "primary"
        ));
        request.setAttribute("feed", feed);
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

    public void displayTickets(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userCredentials");
        Set<Ticket> tickets = TicketFacade.listMine(user);
        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-index.jsp").forward(request, response);
    }

    public void displayNewTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("types", TicketFacade.listTypes());
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-new.jsp").forward(request, response);
    }

    public void displayExistingTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Ticket ticket = TicketFacade.find(id);
            User user = (User) request.getSession().getAttribute("userCredentials");
            if (ticket == null)
                request.setAttribute("error", "ID #" + id + " não encontrado");
            else if (!ticket.getOpenBy().equals(user))
                request.setAttribute("error", "acesso não autorizado ao atendimento #" + id);
            else
                request.setAttribute("ticket", ticket);
        } catch (NullPointerException | NumberFormatException ex) {
            request.setAttribute("error", "nenhum ID fornecido");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-form.jsp").forward(request, response);
    }

    public void processNewTicket(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        Set<TicketMessage> messages = new TreeSet<>();
        messages.add(extractMessageData(request));
        long typeId = Long.parseLong(request.getParameter("type"));
        TicketType type = TicketTypeDAO.find(typeId);
        long productId = Long.parseLong(request.getParameter("product"));
        Product product = ProductDAO.find(productId);
        Ticket ticket = new Ticket(
            null,
            LocalDateTime.now(),
            null,
            TicketStatus.OPEN,
            type,
            (User) request.getSession().getAttribute("userCredentials"),
            product,
            messages
        );
        TicketFacade.save(ticket);
        response.setStatus(200);
    }
    
    private TicketMessage extractMessageData(HttpServletRequest request) {
        TicketMessage message = new TicketMessage();
        message.setMessage(request.getParameter("message"));
        message.setSendDate(LocalDateTime.now());
        message.setSender((User) request.getSession().getAttribute("userCredentials"));
        try {
            message.setTicket(Long.parseLong(request.getParameter("id")));
        } catch (NumberFormatException ex) {
            message.setTicket(null);
        }
        return message;
    }
}
