package br.com.beibe.facade;

import java.sql.SQLException;
import java.util.List;
import br.com.beibe.beans.User;
import br.com.beibe.beans.ValError;
import br.com.beibe.dao.UserDAO;

public final class UserFacade {

    private UserFacade() {}

    public static User authenticate(String login, String password) {
        return UserDAO.authenticate(login, password);
    }

    public static List<ValError> validate(User user) {
        return user.validate();
    }

    public static void save(User user) throws SQLException {
        if (user.getId() == null)
            UserDAO.insert(user);
        // FIXME: UserDAO.update(user);
    }
}
