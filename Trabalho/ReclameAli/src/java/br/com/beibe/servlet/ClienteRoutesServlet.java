package br.com.beibe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.controller.ClienteController;

@WebServlet(name = "ClienteRoutes", urlPatterns = {"/cliente/*"})
public class ClienteRoutesServlet extends HttpServlet {

    private final ClienteController ctrl = ClienteController.getInstance();

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI().substring((request.getContextPath() + "/cliente").length());
        switch (uri) {
            case "":
            case "/":
                ctrl.displayHome(request, response);
                return;
            case "/atendimentos":
                ctrl.displayTickets(request, response);
                return;
            case "/atendimentos/novo":
                ctrl.displayNewTicketForm(request, response);
                return;
            case "/atendimentos/acompanhar":
                ctrl.displayExistingTicketForm(request, response);
                return;
            case "/dados-pessoais":
                ctrl.displayCustomerData(request, response);
                return;
            case "/dados-pessoais/editar":
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
