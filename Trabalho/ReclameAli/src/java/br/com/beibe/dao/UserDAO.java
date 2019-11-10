package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import br.com.beibe.beans.Address;
import br.com.beibe.beans.User;
import br.com.beibe.config.AccessRole;
import br.com.beibe.service.ConnectionFactory;
import br.com.beibe.utils.Security;

public abstract class UserDAO extends DAO {

    public static final String TABLE = "users";
    public static enum Fields {
        ID("id"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        CPF("cpf"),
        EMAIL("email"),
        DATE_BIRTH("date_birth"),
        PHONE("phone"),
        ROLE("role_id"),
        PASSWORD("password");

        private String fieldName;

        private Fields(String fieldName) {
            this.fieldName = fieldName;
        }

        public static String[] toArray() {
            return Stream.of(values()).map(Fields::name).toArray(String[]::new);
        }

        @Override
        public String toString() {
            return this.fieldName;
        }
    }

    private static User extractData(ResultSet rs, Connection conn) throws SQLException {
        Address address = AddressDAO.find(rs.getLong(Fields.ID.toString()), conn);
        Map<Long, Address> addressMap = new HashMap<>();
        if (address != null)
            addressMap.put(address.getId(), address);
        return extractData(rs, addressMap);
    }

    private static User extractData(ResultSet rs, Map<Long, Address> addresses) throws SQLException {
        return new User(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.FIRST_NAME.toString()),
            rs.getString(Fields.LAST_NAME.toString()),
            rs.getString(Fields.CPF.toString()),
            rs.getString(Fields.EMAIL.toString()),
            rs.getDate(Fields.DATE_BIRTH.toString()),
            rs.getString(Fields.PHONE.toString()),
            addresses.get(rs.getLong(Fields.ID.toString())),
            AccessRole.of(rs.getInt(Fields.ROLE.toString())),
            rs.getString(Fields.PASSWORD.toString())
        );
    }

    public static List<User> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<User> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        Map<Long, Address> addresses = AddressDAO.getMap(conn);
        while (rs.next()) {
            users.add(extractData(rs, addresses));
        }
        return users;
    }

    public static Map<Long, User> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Map<Long, User> users = new HashMap<>();
            getList(conn).forEach(user -> users.put(user.getId(), user));;
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static User find(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(id, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

	protected static User find(Long id, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray(),  "WHERE " + Fields.ID + " = ?"));
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractData(rs, conn);
        }
        return null;
	}

    public static void insert(User user) throws SQLException {
        Connection conn = ConnectionFactory.getConnection(false);
        try {
            PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray(), true));
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getCpf());
            stmt.setString(4, user.getEmail());
            if (user.getDateBirth() != null)
                stmt.setDate(5, new Date(user.getDateBirth().getTime()));
            else
                stmt.setNull(5, Types.NULL);
            stmt.setString(6, user.getPhone());
            stmt.setInt(7, user.getRole().getId());
            user.setPassword(Security.encryptPassword(user.getPassword()));
            stmt.setString(8, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user.setId(rs.getLong("id"));
            if (user.getAddress() == null)
                user.setAddress(new Address());
            user.getAddress().setId(user.getId());
            AddressDAO.insert(user.getAddress(), conn);
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }
}
