package br.com.beibe.servlet;

import br.com.beibe.service.ConnectionFactory;
import br.com.beibe.utils.Converter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import java.util.Date;

@WebServlet(name = "Relatorio", urlPatterns = {"/relatorio"})
public class Relatorio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        Connection con = null;
        String action = (String) request.getParameter("action");
        if (action.equals("empl")) {
            try {
                Class.forName("org.postgresql.Driver");
                con = ConnectionFactory.getConnection();
                String jasper = request.getContextPath() + "/employees.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                URL jasperURL = new URL(host + jasper);
                Map<String, Object> params = new HashMap<>();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                request.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                    }
                }
            }

        } else if (action.equals("top")) {
            try {
                Class.forName("org.postgresql.Driver");
                con = ConnectionFactory.getConnection();
                String jasper = request.getContextPath() + "/topreclamacao.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                URL jasperURL = new URL(host + jasper);
                Map<String, Object> params = new HashMap<>();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                request.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                    }
                }
            }

        } else if (action.equals("recl")) {
            String tStatus = (String)request.getParameter("tStatus");
            int sA, sB;
            try {
                Class.forName("org.postgresql.Driver");
                con = ConnectionFactory.getConnection();
                String jasper = request.getContextPath() + "/reclamacao.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                URL jasperURL = new URL(host + jasper);
                Map<String, Object> params = new HashMap<>();
                switch(tStatus) {
                    case ("todos"):
                        sA = 1;
                        sB = 2;
                        break;
                    case ("abertos"):
                        sA = 1;
                        sB = 1;
                        break;
                    case ("fechados"):
                        sA = 2;
                        sB = 2;
                        break;
                    default:
                        sA = 1;
                        sB = 2;
                        break;
                }
                params.put("sA", sA);
                params.put("sB", sB);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                request.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                    }
                }
            }
            
        } else {
            String strDataI = (String)request.getParameter("dataIni");
            String strDataF = (String)request.getParameter("dataFim");
            Date dataI;
            Date dataF;
            dataI = Converter.toDate(strDataI);
            dataF = Converter.toDate(strDataF);
            try {
                Class.forName("org.postgresql.Driver");
                con = ConnectionFactory.getConnection();
                String jasper = request.getContextPath() + "/solicitacoes.jasper";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                URL jasperURL = new URL(host + jasper);
                Map<String, Object> params = new HashMap<>();
                params.put("dataI", dataI);
                params.put("dataF", dataF);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException e) {
                request.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                    }
                }
            }
            
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
        } catch (ParseException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
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
