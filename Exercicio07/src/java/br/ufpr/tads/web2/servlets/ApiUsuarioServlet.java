package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.beans.Usuario;
import br.ufpr.tads.web2.dao.UsuarioDAO;

@WebServlet(name = "CadastroUsuario", urlPatterns = {"/usuarios", "/api/usuarios"})
public class ApiUsuarioServlet extends HttpServlet {

    private List<String> validarDados(Usuario usuario, boolean novo) {
        
        // Instanciar lista para armazenar mensagens de erro
        List<String> erros = new ArrayList<>();

        // Validar se todos os parâmetros foram enviados
        if (!novo && usuario.getId() == 0) {
            erros.add("Nenhum ID informado.");
        }
        if (usuario.getNome() == null || usuario.getNome().equals("")) {
            erros.add("O campo NOME é obrigatório.");
        }
        if (usuario.getLogin() == null || usuario.getLogin().equals("")) {
            erros.add("O campo LOGIN é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
            erros.add("O campo SENHA é obrigatório.");
        }
        return erros;
    }

    private String usuarioComoJSON(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(usuario.getId()).append(",");
        sb.append("\"nome\":\"").append(usuario.getNome()).append("\",");
        sb.append("\"login\":\"").append(usuario.getLogin()).append("\",");
        sb.append("\"senha\":\"").append(usuario.getSenha()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Avaliar se deseja-se a página ou a API
        if (request.getRequestURI().split("/")[2].equals("usuarios")) {
            request.getRequestDispatcher("/jsp/usuarios.jsp").forward(request, response);
            return;
        }

        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Recuperar dados do banco
        List<Usuario> usuarios = UsuarioDAO.listar();
        try (PrintWriter out = response.getWriter()) {
            out.print("[");
            for (int i = 0; i < usuarios.size(); i++) {
                out.print(usuarioComoJSON(usuarios.get(i)));
                if (i < usuarios.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
        }

    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Instanciar usuário e atribuir parâmetros de formulpario
        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("nome"));
        usuario.setLogin(request.getParameter("login"));
        usuario.setSenha(request.getParameter("senha"));

        // Validar dados enviados
        List<String> erros = validarDados(usuario, true);

        // Montar resposta ao usuário
        try (PrintWriter out = response.getWriter()) {
            if (erros.isEmpty()) {

                // Salvar instância de usuário em banco de dados
                UsuarioDAO.inserir(usuario);

                // Retornar dados de usuário inserido como JSON
                out.print("{");
                out.print("\"status\":\"success\",");
                out.print("\"data\":");
                out.print(usuarioComoJSON(usuario));
                out.print("}");
                return;
            }

            // Em caso de erro, retornar status 422
            response.setStatus(422);
            out.print("{");
            out.print("\"status\":\"error\",");
            out.print("\"messages\":[");
            for (int i = 0; i < erros.size(); i++) {
                out.print("\"" + erros.get(i) + "\"");
                if (i < erros.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]}");
        }
    }

    @Override
    protected void doDelete(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Instanciar usuário e atribuir parâmetros de formulpario
        Usuario usuario = new Usuario();
        try {
            usuario.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            usuario.setId(0);
        }

        // Montar resposta ao usuário
        try (PrintWriter out = response.getWriter()) {

            // Excluir usuário do banco de dados
            if (UsuarioDAO.excluir(usuario)) {

                // Retornar dados de usuário inserido como JSON
                out.print("{");
                out.print("\"status\":\"success\",");
                out.print("\"data\":");
                out.print(usuarioComoJSON(usuario));
                out.print("}");
                return;
            }

            // Em caso de erro, retornar status 422
            response.setStatus(422);
            out.print("{");
            out.print("\"status\":\"error\",");
            out.print("\"messages\":[");
            out.print("\"ID #" + usuario.getId() + " não eancontrado para exclusão.\"");
            out.print("]}");
        }
    }

    @Override
    protected void doPut(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // Instanciar usuário e atribuir parâmetros de formulpario
        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("nome"));
        usuario.setLogin(request.getParameter("login"));
        usuario.setSenha(request.getParameter("senha"));
        try {
            usuario.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            usuario.setId(0);
        }

        // Validar dados enviados
        List<String> erros = validarDados(usuario, false);

        // Montar resposta ao usuário
        try (PrintWriter out = response.getWriter()) {
            if (erros.isEmpty()) {

                // Atualizar usuário em banco de dados
                UsuarioDAO.atualizar(usuario);

                // Retornar dados de usuário inserido como JSON
                out.print("{");
                out.print("\"status\":\"success\",");
                out.print("\"data\":");
                out.print(usuarioComoJSON(usuario));
                out.print("}");
                return;
            }

            // Em caso de erro, retornar status 422
            response.setStatus(422);
            out.print("{");
            out.print("\"status\":\"error\",");
            out.print("\"messages\":[");
            for (int i = 0; i < erros.size(); i++) {
                out.print("\"" + erros.get(i) + "\"");
                if (i < erros.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]}");
        }
    }
}
