package br.com.beibe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.beibe.beans.Category;
import br.com.beibe.beans.ValError;
import br.com.beibe.facade.CategoryFacade;

@WebServlet(name = "CategoriesAPI", urlPatterns = {"/api/categories"})
public class ApiCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        Category category;
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            category = CategoryFacade.find(id);
        } catch (NumberFormatException ex) {
            category = null;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            if (category != null) {
                out.print(json.toJson(category));
            } else {
                List<Category> categories = CategoryFacade.listAll();
                out.print(json.toJson(categories));
            }
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            Category category = extractData(request);
            List<ValError> errors;
            switch (action) {
                case "delete":
                    if (category.getId() == null) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Nenhum ID informado para exclusão")));
                    } else if (!CategoryFacade.listProducts(category).isEmpty()) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Há produtos associados a essa categoria")));
                    } else if (!CategoryFacade.delete(category)) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Falha ao excluir categoria")));
                    } else
                        out.print(json.toJson(new ValError("success", "Categoria excluída com sucesso")));
                    return;
                case "new":
                case "update":
                    errors = category.validate();
                    if (errors.isEmpty()) {
                        CategoryFacade.save(category);
                        out.print(json.toJson(category));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
            }
        }
        response.sendError(404);
    }

    private Category extractData(HttpServletRequest request) {
        Category category = new Category();
        try {
            category.setId(Long.parseLong(request.getParameter("id")));
        } catch (NumberFormatException | NullPointerException ex) {
            category.setId(null);
        }
        category.setName(request.getParameter("name"));
        return category;
    }
}
