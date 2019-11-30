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
import br.com.beibe.beans.FeedItem;
import br.com.beibe.beans.Hyperlink;
import br.com.beibe.beans.Ticket;
import br.com.beibe.dao.StateDAO;
import br.com.beibe.facade.TicketFacade;
import br.com.beibe.facade.UserFacade;

@WebServlet(name = "GerenteServlet", urlPatterns = {"/gerente/*"})
public class GerenteServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String baseUri = request.getContextPath() + "/gerente";
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
            case "/atendimentos/acompanhar":
                setHeaderLinks(request, 1);
                displayExistingTicketForm(request, response);
                return;
            case "/colaboradores":
                setHeaderLinks(request, 2);
                displayUsers(request, response);
                return;
            case "/relatorios":
                setHeaderLinks(request, 3);
                generateReport(request, response);
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
        switch (uri) {}
        response.sendError(404);
    }

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Colaboradores", "/colaboradores"));
        headerLinks.add(new Hyperlink("Relatórios", "/relatorios"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

    public void displayHome(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        List<FeedItem> feed = new ArrayList<>();
        Integer ticketsCount = TicketFacade.listOpen().size();
        String ticketsCountStr = ticketsCount.toString();
        feed.add(new FeedItem(
            null,
            ticketsCountStr,
            "atendimento(s) em aberto",
            "/atendimentos"
        ));
        final long DAYS = 7;
        ticketsCount = TicketFacade.listOpenFrom(DAYS).size();
        ticketsCountStr = ticketsCount.toString();
        feed.add(new FeedItem(
            null,
            ticketsCountStr,
            "aberto há mais de " + DAYS + " dias",
            "/atendimentos",
            "danger"
        ));
        feed.add(new FeedItem(
            "<i class=\"fas fa-user-plus\"></i>",
            null,
            "Administrar Colaboradores",
            "/colaboradores",
            "primary"
        ));
        feed.add(new FeedItem(
            "<i class=\"fas fa-newspaper\"></i>",
            null,
            "Emitir Relatórios",
            "/relatorios",
            "success"
        ));
        request.setAttribute("feed", feed);
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

    public void displayTickets(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        Set<Ticket> tickets = TicketFacade.listAll();
        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-index.jsp").forward(request, response);
    }

    public void displayExistingTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Ticket ticket = TicketFacade.find(id);
            if (ticket == null)
                request.setAttribute("error", "ID #" + id + " não encontrado");
            else
                request.setAttribute("ticket", ticket);
        } catch (NullPointerException | NumberFormatException ex) {
            request.setAttribute("error", "nenhum ID fornecido");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-form.jsp").forward(request, response);
    }

    public void displayUsers(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("users", UserFacade.listEmployees());
        request.setAttribute("states", StateDAO.getList());
        request.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);
    }

    public void generateReport(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/reports.jsp").forward(request, response);
    }
}
