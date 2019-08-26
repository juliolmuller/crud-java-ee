
package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PortalServlet", urlPatterns = {"/portal"})
public class PortalServlet extends HttpServlet {

    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        // Recuperar sessão ativa ou redirecionar para tela de login
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        // Montar resposta ao usuário
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
            out.println("<title>Web 2 :: Exercício 02</title>");
            out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<a href=\"" + request.getContextPath() + "/logout\">Logout</a>");
            out.println("<form class=\"my-5\">");
            out.println("<input type=\"text\" name=\"nome\" placeholder=\"Nome do usuário\" required /><br>");
            out.println("<input type=\"text\" name=\"login\" placeholder=\"Login de acesso\" required /><br>");
            out.println("<input type=\"password\" name=\"senha\" placeholder=\"Senha de acesso\" required /><br>");
            out.println("<button type=\"submit\">Cadastrar</button>");
            out.println("</form>");
            out.println("<table class=\"table\">");
            out.println("<thead class=\"thead-dark\">");
            out.println("<tr>");
            out.println("<th scope=\"col\">Nome do usuário</th>");
            out.println("<th scope=\"col\">Login de acesso</th>");
            out.println("<th scope=\"col\">Senha</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody></tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("<script src=\"" + request.getContextPath() + "/js/jquery.min.js\"></script>");
            out.println("<script src=\"" + request.getContextPath() + "/js/portal-scripts.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        }
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
