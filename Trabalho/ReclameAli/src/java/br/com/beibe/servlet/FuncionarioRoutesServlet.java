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
import br.com.beibe.controller.FuncionarioController;

@WebServlet(name = "FuncionarioRoutes", urlPatterns = {"/funcionario/*"})
public class FuncionarioRoutesServlet extends HttpServlet {

    private final FuncionarioController ctrl = FuncionarioController.getInstance();

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Categorias", "/categorias"));
        headerLinks.add(new Hyperlink("Produtos", "/produtos"));
        headerLinks.get(activePage).setActive(true);
        request.setAttribute("headerLinks", headerLinks);
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String baseUri = request.getContextPath() + "/funcionario";
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
            case "/categorias":
                setHeaderLinks(request, 2);
                ctrl.displayCategories(request, response);
                return;
            case "/categorias/nova":
            case "/categorias/visualizar":
            case "/categorias/editar":
                setHeaderLinks(request, 2);
                ctrl.displayCategoriesForm(request, response);
                return;
            case "/produtos":
                setHeaderLinks(request, 3);
                ctrl.displayProducts(request, response);
                return;
            case "/produtos/novo":
            case "/produtos/visualizar":
            case "/produtos/editar":
                setHeaderLinks(request, 3);
                ctrl.displayProductsForm(request, response);
                return;
            default:
                System.out.println("Funcionario 404: (GET) " + uri);
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
            case "/atendimentos/acompanhar":
                ctrl.processExistingTicket(request, response);
                return;
            case "/categorias/nova":
                ctrl.processNewCategory(request, response);
                return;
            case "/categorias/editar":
                ctrl.processExistingCategory(request, response);
                return;
            case "/categorias/excluir":
                ctrl.deleteCategory(request, response);
                return;
            case "/produtos/novo":
                ctrl.processNewProduct(request, response);
                return;
            case "/produtos/editar":
                ctrl.processExistingProduct(request, response);
                return;
            case "/produtos/excluir":
                ctrl.deleteProduct(request, response);
                return;
            default:
                System.out.println("Funcionario 404: (POST) " + uri);
                response.sendError(404);
        }
    }
}
