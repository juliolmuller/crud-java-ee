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
import br.com.beibe.beans.Product;
import br.com.beibe.beans.ValError;
import br.com.beibe.facade.ProductFacade;

@WebServlet(name = "ProductsAPI", urlPatterns = {"/api/products"})
public class ApiProductsServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        Product product;
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            product = ProductFacade.find(id);
        } catch (NumberFormatException ex) {
            product = null;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            if (product != null) {
                out.print(json.toJson(product));
            } else {
                List<Product> products = ProductFacade.listAll();
                out.print(json.toJson(products));
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
            Product product = extractData(request);
            List<ValError> errors;
            switch (action) {
                case "delete":
                    if (product.getId() == null) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Nenhum ID informado para exclusão")));
                    } else if (!ProductFacade.listTickets(product).isEmpty()) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Há atendimentos associados a esse produto")));
                    } else if (!ProductFacade.delete(product)) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Falha ao excluir produto")));
                    } else
                        out.print(json.toJson(new ValError("success", "Produto excluído com sucesso")));
                    return;
                case "new":
                case "update":
                    errors = product.validate();
                    if (errors.isEmpty()) {
                        ProductFacade.save(product);
                        out.print(json.toJson(product));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
            }
        }
        response.sendError(404);
    }

    private Product extractData(HttpServletRequest request) {
        Product product = new Product();
        try {
            product.setId(Long.parseLong(request.getParameter("id")));
        } catch (NumberFormatException | NullPointerException ex) {
            product.setId(null);
        }
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        try {
            product.setWeight(Double.parseDouble(request.getParameter("weight")));
        } catch (NumberFormatException | NullPointerException ex) {
            product.setWeight(null);
        }
        product.setUtc(request.getParameter("utc_code"));
        product.setEan(request.getParameter("ean_code"));
        Category category = new Category();
        try {
            category.setId(Long.parseLong(request.getParameter("category")));
        } catch (NullPointerException | NumberFormatException ex) {
        } finally {
            product.setCategory(category);
        }

        return product;
    }
}
