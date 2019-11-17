package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.User;
import br.com.beibe.beans.ValError;
import br.com.beibe.dao.UserDAO;

public abstract class UserFacade {

    public static User authenticate(String login, String password) {
        return UserDAO.authenticate(login, password);
    }

    public static List<User> listEmployees() {
        List<User> users = UserDAO.getList();
        users.removeIf(user -> user.getRole().equals("cliente"));
        return users;
    }

    public static User find(Long id) {
        return UserDAO.find(id);
    }

    public static void save(User user) {
        try {
            if (user.getId() == null)
                UserDAO.insert(user);
            else
                UserDAO.update(user);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void UpdatePassword(User user) {
        try {
            UserDAO.updatePassword(user);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean delete(User user) {
        try {
            UserDAO.delete(user.getId());
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static List<Ticket> listTickets(User user) {
        return new ArrayList<>(); // TODO: implement TicketDAO
    }
}
