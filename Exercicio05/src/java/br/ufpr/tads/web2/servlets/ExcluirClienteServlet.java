package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirCliente", urlPatterns = {"/clientes-excluir"})
public class ExcluirClienteServlet extends HttpServlet {

    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        // Validar se usuário está logado
        if (request.getSession().getAttribute("login") == null) {
            try {
                request.setAttribute("msg", "Faça-me o favor de logar antes!");
                request.setAttribute("cor", "danger");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            } catch (ServletException e) {}
        }
        
        // Verifica se há um parâmetro 'id' e se o registro existe
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Cliente cliente = ClienteDAO.comId(id);
            
            // Efetuar exclusão do registro
            ClienteDAO.excluir(cliente);
        } catch (RuntimeException e) {}
        
        // Redirecionar para lista de clientes
        response.sendRedirect(request.getContextPath() + "/clientes");
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
