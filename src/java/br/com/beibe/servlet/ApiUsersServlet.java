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
import br.com.beibe.dao.UserDAO;
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
        Gson json = new Gson();
        List<ValError> errors = new ArrayList<>();
        PrintWriter out = response.getWriter();
        try {
            User user; Long id;
            String role = Converter.nullable(request.getParameter("role"));
            switch (action) {
                case "delete":
                    id = Long.parseLong(request.getParameter("id"));
                    user = UserDAO.find(id);
                    User currentUser = (User) request.getSession().getAttribute("userCredentials");
                    if (currentUser.getId().equals(user.getId())) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Você não pode excluir seu próprio perfil")));
                    } else if (!UserFacade.listTickets(user).isEmpty()) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Há atendimentos associados a esse usuário")));
                    } else if (!UserFacade.delete(user)) {
                        response.setStatus(422);
                        out.print(json.toJson(new ValError("error", "Falha ao excluir usuário")));
                    } else
                        out.print(json.toJson(new ValError("success", "Usuário excluído com sucesso")));
                    return;
                case "update":
                    id = Long.parseLong(request.getParameter("id"));
                    user = User.getInstanceOf(role);
                    if (user == null) {
                        response.setStatus(422);
                        errors.add(new ValError("error", "Perfil de acesso não selecionado"));
                        out.print(json.toJson(errors));
                    } else {
                        User original = UserFacade.find(id);
                        user.setCpf(original.getCpf());
                        user.setId(id);
                        extractData(request, user);
                        errors = user.validate();
                        errors.removeIf(error -> error.getField().equals("cpf") || error.getField().equals("email") || error.getField().equals("password1"));
                        if (errors.isEmpty()) {
                            UserFacade.save(user);
                            out.print(json.toJson(user));
                        } else {
                            response.setStatus(422);
                            out.print(json.toJson(errors));
                        }
                    }
                    return;
                case "new":
                    user = User.getInstanceOf(role);
                    if (user == null) {
                        response.setStatus(422);
                        errors.add(new ValError("error", "Perfil de acesso não selecionado"));
                        out.print(json.toJson(errors));
                    } else {
                        extractData(request, user);
                        errors = user.validate();
                        if (user.getPassword() == null || !user.getPassword().equals(request.getParameter("password2")))
                            errors.add(new ValError("password2", "As senhas não conferem"));
                        if (errors.isEmpty()) {
                            UserFacade.save(user);
                            out.print(json.toJson(user));
                        } else {
                            response.setStatus(422);
                            out.print(json.toJson(errors));
                        }
                    }
                    return;
                case "password":
                    id = Long.parseLong(request.getParameter("id"));
                    user = UserFacade.find(id);
                    if (user == null) {
                        response.setStatus(422);
                        errors.add(new ValError("error", "Usuário não encontrado para atualização"));
                        out.print(json.toJson(errors));
                        return;
                    }
                    user = UserFacade.authenticate(user.getEmail(), request.getParameter("password"));
                    if (user == null) {
                        response.setStatus(422);
                        errors.add(new ValError("error", "Senha atual não confere"));
                        out.print(json.toJson(errors));
                        return;
                    }
                    user.setPassword(request.getParameter("password1"));
                    errors = user.validate();
                    errors.removeIf(error -> !error.getField().equals("password1"));
                    if (user.getPassword() != null && !user.getPassword().equals(request.getParameter("password2")))
                        errors.add(new ValError("password2", "As senhas não conferem"));
                    if (errors.isEmpty()) {
                        UserFacade.updatePassword(user);
                        out.print(json.toJson(user));
                    } else {
                        response.setStatus(422);
                        out.print(json.toJson(errors));
                    }
                    return;
            }
        } catch (NumberFormatException ex) {
            response.setStatus(422);
            out.print(json.toJson(new ValError("error", "ID de usuário não informada")));
            return;
        } finally {
            out.close();
        }
        response.sendError(404);
    }

    private void extractData(HttpServletRequest request, User user) {
        Address address = new Address();
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        if (user.getId() == null)
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
