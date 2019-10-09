package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import br.ufpr.tads.web2.facades.ClienteFacade;
import br.ufpr.tads.web2.beans.Cliente;

@WebServlet(name = "Clientes", urlPatterns = {"/clientes"})
public class ClientesServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Recuperar os dados dos clientes do banco
        List<Cliente> clientes = ClienteFacade.buscarTodos();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/jsp/clientesListar.jsp").forward(request, response);
    }
}
