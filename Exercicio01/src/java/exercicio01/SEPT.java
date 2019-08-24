
package exercicio01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SEPT", urlPatterns = {"/SEPT"})
public class SEPT extends HttpServlet {
    String[] curso = {
        "Agente Comunitário de Saúde",
        "Petróleo e Gás",
        "Análise e Desenvolvimento de Sistemas",
        "Comunicação Institucional",
        "Gestão da Qualidade",
        "Gestão Pública",
        "Luteria",
        "Negócios Imobiliários",
        "Produção Cênica",
        "Secretariado",
        "Pós-Graduação em Bioinformática",
        "Especialização em Inteligência Artificial",
        "Especialização em Engenharia de Software"
    };
    String[] linkCurso = {
        "http://www.tacs.ufpr.br",
        "http://www.sept.ufpr.br/portal/petroleogas/",
        "https://www.tads.ufpr.br",
        "http://www.tci.ufpr.br",
        "http://www.gestaodaqualidade.ufpr.br",
        "http://www.gestaopublica.ufpr.br",
        "http://www.luteria.ufpr.br/portal/",
        "http://www.sept.ufpr.br/portal/negociosimobiliarios/",
        "http://www.tpc.ufpr.br",
        "http://www.sept.ufpr.br/portal/secretariado/",
        "http://www.bioinfo.ufpr.br",
        "http://www.iaa.ufpr.br",
        "http://www.engenhariadesoftware.ufpr.br:28080/engenhariadesoftware/"    
    };
    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String[] imgCurso = {
            request.getContextPath() + "/imgs/01acs.png",
            request.getContextPath() + "/imgs/02pg.png",
            request.getContextPath() + "/imgs/03ads.png",
            request.getContextPath() + "/imgs/04ci.png",
            request.getContextPath() + "/imgs/05gq.png",
            request.getContextPath() + "/imgs/06gp.png",
            request.getContextPath() + "/imgs/07l.png",
            request.getContextPath() + "/imgs/08ni.png",
            request.getContextPath() + "/imgs/09pc.png",
            request.getContextPath() + "/imgs/10s.png",
            request.getContextPath() + "/imgs/11b.png",
            request.getContextPath() + "/imgs/12eia.png",
            request.getContextPath() + "/imgs/13ees.png"
        };
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
            out.println("<title>SEPT | Web 2</title>");
            out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath()+ "/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<header class=\"container-fluid\">");
            out.println("<div style=\"background: url('" + request.getContextPath()+ "/imgs/beemoBg.jpg') center;\">");
            out.println("<div style=\"background: rgb(255, 255, 255, 0.1);\">");
            out.println("<div class=\"container jumbotron text-white font-weight-bold\" style=\"background: rgb(0, 0, 0, 0);\">");
            out.println("<h1 class=\"display-4\" style=\"font-family:verdana;font-size:40px;color:blue;\">SEPT - Setor de Educação "
                + "Profissional e tecnológica</h1><br/><br/>");
            out.println("<a href=\"http://www.sept.ufpr.br/portal/\"><p class=\"font-weight-light\" style=\"font-family:arial;"
                + "color:black;font-size:20px;text-align:center\">Página do setor</p></a>");
            out.println("<hr class=\"d-block my-4 bg-white\" style=\"height: 2px;\" />");
            out.println("<div class=\"text-center\">");
            out.println("<a href=\"" + request.getContextPath() + "/tads.jsp\" class=\"btn btn-outline-dark\">Meu TADS</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class=\"container text-center font-family\">");
            out.println("<h2>Cursos oferecidos no setor</h2>");
            out.println("<main class=\"mt-5\"");
            out.println("<br><h3>Cursos técnicos</h3><br/>");
            out.println("<div class=\"row justify-content-center\">");
            out.println("<div class=\"card-deck\">");
            for (int i = 0, j = 0; i < curso.length; i++) {
                if (curso[i].equals("Análise e Desenvolvimento de Sistemas")) {
                    j = 0;
                    out.println("</div></div>"); // fim do <div row>
                    out.println("<hr class=\"d-block my-4 bg-blue\" style=\"height: 2px;\" />");
                    out.println("<br/><h3>Cursos Superiores de Tecnologia</h3><br/>");
                    out.println("<div class=\"row justify-content-center\">");
                    out.println("<div class=\"card-deck card-group\">");
                }
                if (curso[i].equals("Pós-Graduação em Bioinformática")) {
                    j = 0;
                    out.println("</div></div>"); //fim do <div row>
                    out.println("<hr class=\"d-block my-4 bg-blue\" style=\"height: 2px;\" />");
                    out.println("<br/><h3>Cursos de Pós Graduação</h3><br/>");
                    out.println("<div class=\"row justify-content-center\">");
                    out.println("<div class=\"card-deck card-group\">");
                }
                if (j == 4) {
                    out.println("</div></div><br/>"); // fim do <div row pra serparar linhas de cursos
                    out.println("<div class=\"row justify-content-center\">");
                    out.println("<div class=\"card-deck card-group\">");
                    j = 0;
                }
                out.println("<div class=\"card sm-3 m-4\" style=\"width:12rem;\">");
                out.println("<img class=\"card-img-top\" src=\"" + imgCurso[i] +"\" "
                    + "alt=\"Miniatura curso " + curso[i] + "\">");
                out.println("<div class=\"card-body\">");
                    out.println("<h5 class=\"card-title\">" + curso[i] + "</h5>");
                out.println("</div>");
                out.println("<div class=\"card-footer\">");
                out.println("<a href=\"" + linkCurso[i] + "\" class=\"card-link\">"
                    + "Página do Curso</a>");
                out.println("</div>");
                out.println("</div>");       
                j++;
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");
            out.println("</div>");
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
