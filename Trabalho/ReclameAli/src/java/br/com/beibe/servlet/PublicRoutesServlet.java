package br.com.beibe.servlet;

import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import br.com.beibe.beans.Usuario;
import br.com.beibe.facade.UsuarioFacade;

@WebServlet(name = "PublicRoutes", urlPatterns = {"/entrar"}, initParams = {
    @WebInitParam(name = "routesFile", value = "/routes.properties")
})
public class PublicRoutesServlet extends HttpServlet {

    private Properties rolesRoutes;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (InputStream is = this.getClass().getResourceAsStream(config.getInitParameter("routesFile"))) {
            rolesRoutes = new Properties();
            rolesRoutes.load(is);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "signin":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                Usuario user = UsuarioFacade.authenticate(login, password);
                if (user != null) {
                    configSessionAndForward(user, request, response);
                } else {
                    request.setAttribute("authError", "Usuário e/ou senha inválidos.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                return;
            case "signup":
                Usuario newUser = extractUserData(request);
                List<String> errors = UsuarioFacade.validate(newUser);
                if (errors.isEmpty()) {
                    try {
                        UsuarioFacade.save(newUser);
                        configSessionAndForward(newUser, request, response);
                    } catch (SQLException ex) {
                        throw new ServletException(ex);
                    }
                } else {
                    request.setAttribute("dataErrors", errors);
                }
                return;
        }
        response.sendError(404);
    }

    private void configSessionAndForward(Usuario user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userData", user);
        session.setAttribute("accessRole", user.getRole());
        request.getRequestDispatcher(rolesRoutes.getProperty(user.getRole())).forward(request, response);
    }

    private Usuario extractUserData(HttpServletRequest request) {
        return null;
    }
}
