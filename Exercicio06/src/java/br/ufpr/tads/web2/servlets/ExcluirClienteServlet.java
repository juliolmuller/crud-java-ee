package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.facades.ClienteFacade;

@WebServlet(name = "ExcluirCliente", urlPatterns = {"/clientes/excluir"})
public class ExcluirClienteServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Verifica se há um parâmetro 'id' e se o registro existe
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            ClienteFacade.remover(id);
        } catch (RuntimeException e) {}

        // Redirecionar para lista de clientes
        response.sendRedirect(request.getContextPath() + "/clientes");
    }
}
