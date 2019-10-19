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
import br.com.beibe.controller.GerenteController;

@WebServlet(name = "GerenteRoutes", urlPatterns = {"/gerente/*"})
public class GerenteRoutesServlet extends HttpServlet {

    private final GerenteController ctrl = GerenteController.getInstance();

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Colaboradores", "/colaboradores"));
        headerLinks.add(new Hyperlink("Relatórios", "/relatorios"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

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
                ctrl.displayHome(request, response);
                return;
            case "/atendimentos":
                setHeaderLinks(request, 1);
                ctrl.displayTickets(request, response);
                return;
            case "/atendimentos/acompanhar":
                setHeaderLinks(request, 1);
                ctrl.displayExistingTicketForm(request, response);
                return;
            case "/colaboradores":
                setHeaderLinks(request, 2);
                ctrl.displayUsers(request, response);
                return;
            case "/colaboradores/novo":
            case "/colaboradores/visualizar":
            case "/colaboradores/editar":
                setHeaderLinks(request, 2);
                ctrl.displayUsersForm(request, response);
                return;
            case "/relatorios":
                setHeaderLinks(request, 3);
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
