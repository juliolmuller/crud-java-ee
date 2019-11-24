package br.com.beibe.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.beans.Hyperlink;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.User;
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
            case "/dados-pessoais":
                setHeaderLinks(request, 2);
                displayCustomerData(request, response);
                return;
            case "/dados-pessoais/editar":
                setHeaderLinks(request, 2);
                displayCustomerDataForm(request, response);
                return;
        }
        response.sendError(404);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        switch (uri) {
            case "/atendimentos/novo":
                processNewTicket(request, response);
                return;
            case "/atendimentos/acompanhar":
                processExistingTicket(request, response);
                return;
            case "/dados-pessoais/editar":
                processExistingCustomer(request, response);
                return;
        }
        response.sendError(404);
    }

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Meus Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Meus Dados", "/dados-pessoais"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

    public void displayHome(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
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
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-new.jsp").forward(request, response);
    }

    public void displayExistingTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-form.jsp").forward(request, response);
    }

    public void displayCustomerData(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/users-form.jsp").forward(request, response);
    }

    public void displayCustomerDataForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/users-form.jsp").forward(request, response);
    }

    public void processNewTicket(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cliente/atendimentos");
    }

    public void processExistingTicket(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cliente/atendimentos");
    }

    public void processExistingCustomer(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cliente/dados-pessoais");
    }
}
