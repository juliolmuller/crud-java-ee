package br.com.beibe.dao;

import java.sql.Connection;
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
import br.com.beibe.beans.State;

abstract class AddressDAO extends DAO {

    public static final String TABLE = "addresses";
    public static enum Fields {
        ID("user_id"),
        ZIP_CODE("zip_code"),
        STREET("street"),
        NUMBER("number"),
        COMPLEMENT("complement"),
        NEIGHTBORHOOD("neightborhood"),
        CITY("city"),
        STATE("state_id");

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

    private static Address extractData(ResultSet rs, Connection conn) throws SQLException {
        State state = StateDAO.find(rs.getLong(Fields.STATE.toString()), conn);
        Map<Long, State> stateMap = new HashMap<>();
        if (state != null)
            stateMap.put(state.getId(), state);
        return extractData(rs, stateMap);
    }

    private static Address extractData(ResultSet rs, Map<Long, State> states) throws SQLException {
        return new Address(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.ZIP_CODE.toString()),
            rs.getString(Fields.STREET.toString()),
            rs.getInt(Fields.NUMBER.toString()),
            rs.getString(Fields.COMPLEMENT.toString()),
            rs.getString(Fields.NEIGHTBORHOOD.toString()),
            rs.getString(Fields.CITY.toString()),
            states.get(rs.getLong(Fields.STATE.toString()))
        );
    }

    protected static List<Address> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<Address> addresses = new ArrayList<>();
        Map<Long, State> states = StateDAO.getMap(conn);
        while (rs.next()) {
            addresses.add(extractData(rs, states));
        }
        return addresses;
    }

    protected static Map<Long, Address> getMap(Connection conn) throws SQLException {
        Map<Long, Address> addresses = new HashMap<>();
        getList(conn).forEach(address -> addresses.put(address.getId(), address));
        return addresses;
    }

    protected static Address find(Object value, Fields field, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray(),  "WHERE " + field + " = ?"));
        if (value instanceof String)
            stmt.setString(1, (String) value);
        else if (value instanceof Long)
            stmt.setLong(1, (Long) value);
        else if (value instanceof Integer)
            stmt.setInt(1, (Integer) value);
        else
            throw new SQLException("Type cannot be set to the prepared statement");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractData(rs, conn);
        }
        return null;
    }

    protected static void insert(Address address, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray()));
        stmt.setLong(1, address.getId());
        stmt.setString(2, address.getZipCode());
        stmt.setString(3, address.getStreet());
        stmt.setInt(4, address.getNumber());
        stmt.setString(5, address.getComplement());
        stmt.setString(6, address.getNeightborhood());
        stmt.setString(7, address.getCity());
        if (address.getState() != null)
            stmt.setLong(8, address.getState().getId());
        else
            stmt.setNull(8, Types.NULL);
        stmt.executeUpdate();
    }
}
