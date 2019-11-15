package br.com.beibe.servlet;

import java.io.IOException;
import java.util.List;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.beans.Address;
import br.com.beibe.beans.Cliente;
import br.com.beibe.beans.User;
import br.com.beibe.beans.State;
import br.com.beibe.beans.ValError;
import br.com.beibe.dao.StateDAO;
import br.com.beibe.facade.UserFacade;
import br.com.beibe.utils.Converter;

@WebServlet(name = "PublicServlet", urlPatterns = {"/entrar"})
public class PublicServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        List<State> states = StateDAO.getList();
        request.setAttribute("states", states);
        request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
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
                List<ValError> errors = UserFacade.validate(newUser);
                if (errors.isEmpty()) {
                    try {
                        UserFacade.save(newUser);
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
        request.getSession().setAttribute("userCredentials", user);
        response.sendRedirect(request.getContextPath() + "/" + user.getRole());
    }

    private User extractUserData(HttpServletRequest request) {
        User user = new Cliente();
        Address address = new Address();
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setCpf(request.getParameter("cpf"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password1"));
        try {
            user.setDateBirth(Converter.toLocalDate(request.getParameter("date_birth")));
        } catch (NullPointerException ex) {
            user.setDateBirth(null);
        }
        address.setZipCode(request.getParameter("zip_code"));
        address.setStreet(request.getParameter("street"));
        try {
            address.setNumber(Integer.parseInt(request.getParameter("number")));
        } catch (NumberFormatException | NullPointerException ex) {
            address.setNumber(null);
        }
        address.setComplement(request.getParameter("complement"));
        address.setNeightborhood(request.getParameter("neightborhood"));
        address.setCity(request.getParameter("city"));
        State state = new State();
        try {
            state.setId(Long.parseLong(request.getParameter("state")));
            address.setState(state);
        } catch (NullPointerException | NumberFormatException ex) {
            address.setState(state);
        }
        user.setAddress(address);
        return user;
    }
}
