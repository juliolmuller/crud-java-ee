package br.com.beibe.servlet;

import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebInitParam;
import br.com.beibe.beans.User;
import br.com.beibe.beans.Address;
import br.com.beibe.config.AccessRole;
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
                        newUser = UserFacade.save(newUser);
                        configSessionAndForward(newUser, request, response);
                    } catch (SQLException ex) {
                        throw new ServletException(ex);
                    }
                } else {
                    request.setAttribute("dataErrors", errors);
                    request.getRequestDispatcher("/WEB-INF/jsp/error-json.jsp").forward(request, response);
                }
                return;
        }
        response.sendError(404);
    }

    private void configSessionAndForward(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userData", user);
        session.setAttribute("accessRole", user.getRole());
        response.sendRedirect(request.getContextPath() + "/" + user.getRole());
    }

    private User extractUserData(HttpServletRequest request) {
        User user = new User();
        Address address = new Address();
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setCpf(request.getParameter("cpf"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password1"));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            user.setDateBirth(formatter.parse(request.getParameter("date_birth")));
        } catch (ParseException | NullPointerException ex) {
            user.setDateBirth(null);
        }
        address.setZipCode(request.getParameter("zip_code"));
        address.setStreet(request.getParameter("street"));
        address.setComplement(request.getParameter("complement"));
        address.setNeightborhood(request.getParameter("neightborhood"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        try {
            String number = request.getParameter("number");
            if (number == null) {
                address.setNumber(null);
            } else {
                address.setNumber(Integer.parseInt(number));
            }
        } catch (NumberFormatException ex) {
            address.setNumber(-1);
        }
        user.setAddress(address);
        user.setRole(AccessRole.CLIENTE);
        return user;
    }
}
