package br.com.beibe.facade;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import br.com.beibe.beans.User;
import br.com.beibe.dao.UserDAO;

public final class UserFacade {

    private UserFacade() {}

    public static User authenticate(String login, String password) {
        // Implementação pendente
        return null;
    }

    public static List<String> validate(User user) {
        // Implementação pendente
        return new ArrayList<>();
    }

    public static User save(User user) throws SQLException {
        return UserDAO.insert(user);
    }
}
