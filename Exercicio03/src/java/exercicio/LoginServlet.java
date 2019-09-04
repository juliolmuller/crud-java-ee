
package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        // Ajustar configuração charset de entrada
        request.setCharacterEncoding("UTF-8");
        
        // Avaliar se haverá login ou se há sessão iniciada
        boolean logado = false;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        HttpSession session = request.getSession(false);
        if (login != null && login.equals(senha)) { // Se login é válido, armazenar em sessão
            logado = true;
            session = request.getSession();
            session.setAttribute("usuarioLogado", new Usuario("usuário \"" + login + "\"", login, senha));
        } else if (login == null && senha == null && session != null) { // Se nenhuma credencial for passada e já há uma sessão, redirecionar para 'Portal'
            response.sendRedirect(request.getContextPath() + "/portal");
            return;
        }
        
        // Montar resposta ao usuário
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
            out.println("<title>Web 2 :: Exercício 03</title>");
            out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/bootstrap.min.css\">");
            out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/login-styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"wrapper fade-in-down\">");
            out.println("<div id=\"form-content\">");
            out.println("<div class=\"fade-in first\">");
            if (logado) {
                out.println("<img src=\"" + request.getContextPath() + "/img/check-icon.png\" id=\"icon\" alt=\"Ícone de sucesso\" /></div>");
                out.println("<h3 class=\"mb-5 fade-in second text-success\">Login realizado com sucesso!</h3>");
                out.println("<div id=\"form-footer\">");
                out.println("<a href=\"" + request.getContextPath() + "/portal\" class=\"underline-hover\">Prosseguir para o Portal</a>");
            } else {
                out.println("<img src=\"" + request.getContextPath() + "/img/uncheck-icon.png\" id=\"icon\" alt=\"Ícone de erro\" /></div>");
                out.println("<h3 class=\"mb-5 fade-in third text-danger\">Ops! Credenciais inválidas!</h3>");
                out.println("<div id=\"form-footer\">");
                out.println("<a href=\"" + request.getContextPath() + "/\" class=\"underline-hover\">Voltar para a tela de login</a>");
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
