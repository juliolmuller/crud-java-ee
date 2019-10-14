package br.com.beibe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.controller.ClienteController;

@WebServlet(name = "PublicRoutes", urlPatterns = {"/cliente/*"})
public class ClienteServlet extends HttpServlet {

    private final ClienteController ctrl = ClienteController.getInstance();

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURL().substring(request.getContextPath().length());
        switch (uri) {
            case "/":
                ctrl.displayHome(request, response);
                return;
            case "/atendimentos":
                ctrl.displayTickets(request, response);
                return;
            case "/atendimentos/novo":
                ctrl.displayNewTicketForm(request, response);
                return;
            case "/atendimentos/info":
                ctrl.displayExistingTicketForm(request, response);
                return;
            case "/dados-pessoais":
                ctrl.displayCustomerData(request, response);
                return;
            case "/dados-pessoais/editar":
                ctrl.displayCustomerDataForm(request, response);
                return;
            default:
                response.sendError(404);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURL().substring(request.getContextPath().length());
        switch (uri) {
            case "/atendimentos/novo":
                ctrl.processNewTicket(request, response);
                return;
            case "/atendimentos/info":
                ctrl.processTicketUpdate(request, response);
                return;
            case "/dados-pessoais/editar":
                ctrl.processCustomerUpdate(request, response);
                return;
            default:
                response.sendError(404);
        }
    }
}
