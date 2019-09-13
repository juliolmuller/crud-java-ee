package servlets;

import model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UsuarioDAO;

@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/cadastrar-usuario"})
public class CadastrarUsuarioServlet extends HttpServlet {

    protected boolean validateRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {

        // Validar se usuário está logado
        if (request.getSession(false) == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        }
        
        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        return true;
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Validar session e configurar entrada e saída de dados
        if (!validateRequest(request, response)) {
            return;
        }

        // Recuperar dados do banco
        List<Usuario> usuarios = UsuarioDAO.listar();
        try (PrintWriter out = response.getWriter()) {
            out.print("[");
            for (int i = 0; i < usuarios.size(); i++) {
                out.print("{");
                out.print("\"id\":" + usuarios.get(i).getId() + ",");
                out.print("\"nome\":\"" + usuarios.get(i).getNome() + "\",");
                out.print("\"login\":\"" + usuarios.get(i).getLogin() + "\",");
                out.print("\"senha\":\"" + usuarios.get(i).getSenha() + "\"");
                out.print("}");
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

        // Validar session e configurar entrada e saída de dados
        if (!validateRequest(request, response)) {
            return;
        }

        // Salvar os parâmetros enviados na requisição
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // Validar se todos os parâmetros foram enviados
        List<String> erros = new ArrayList<>();
        if (nome == null || nome.equals("")) {
            erros.add("O campo NOME é obrigatório.");
        }
        if (login == null || login.equals("")) {
            erros.add("O campo LOGIN é obrigatório.");
        } else if (UsuarioDAO.existe(login)) {
            erros.add("Já existe um usuário com o login " + login.toUpperCase() + ".");
        }
        if (senha == null || senha.equals("")) {
            erros.add("O campo SENHA é obrigatório.");
        }

        // Montar resposta ao usuário
        try (PrintWriter out = response.getWriter()) {
            if (erros.size() > 0) {
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
            } else {
                
                // Instanciar usuário e inserir no banco de dados
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                UsuarioDAO.inserir(usuario);
                
                // Retornar dados de usuário inserido como JSON
                out.print("{");
                out.print("\"status\":\"success\",");
                out.print("\"data\":{");
                out.print("\"id\":" + usuario.getId() + ",");
                out.print("\"nome\":\"" + usuario.getNome() + "\",");
                out.print("\"login\":\"" + usuario.getLogin() + "\",");
                out.print("\"senha\":\"" + usuario.getSenha() + "\"");
                out.print("}}");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
