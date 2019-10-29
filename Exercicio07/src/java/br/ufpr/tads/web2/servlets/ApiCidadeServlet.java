/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.beans.Cidade;
import br.ufpr.tads.web2.beans.Estado;
import br.ufpr.tads.web2.facades.Facade;

@WebServlet(name = "CadastroCidade", urlPatterns = {"/api/cidades"})
public class ApiCidadeServlet extends HttpServlet {

    private String cidadeComoJSON(Cidade cidade) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(cidade.getId()).append(",");
        sb.append("\"nome\":\"").append(cidade.getNome()).append("\",");
        sb.append("\"estado\":{");
        sb.append("\"id\":").append(cidade.getEstado().getId()).append(",");
        sb.append("\"nome\":\"").append(cidade.getEstado().getNome()).append("\",");
        sb.append("\"sigla\":\"").append(cidade.getEstado().getSigla()).append("\"");
        sb.append("}}");
        return sb.toString();
    }

    @Override
    protected void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Recuperar dados do banco e montar resposta
        try (PrintWriter out = response.getWriter()) {
            int estadoId = Integer.parseInt(request.getParameter("estado"));
            List<Cidade> cidades = Facade.buscarCidades(estadoId);
            out.print("[");
            for (int i = 0; i < cidades.size(); i++) {
                out.print(cidadeComoJSON(cidades.get(i)));
                if (i < cidades.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
        }
    }
}
