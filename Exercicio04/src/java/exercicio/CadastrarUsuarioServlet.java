package exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ) throws IOException {

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
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            while (rs.next()) {
                String loginDb = rs.getString("login_usuario");
                String senhaDb = rs.getString("senha_usuario");
                String nomeDb = rs.getString("nome_usuario");
                listaUsuarios.add(new Usuario(nomeDb, loginDb, senhaDb));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        if (nome == null || nome.equals("")) {
            erros.add("O campo NOME é obrigatório.");
        }
        if (login == null || login.equals("")) {
            erros.add("O campo LOGIN é obrigatório.");
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
                List<Usuario> listaUsuarios = new ArrayList<>();
                while (rs.next()) {
                    try {
                        String loginDb = rs.getString("login_usuario");
                        String senhaDb = rs.getString("senha_usuario");
                        String nomeDb = rs.getString("nome_usuario");
                        listaUsuarios.add(new Usuario(nomeDb, loginDb, senhaDb));
                    } catch (SQLException ex) {
                        Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    st = con.prepareStatement("insert into tb_usuario(login_usuario, senha_usuario, nome_usuario) values (?, ?, ?)");
                    st.setString(1, login);
                    st.setString(2, senha);
                    st.setString(3, nome);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
