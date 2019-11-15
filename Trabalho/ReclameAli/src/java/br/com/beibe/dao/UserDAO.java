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
        ROLE("role"),
        PASSWORD("password");

        private final String fieldName;

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
        Address address = AddressDAO.find(rs.getLong(Fields.ID.toString()), AddressDAO.Fields.ID, conn);
        Map<Long, Address> addressMap = new HashMap<>();
        if (address != null)
            addressMap.put(address.getId(), address);
        return extractData(rs, addressMap);
    }

    private static User extractData(ResultSet rs, Map<Long, Address> addresses) throws SQLException {
        User user = User.getInstanceOf(rs.getString(Fields.ROLE.toString()));
        user.setId(rs.getLong(Fields.ID.toString()));
        user.setFirstName(rs.getString(Fields.FIRST_NAME.toString()));
        user.setLastName(rs.getString(Fields.LAST_NAME.toString()));
        user.setCpf(rs.getString(Fields.CPF.toString()));
        user.setEmail(rs.getString(Fields.EMAIL.toString()));
        user.setDateBirth(rs.getDate(Fields.DATE_BIRTH.toString()).toLocalDate());
        user.setPhone(rs.getString(Fields.PHONE.toString()));
        user.setPassword(rs.getString(Fields.PASSWORD.toString()));
        user.setAddress(addresses.get(rs.getLong(Fields.ID.toString())));
        return user;
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
            getList(conn).forEach(user -> users.put(user.getId(), user));
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static User find(Long id) {
        return find(id, Fields.ID);
    }

    public static User find(Object value, Fields field) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(value, field, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

	protected static User find(Object value, Fields field, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray(),  "WHERE " + field + " = ?"));
        if (value instanceof String)
            stmt.setString(1, (String) value);
        else if (value instanceof Long)
            stmt.setLong(1, (Long) value);
        else if (value instanceof Integer)
            stmt.setInt(1, (Integer) value);
        else if (value instanceof java.util.Date)
            stmt.setDate(1, new Date(((java.util.Date) value).getTime()));
        else
            throw new SQLException("Type cannot be set to the prepared statement");
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
                stmt.setDate(5, Date.valueOf((user.getDateBirth())));
            else
                stmt.setNull(5, Types.NULL);
            stmt.setString(6, user.getPhone());
            stmt.setString(7, user.getRole());
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
