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

@WebServlet(name = "FuncionarioRoutes", urlPatterns = {"/funcionario/*"})
public class FuncionarioRoutesServlet extends HttpServlet {

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
            case "/categorias":
                setHeaderLinks(request, 2);
                displayCategories(request, response);
                return;
            case "/categorias/nova":
            case "/categorias/visualizar":
            case "/categorias/editar":
                setHeaderLinks(request, 2);
                displayCategoriesForm(request, response);
                return;
            case "/produtos":
                setHeaderLinks(request, 3);
                displayProducts(request, response);
                return;
            case "/produtos/novo":
            case "/produtos/visualizar":
            case "/produtos/editar":
                setHeaderLinks(request, 3);
                displayProductsForm(request, response);
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
                processExistingTicket(request, response);
                return;
            case "/categorias/nova":
                processNewCategory(request, response);
                return;
            case "/categorias/editar":
                processExistingCategory(request, response);
                return;
            case "/categorias/excluir":
                deleteCategory(request, response);
                return;
            case "/produtos/novo":
                processNewProduct(request, response);
                return;
            case "/produtos/editar":
                processExistingProduct(request, response);
                return;
            case "/produtos/excluir":
                deleteProduct(request, response);
                return;
            default:
                System.out.println("Funcionario 404: (POST) " + uri);
                response.sendError(404);
        }
    }

    private void setHeaderLinks(HttpServletRequest request, int activePage) {
        List<Hyperlink> headerLinks = new ArrayList<>();
        headerLinks.add(new Hyperlink("Home", "/"));
        headerLinks.add(new Hyperlink("Atendimentos", "/atendimentos"));
        headerLinks.add(new Hyperlink("Categorias", "/categorias"));
        headerLinks.add(new Hyperlink("Produtos", "/produtos"));
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
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-index.jsp").forward(request, response);
    }

    public void displayExistingTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-form.jsp").forward(request, response);
    }

    public void displayCategories(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/categories-index.jsp").forward(request, response);
    }

    public void displayCategoriesForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/categories-form.jsp").forward(request, response);
    }

    public void displayProducts(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/products-index.jsp").forward(request, response);
    }

    public void displayProductsForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/products-form.jsp").forward(request, response);
    }

    public void processExistingTicket(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/atendimentos");
    }

    public void processNewCategory(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/categorias");
    }

    public void processExistingCategory(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/categorias");
    }

    public void deleteCategory(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/categorias");
    }

    public void processNewProduct(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/produtos");
    }

    public void processExistingProduct(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/produtos");
    }

    public void deleteProduct(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/funcionario/produtos");
    }
}
