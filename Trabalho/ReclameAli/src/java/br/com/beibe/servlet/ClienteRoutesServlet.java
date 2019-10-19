package br.com.beibe.servlet;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.beans.Hyperlink;
import br.com.beibe.controller.ClienteController;

@WebServlet(name = "ClienteRoutes", urlPatterns = {"/cliente/*"})
public class ClienteRoutesServlet extends HttpServlet {

    private final ClienteController ctrl = ClienteController.getInstance();

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Meus Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Meus Dados", "/dados-pessoais"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

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
                ctrl.displayHome(request, response);
                return;
            case "/atendimentos":
                setHeaderLinks(request, 1);
                ctrl.displayTickets(request, response);
                return;
            case "/atendimentos/novo":
                setHeaderLinks(request, 1);
                ctrl.displayNewTicketForm(request, response);
                return;
            case "/atendimentos/acompanhar":
                setHeaderLinks(request, 1);
                ctrl.displayExistingTicketForm(request, response);
                return;
            case "/dados-pessoais":
                setHeaderLinks(request, 2);
                ctrl.displayCustomerData(request, response);
                return;
            case "/dados-pessoais/editar":
                setHeaderLinks(request, 2);
                ctrl.displayCustomerDataForm(request, response);
                return;
            default:
                System.out.println("Cliente 404: (GET) " + uri);
                response.sendError(404);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        switch (uri) {
            case "/atendimentos/novo":
                ctrl.processNewTicket(request, response);
                return;
            case "/atendimentos/acompanhar":
                ctrl.processExistingTicket(request, response);
                return;
            case "/dados-pessoais/editar":
                ctrl.processExistingCustomer(request, response);
                return;
            default:
                System.out.println("Cliente 404: (POST) " + uri);
                response.sendError(404);
        }
    }
}
