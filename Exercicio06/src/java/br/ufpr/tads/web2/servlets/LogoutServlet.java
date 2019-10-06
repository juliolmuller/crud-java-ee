package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // Validar se usuário está logado
        request.getSession().invalidate();
        try {
          request.setAttribute("msg", "Usuário desconectado com sucesso!");
          request.setAttribute("cor", "warning");
          request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {}
    }
}
