package br.com.beibe.facade;

import java.util.ArrayList;
import java.util.List;
import br.com.beibe.beans.Usuario;
import java.sql.SQLException;

public final class UsuarioFacade {

    private UsuarioFacade() {}

    public static Usuario authenticate(String login, String password) {
        // Implementação pendente
        return null;
    }

    public static List<String> validate(Usuario user) {
        // Implementação pendente
        return new ArrayList<>();
    }

    public static void save(Usuario user) throws SQLException {
        // Implementação pendente
    }
}
