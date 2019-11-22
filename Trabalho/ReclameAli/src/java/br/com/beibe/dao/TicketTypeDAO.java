package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;
import java.util.HashMap;
import br.com.beibe.beans.TicketType;
import br.com.beibe.service.ConnectionFactory;

public abstract class TicketTypeDAO extends DAO {

    public static final String TABLE = "ticket_types";
    public static enum Fields {
        ID("id"),
        NAME("name");

        private final String fieldName;

        private Fields(String fieldName) {
            this.fieldName = fieldName;
        }

        public static String[] toArray() {
            return Stream.of(values()).map(Fields::toString).toArray(String[]::new);
        }

        @Override
        public String toString() {
            return this.fieldName;
        }
    }

    private static TicketType extractData(ResultSet rs) throws SQLException {
        return new TicketType(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.NAME.toString())
        );
    }

    public static List<TicketType> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<TicketType> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<TicketType> types = new ArrayList<>();
        while (rs.next()) {
            types.add(extractData(rs));
        }
        return types;
    }

    public static Map<Long, TicketType> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getMap(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static Map<Long, TicketType> getMap(Connection conn) throws SQLException {
        Map<Long, TicketType> types = new HashMap<>();
        getList(conn).forEach(type -> types.put(type.getId(), type));
        return types;
    }

    public static TicketType find(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(id, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

	protected static TicketType find(Long id, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray(),  "WHERE " + Fields.ID + " = ?"));
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractData(rs);
        }
        return null;
	}
}
