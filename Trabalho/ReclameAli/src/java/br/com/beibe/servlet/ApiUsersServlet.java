package br.com.beibe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.beibe.beans.Address;
import br.com.beibe.beans.State;
import br.com.beibe.beans.User;
import br.com.beibe.beans.ValError;
import br.com.beibe.facade.UserFacade;
import br.com.beibe.utils.Converter;

@WebServlet(name = "UsersAPI", urlPatterns = {"/api/users"})
public class ApiUsersServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        User user;
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            user = UserFacade.find(id);
        } catch (NumberFormatException ex) {
            user = null;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            if (user != null) {
                out.print(json.toJson(user));
            } else {
                List<User> users = UserFacade.listEmployees();
                out.print(json.toJson(users));
            }
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            String role = request.getParameter("role");
            User user = User.getInstanceOf(role);
            List<ValError> errors;
            if (user == null) {
                errors = new ArrayList<>();
                errors.add(new ValError("role", "Perfil de acesso '" + role + "' não existe"));
                response.setStatus(422);
                out.print(json.toJson(errors));
                return;
            }
            extractData(request, user);
            errors = user.validate();
            switch (action) {
                case "delete":
                    User currentUser = (User) request.getSession().getAttribute("userCredentials");
                    if (user.getId() == null) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Nenhum ID informado para exclusão")));
                    } else if (currentUser.getId().equals(user.getId())) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Você não pode excluir seu próprio perfil")));
                    } else if (!UserFacade.listTickets(user).isEmpty()) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Há produtos associados a essa categoria")));
                    } else if (!UserFacade.delete(user)) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Falha ao excluir categoria")));
                    } else
                        out.print(json.toJson(new ValError("success", "Categoria excluída com sucesso")));
                    return;
                case "new":
                    if (!request.getParameter("password2").equals(user.getPassword()))
                        errors.add(new ValError("password2", "As senhas não conferem"));
                    if (errors.isEmpty()) {
                        UserFacade.save(user);
                        out.print(json.toJson(user));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
                case "update":
                    errors.removeIf(error -> error.getField().equals("password1"));
                    if (errors.isEmpty()) {
                        UserFacade.save(user);
                        out.print(json.toJson(user));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
                case "password":
                    errors.removeIf(error -> !error.getField().equals("password1"));
                    if (!request.getParameter("password2").equals(user.getPassword()))
                        errors.add(new ValError("password2", "As senhas não conferem"));
                    if (errors.isEmpty()) {
                        UserFacade.save(user);
                        out.print(json.toJson(user));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
            }
        }
        response.sendError(404);
    }

    private void extractData(HttpServletRequest request, User user) {
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
    }
}
