package br.com.beibe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.controller.FuncionarioController;

@WebServlet(name = "FuncionarioRoutes", urlPatterns = {"/funcionario/*"})
public class FuncionarioRoutesServlet extends HttpServlet {

    private final FuncionarioController ctrl = FuncionarioController.getInstance();

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI().substring((request.getContextPath() + "/funcionario").length());
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
            case "/categorias":
                ctrl.displayCategories(request, response);
                return;
            case "/categorias/nova":
            case "/categorias/visualizar":
            case "/categorias/editar":
                ctrl.displayCategoriesForm(request, response);
                return;
            case "/produtos":
                ctrl.displayProducts(request, response);
                return;
            case "/produtos/novo":
            case "/produtos/visualizar":
            case "/produtos/editar":
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
