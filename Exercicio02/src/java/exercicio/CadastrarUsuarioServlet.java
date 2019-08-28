
package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/cadastrar-usuario"})
public class CadastrarUsuarioServlet extends HttpServlet {

    protected boolean validateRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException  {
        
        // Configurar input e output
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        // Validar se usuário está logado
        if (request.getSession(false) == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        }
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
        
        // Recuperar dados de sessão
        HttpSession session = request.getSession(false);
        List<Usuario> listaUsuarios = (ArrayList<Usuario>) session.getAttribute("listaUsuarios");
        
        // Se é a primeira vez que se está acessando a servlet, intanciar lista de usuários padrão
        if (listaUsuarios == null) {
            listaUsuarios = new ArrayList<>();
            listaUsuarios.add(new Usuario("André Antunes", "AANTUNES", "IS2adm"));
            listaUsuarios.add(new Usuario("Aurélio Matsunaga", "AMATSUNA", "GOisC00l"));
            listaUsuarios.add(new Usuario("Cassiano Vidal", "CVIDAL", "humble-boy"));
            listaUsuarios.add(new Usuario("Júlio Müller", "JMULLER", "qwerty123"));
            listaUsuarios.add(new Usuario("Wesley Caetano", "WCAETANO", "senha1234"));
            session.setAttribute("listaUsuarios", listaUsuarios);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print("[");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                out.print("{");
                out.print("\"nome\":\"" + listaUsuarios.get(i).getNome() + "\",");
                out.print("\"login\":\"" + listaUsuarios.get(i).getLogin() + "\",");
                out.print("\"senha\":\"" + listaUsuarios.get(i).getSenha() + "\"");
                out.print("}");
                if (i < listaUsuarios.size() - 1) {        
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
        System.out.print(nome + " => Júlio");
        if (nome.equals("")) {
            erros.add("O campo NOME é obrigatório.");
        }
        if (login.equals("")) {
            erros.add("O campo LOGIN é obrigatório.");
        }
        if (senha.equals("")) {
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
                HttpSession session = request.getSession(false);
                List<Usuario> listaUsuarios = (ArrayList<Usuario>) session.getAttribute("listaUsuarios");
                Usuario usuario = new Usuario(nome, login, senha);
                listaUsuarios.add(usuario);
                session.setAttribute("listaUsuarios", listaUsuarios);
                out.print("{");
                out.print("\"status\":\"success\",");
                out.print("\"data\":{");
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
