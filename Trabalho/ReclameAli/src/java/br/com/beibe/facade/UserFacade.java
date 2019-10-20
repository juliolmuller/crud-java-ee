package br.com.beibe.facade;

import java.util.ArrayList;
import java.util.List;
import br.com.beibe.beans.User;
import java.sql.SQLException;

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

    public static void save(User user) throws SQLException {
        // Implementação pendente
    }
}
