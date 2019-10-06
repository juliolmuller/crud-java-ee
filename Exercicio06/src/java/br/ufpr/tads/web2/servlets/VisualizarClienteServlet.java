package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VisualizarCliente", urlPatterns = {"/clientes/visualizar"})
public class VisualizarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Verifica se há um parâmetro 'id' e se o registro existe
        Cliente cliente = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            cliente = ClienteDAO.comId(id);
            if (cliente == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/clientes");
        }

        // Setar bean em atributo da requisição e redirecionar
        request.setAttribute("cliente", cliente);
        request.setAttribute("readOnly", true);
        request.getRequestDispatcher("/jsp/clientesForm.jsp").forward(request, response);
    }
}
