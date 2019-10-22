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
import br.com.beibe.beans.User;
import br.com.beibe.facade.UserFacade;

@WebServlet(name = "PublicRoutes", urlPatterns = {"/entrar"}, initParams = {
    @WebInitParam(name = "routesFile", value = "/routes.properties")
})
public class PublicRoutesServlet extends HttpServlet {

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
                User user = UserFacade.authenticate(login, password);
                if (user != null) {
                    configSessionAndForward(user, request, response);
                } else {
                    request.setAttribute("authError", true);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                return;
            case "signup":
                User newUser = extractUserData(request);
                List<String> errors = UserFacade.validate(newUser);
                if (errors.isEmpty()) {
                    try {
                        UserFacade.save(newUser);
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

    private void configSessionAndForward(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userData", user);
        session.setAttribute("accessRole", user.getRole());
        request.getRequestDispatcher("/" + user.getRole()).forward(request, response);
    }

    private User extractUserData(HttpServletRequest request) {
        return null;
    }
}
