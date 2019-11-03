package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import br.com.beibe.service.ConnectionFactory;
import br.com.beibe.beans.State;

public final class StateDAO extends DAO {

    private static final String STATE_TABLE = "states";
    private static final String[] STATE_COLUMNS = { "id", "name", "abrev" };

    private StateDAO() {}

    public static List<State> getAll() {
        try (Connection conn = ConnectionFactory.getConnection(true)) {
            List<State> states = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(buildSelectQuery(STATE_TABLE, STATE_COLUMNS));
            while (rs.next()) {
                State state = new State();
                state.setId(rs.getLong("id"));
                state.setName(rs.getString("name"));
                state.setAbrev(rs.getString("abrev"));
                states.add(state);
            }
            return states;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
