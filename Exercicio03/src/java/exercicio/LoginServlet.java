package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
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
    ) throws ServletException, IOException, SQLException {
        
        // Ajustar configuração charset de entrada
        request.setCharacterEncoding("UTF-8");
        
        // Avaliar se haverá login ou se há sessão iniciada
        boolean logado = false, valid = false;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        HttpSession session = request.getSession(false);
        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String dbUser = "cassianovidal";
        String dbSenha = "";
        String url = "jdbc:postgresql://localhost:5432/web2ex3";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, dbUser, dbSenha);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            st = con.prepareStatement("select login_usuario, senha_usuario, nome_usuario from tb_usuario");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        while(rs.next()) {
            String loginDb = rs.getString("login_usuario");
            String senhaDb = rs.getString("senha_usuario");
            String nomeDb = rs.getString("nome_usuario");
            if (login.equals(loginDb) && senha.equals(senhaDb)) {
                logado = true;
                session = request.getSession();
                session.setAttribute("usuarioLogado", new Usuario(nomeDb, loginDb, senhaDb));
                valid = true;
                break;
            } 
        }
        if (!valid){
            if (login == null && senha == null && session != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/portal");
                rd.forward(request, response);
                return;
            }
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
                String errMsg = "Ops! Credenciais inválidas";
                String page = request.getContextPath()+ "/";
                request.setAttribute("errMsg", errMsg);
                request.setAttribute("page", page);
                RequestDispatcher rd = request.getRequestDispatcher("/Erro");
                rd.forward(request, response);

  
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
