package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import br.com.beibe.beans.TicketMessage;
import br.com.beibe.beans.User;
import br.com.beibe.service.ConnectionFactory;

public abstract class TicketMessageDAO extends DAO {

    public static final String TABLE = "ticket_messages";
    public static enum Fields {
        ID("id"),
        MESSAGE("message"),
        SENDER("user_id"),
        TICKET("ticket_id"),
        SEND_DATE("send_date");

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

    private static TicketMessage extractData(ResultSet rs, Connection conn) throws SQLException {
        User sender = UserDAO.find(rs.getLong(Fields.SENDER.toString()), conn);
        return new TicketMessage(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.MESSAGE.toString()),
            sender,
            rs.getLong(Fields.TICKET.toString()),
            rs.getTimestamp(Fields.SEND_DATE.toString()).toLocalDateTime()
        );
    }

    public static List<TicketMessage> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<TicketMessage> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<TicketMessage> messages = new ArrayList<>();
        while (rs.next()) {
            messages.add(extractData(rs, conn));
        }
        return messages;
    }

    public static Map<Long, TicketMessage> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Map<Long, TicketMessage> messages = new HashMap<>();
            getList(conn).forEach(message -> messages.put(message.getId(), message));
            return messages;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static TicketMessage find(Long id) {
        return find(id, Fields.ID);
    }

    public static TicketMessage find(Object value, Fields field) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(value, field, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static TicketMessage find(Object value, Fields field, Connection conn) throws SQLException {
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

    public static void insert(TicketMessage message) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            insert(message, conn);
        }
    }

    protected static void insert(TicketMessage message, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray(), true));
        stmt.setString(1, message.getMessage());
        stmt.setLong(2, message.getSender().getId());
        stmt.setLong(3, message.getTicket());
        stmt.setTimestamp(4, Timestamp.valueOf(message.getSendDate()));
        ResultSet rs = stmt.executeQuery();
        rs.next();
        message.setId(rs.getLong(Fields.ID.toString()));
    }
}
