package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VisualizarCliente", urlPatterns = {"/clientes-visualizar"})
public class VisualizarClienteServlet extends HttpServlet {

    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Validar se usuário está logado
        if (request.getSession().getAttribute("login") == null) {
            request.setAttribute("msg", "Faça-me o favor de logar antes!");
            request.setAttribute("cor", "danger");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

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
        request.getRequestDispatcher("clientesVisualizar.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
