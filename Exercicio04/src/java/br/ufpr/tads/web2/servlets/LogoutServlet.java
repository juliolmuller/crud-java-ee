package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // Avaliar se há sessões ativas
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Montar resposta ao usuário
        LoginBean usuario = (LoginBean) session.getAttribute("login");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"pt-BR\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
            out.println("<title>Web 2 :: Exercício 04</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\" />");
            out.println("<link rel=\"stylesheet\" href=\"css/login-styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"wrapper fade-in-down\">");
            out.println("<div id=\"form-content\">");
            out.println("<div class=\"fade-in first\">");
            out.println("<img src=\"img/bye-bye.png\""
                    + " id=\"icon\" class=\"m-5\" alt=\"Ícone de sucesso\" />");
            out.println("</div>");
            out.println("<h3 class=\"mb-5 fade-in second\">"
                    + "Até a próxima, " + usuario.getNomeUsuario() + "!"
                    + "</h3>");
            out.println("<div id=\"form-footer\">");
            out.println("<a href=\"" + request.getContextPath() + "/\" class=\"underline-hover\">Novo acesso</a>");
            out.println("</div></div></div>");
            out.println("</body>");
            out.println("</html>");
        }
        session.invalidate();
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
