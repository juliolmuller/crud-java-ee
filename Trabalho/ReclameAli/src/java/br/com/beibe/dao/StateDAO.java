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
import br.com.beibe.beans.State;
import br.com.beibe.service.ConnectionFactory;

public abstract class StateDAO extends DAO {

    public static final String TABLE = "states";
    public static enum Fields {
        ID("id"),
        NAME("name"),
        ABREV("abrev");

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

    private static State extractData(ResultSet rs) throws SQLException {
        return new State(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.NAME.toString()),
            rs.getString(Fields.ABREV.toString())
        );
    }

    public static List<State> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<State> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<State> states = new ArrayList<>();
        while (rs.next()) {
            states.add(extractData(rs));
        }
        return states;
    }

    public static Map<Long, State> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getMap(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static Map<Long, State> getMap(Connection conn) throws SQLException {
        Map<Long, State> states = new HashMap<>();
        getList(conn).forEach(state -> states.put(state.getId(), state));
        return states;
    }

    public static State find(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(id, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

	protected static State find(Long id, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray(),  "WHERE " + Fields.ID + " = ?"));
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractData(rs);
        }
        return null;
	}
}
