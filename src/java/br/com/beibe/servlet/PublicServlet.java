package br.com.beibe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.beibe.beans.Address;
import br.com.beibe.beans.Cliente;
import br.com.beibe.beans.User;
import br.com.beibe.beans.State;
import br.com.beibe.beans.ValError;
import br.com.beibe.dao.StateDAO;
import br.com.beibe.facade.UserFacade;
import br.com.beibe.utils.Converter;

@WebServlet(name = "PublicServlet", urlPatterns = {"/entrar", "/sair"})
public class PublicServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        if (request.getRequestURI().split("/")[2].equals("sair")) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/entrar");
        } else {
            List<State> states = StateDAO.getList();
            request.setAttribute("states", states);
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
                    request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
                }
                return;
            case "signup":
                User newUser = extractUserData(request);
                List<ValError> errors = newUser.validate();
                if (!request.getParameter("password2").equals(newUser.getPassword()))
                    errors.add(new ValError("password2", "As senhas não conferem"));
                if (!Boolean.parseBoolean(request.getParameter("terms")))
                    errors.add(new ValError("terms", "Você precisa aceitas os termos de uso da plataforma para continuar"));
                if (errors.isEmpty()) {
                    UserFacade.save(newUser);
                    configSessionAndForward(newUser, request, response);
                } else {
                    response.setStatus(422);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        Gson json = new Gson();
                        out.print(json.toJson(errors));
                    }
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
        } catch (NullPointerException | NumberFormatException ex) {
        } finally {
            address.setState(state);
        }
        user.setAddress(address);
        return user;
    }
}
