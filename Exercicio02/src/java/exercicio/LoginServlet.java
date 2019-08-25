
package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
            out.println("<title>Web 2 :: Exercício 02</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
            out.println("<link rel=\"stylesheet\" href=\"css/login-styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"wrapper fade-in-down\">");
            out.println("<div id=\"form-content\">");
            out.println("<div class=\"fade-in first\">");
            if (request.getParameter("login") != null && request.getParameter("login").equals(request.getParameter("senha"))) {
                out.println("<img src=\"img/check-icon.png\" id=\"icon\" alt=\"Ícone de sucesso\" /></div>");
                out.println("<h3 class=\"mb-5 fade-in second text-success\">Login realizado com suncesso!</h3>");
                out.println("<div id=\"form-footer\">");
                out.println("<a href=\"portal\" class=\"underline-hover\">Prosseguir para o Portal &gt;&gt;&gt;</a>");
            } else {
                out.println("<img src=\"img/uncheck-icon.png\" id=\"icon\" alt=\"Ícone de erro\" /></div>");
                out.println("<h3 class=\"mb-5 fade-in third text-danger\">Ops! Credenciais inválidas!</h3>");
                out.println("<div id=\"form-footer\">");
                out.println("<a href=\"" + request.getContextPath() + "\" class=\"underline-hover\">&lt;&lt;&lt; Voltar para a tela de login</a>");
            }
            out.println("</div></div></div>");
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
