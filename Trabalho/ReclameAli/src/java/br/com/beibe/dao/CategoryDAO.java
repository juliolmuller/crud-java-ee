package br.com.beibe.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import br.com.beibe.beans.Category;
import br.com.beibe.service.ConnectionFactory;

public abstract class CategoryDAO extends DAO {

    public static final String TABLE = "categories";
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

    private static Category extractData(ResultSet rs) throws SQLException {
        return new Category(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.NAME.toString())
        );
    }

    public static List<Category> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<Category> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractData(rs));
        }
        return categories;
    }

    public static Map<Long, Category> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Map<Long, Category> categories = new HashMap<>();
            getList(conn).forEach(category -> categories.put(category.getId(), category));
            return categories;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Category find(Long id) {
        return find(id, Fields.ID);
    }

    public static Category find(Object value, Fields field) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(value, field, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static Category find(Object value, Fields field, Connection conn) throws SQLException {
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
            return extractData(rs);
        }
        return null;
    }



    public static void insert(Category category) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray(), true));
            stmt.setString(1, category.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                category.setId(rs.getLong(Fields.ID.toString()));
        }
    }

    public static void update(Category category) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(buildUpdateQuery(TABLE, Fields.toArray(), Fields.ID.toString()));
            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(Long id) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(buildDeleteQuery(TABLE, Fields.ID.toString()));
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
