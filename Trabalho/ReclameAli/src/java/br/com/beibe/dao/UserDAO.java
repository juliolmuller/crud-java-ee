package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import br.com.beibe.service.ConnectionFactory;
import br.com.beibe.beans.User;

public final class UserDAO extends DAO {
    
    private static final String USER_TABLE = "users";
    private static final String[] USER_COLUMNS = {
        "first_name", "last_name", "cpf", "email",
        "date_birth", "phone", "role_id", "password"
    };
    private static final String ADDRESS_TABLE = "addresses";
    private static final String[] ADDRESS_COLUMNS = {
        "user_id", "zip_code", "street", "number",
        "complement", "neightborhood", "city", "state"
    };
    private static final String ROLES_TABLE = "roles";

    private UserDAO() {}
    
    public static User insert(User user) throws SQLException{
        Connection conn = ConnectionFactory.getConnection(false);
        try {
            PreparedStatement stmt;
            ResultSet rs;
            stmt = conn.prepareStatement(
                "SELECT id FROM " + ROLES_TABLE + " WHERE name = ?"
            );
            stmt.setString(1, user.getRole().toString());
            rs = stmt.executeQuery();
            if (!rs.next()) throw new SQLException("Unable to find role '" + user.getRole() + "'");
            long roleId = rs.getLong("id");
            stmt = conn.prepareStatement(buildInsertQuery(USER_TABLE, USER_COLUMNS, true));
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getCpf());
            stmt.setString(4, user.getEmail());
            stmt.setDate(5, new Date(user.getDateBirth().getTime()));
            stmt.setString(6, user.getPhone());
            stmt.setLong(7, roleId);
            stmt.setString(8, user.getPassword());
            rs = stmt.executeQuery();
            rs.next();
            long userId = rs.getLong("id");
            user.setId(userId);
            stmt = conn.prepareStatement(buildInsertQuery(ADDRESS_TABLE, ADDRESS_COLUMNS, false));
            stmt.setLong(1, userId);
            stmt.setString(2, user.getAddress().getZipCode());
            stmt.setString(3, user.getAddress().getStreet());
            stmt.setInt(4, user.getAddress().getNumber());
            stmt.setString(5, user.getAddress().getComplement());
            stmt.setString(6, user.getAddress().getNeightborhood());
            stmt.setString(7, user.getAddress().getCity());
            stmt.setString(8, user.getAddress().getState());
            stmt.executeUpdate();
            conn.commit();
            return user;
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }
}
