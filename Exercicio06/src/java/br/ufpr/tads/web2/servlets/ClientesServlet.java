package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.util.List;

@WebServlet(name = "Clientes", urlPatterns = {"/clientes"})
public class ClientesServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Recuperar os dados dos clientes do banco
        List<Cliente> clientes = ClienteDAO.listar();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/jsp/clientesListar.jsp").forward(request, response);
    }
}
