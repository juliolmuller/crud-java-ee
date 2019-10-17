package br.com.beibe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.controller.GerenteController;

@WebServlet(name = "GerenteRoutes", urlPatterns = {"/gerente/*"})
public class GerenteRoutesServlet extends HttpServlet {

    private final GerenteController ctrl = GerenteController.getInstance();

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
                ctrl.displayHome(request, response);
                return;
            case "/atendimentos":
                ctrl.displayTickets(request, response);
                return;
            case "/atendimentos/acompanhar":
                ctrl.displayExistingTicketForm(request, response);
                return;
            case "/colaboradores":
                ctrl.displayUsers(request, response);
                return;
            case "/colaboradores/novo":
            case "/colaboradores/visualizar":
            case "/colaboradores/editar":
                ctrl.displayUsersForm(request, response);
                return;
            case "/relatorios":
                ctrl.generateReport(request, response);
                return;
            default:
                System.out.println("Gerente 404: (GET) " + uri);
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
            case "/colaboradores/novo":
                ctrl.processNewUser(request, response);
                return;
            case "/colaboradores/editar":
                ctrl.processExistingUser(request, response);
                return;
            case "/colaboradores/excluir":
                ctrl.deleteUser(request, response);
                return;
            default:
                System.out.println("Gerente 404: (POST) " + uri);
                response.sendError(404);
        }
    }
}
