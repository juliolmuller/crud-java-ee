package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import br.com.beibe.beans.Ticket;
import br.com.beibe.beans.TicketMessage;
import br.com.beibe.beans.TicketStatus;
import br.com.beibe.service.ConnectionFactory;
import java.time.LocalDateTime;

public abstract class TicketDAO extends DAO {

    public static final String TABLE = "tickets";
    public static enum Fields {
        ID("id"),
        OPENING("opening_date"),
        CLOSING("closing_date"),
        STATUS("status_id"),
        TYPE("type_id"),
        USER("created_by"),
        PRODUCT("product_id");

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

    private static Ticket extractData(ResultSet rs, Connection conn) throws SQLException {
        long id = rs.getLong(Fields.ID.toString());
        List<TicketMessage> messages = TicketMessageDAO.getList(TicketMessageDAO.Fields.TICKET, id, conn);
        Set<TicketMessage> messagesSet = new TreeSet<>(messages);
        Timestamp ts = rs.getTimestamp(Fields.CLOSING.toString());
        LocalDateTime ldt = ts != null ? ts.toLocalDateTime() : null;
        return new Ticket(
            id,
            rs.getTimestamp(Fields.OPENING.toString()).toLocalDateTime(),
            ldt,
            TicketStatus.ofId(rs.getInt(Fields.STATUS.toString())),
            TicketTypeDAO.find(rs.getLong(Fields.TYPE.toString()), conn),
            UserDAO.find(rs.getLong(Fields.USER.toString()), conn),
            ProductDAO.find(rs.getLong(Fields.PRODUCT.toString()), conn),
            messagesSet
        );
    }

    public static List<Ticket> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<Ticket> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<Ticket> tickets = new ArrayList<>();
        while (rs.next()) {
            tickets.add(extractData(rs, conn));
        }
        return tickets;
    }

    public static Map<Long, Ticket> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Map<Long, Ticket> tickets = new HashMap<>();
            getList(conn).forEach(ticket -> tickets.put(ticket.getId(), ticket));
            return tickets;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Ticket find(Long id) {
        return find(id, Fields.ID);
    }

    public static Ticket find(Object value, Fields field) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(value, field, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static Ticket find(Object value, Fields field, Connection conn) throws SQLException {
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

    public static void insert(Ticket ticket) throws SQLException {
        Connection conn = ConnectionFactory.getConnection(false);
        try {
            PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray(), true));
            stmt.setTimestamp(1, Timestamp.valueOf(ticket.getOpeningDate()));
            if (ticket.getClosingDate() != null)
                stmt.setTimestamp(2, Timestamp.valueOf(ticket.getClosingDate()));
            else
                stmt.setNull(2, Types.NULL);
            stmt.setLong(3, ticket.getStatus().getId());
            stmt.setLong(4, ticket.getType().getId());
            stmt.setLong(5, ticket.getOpenBy().getId());
            stmt.setLong(6, ticket.getProduct().getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            long id = rs.getLong("id");
            ticket.setId(id);
            ticket.getMessages().forEach(msg -> {
                msg.setId(id);
                try {
                    TicketMessageDAO.insert(msg, conn);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public static void update(Ticket ticket) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String[] mutable = { Fields.STATUS.toString(), Fields.TYPE.toString(), Fields.CLOSING.toString() };
            PreparedStatement stmt = conn.prepareStatement(buildUpdateQuery(TABLE, mutable, Fields.ID.toString()));
            stmt.setLong(1, ticket.getStatus().getId());
            stmt.setLong(2, ticket.getType().getId());
            if (ticket.getClosingDate() != null)
                stmt.setTimestamp(3, Timestamp.valueOf(ticket.getClosingDate()));
            else
                stmt.setNull(3, Types.NULL);
            stmt.setLong(4, ticket.getId());
            stmt.executeUpdate();
            conn.commit();
        }
    }
}
