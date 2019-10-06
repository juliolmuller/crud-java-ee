package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.LoginBean;
import br.ufpr.tads.web2.beans.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufpr.tads.web2.dao.UsuarioDAO;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Ajustar configuração charset de entrada
        request.setCharacterEncoding("UTF-8");

        // Capturar credenciais de acesso
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // Validar credenciais e efetuar login
        Usuario usuario;
        if (login != null && senha != null) {
            usuario = UsuarioDAO.validar(login, senha);
            if (usuario != null) {
                LoginBean bean = new LoginBean();
                bean.setIdUsuario(usuario.getId());
                bean.setLoginUsuario(usuario.getLogin());
                bean.setNomeUsuario(usuario.getNome());
                request.getSession().setAttribute("login", bean);
                response.sendRedirect(request.getContextPath() + "/portal.jsp");
                return;
            }
        }

        // Em caso de erro, exibir view de erro
        request.setAttribute("msg", "Ops! Usuário e/ou senha inválidos!");
        request.setAttribute("cor", "danger");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
