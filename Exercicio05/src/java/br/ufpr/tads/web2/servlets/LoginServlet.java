package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.LoginBean;
import br.ufpr.tads.web2.beans.Usuario;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.dao.UsuarioDAO;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException, SQLException {

        // Ajustar configuração charset de entrada
        request.setCharacterEncoding("UTF-8");

        // Capturar credenciais de acesso
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // Validar se usuário está logado
        if (login == null && senha == null && request.getSession(false) != null) {
            response.sendRedirect("portal.jsp");
            return;
        }

        // Validar credenciais e efetuar login
        Usuario usuario;
        if (login != null && senha != null) {
            usuario = UsuarioDAO.validar(login, senha);
            if (usuario != null) {
                LoginBean bean = new LoginBean();
                bean.setIdUsuario(usuario.getId());
                bean.setLoginUsuario(usuario.getLogin());
                bean.setNomeUsuario(usuario.getNome());
                request.getSession().setAttribute("login", bean);
                response.sendRedirect("portal.jsp");
                return;
            }
        }

        // Em caso de erro, exibir view de erro
        request.setAttribute("msg", "Ops! Você errou alguma coisa!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
