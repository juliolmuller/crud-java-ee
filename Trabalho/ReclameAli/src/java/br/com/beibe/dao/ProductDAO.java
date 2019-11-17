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
import br.com.beibe.beans.Category;
import br.com.beibe.beans.Product;
import br.com.beibe.service.ConnectionFactory;

public abstract class ProductDAO extends DAO {

    public static final String TABLE = "products";
    public static enum Fields {
        ID("id"),
        NAME("name"),
        DESCRIPTION("description"),
        WEIGHT("weight"),
        UTC("utc_code"),
        EAN("ean_code"),
        CATEGORY("category_id");

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

    private static Product extractData(ResultSet rs, Connection conn) throws SQLException {
        Category category = CategoryDAO.find(rs.getLong(Fields.CATEGORY.toString()), conn);
        return new Product(
            rs.getLong(Fields.ID.toString()),
            rs.getString(Fields.NAME.toString()),
            rs.getString(Fields.DESCRIPTION.toString()),
            rs.getDouble(Fields.WEIGHT.toString()),
            rs.getString(Fields.UTC.toString()),
            rs.getString(Fields.EAN.toString()),
            category
        );
    }

    public static List<Product> getList() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return getList(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static List<Product> getList(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(buildSelectQuery(TABLE, Fields.toArray()));
        ResultSet rs = stmt.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(extractData(rs, conn));
        }
        return products;
    }

    public static Map<Long, Product> getMap() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Map<Long, Product> products = new HashMap<>();
            getList(conn).forEach(product -> products.put(product.getId(), product));
            return products;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Product find(Long id) {
        return find(id, Fields.ID);
    }

    public static Product find(Object value, Fields field) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return find(value, field, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static Product find(Object value, Fields field, Connection conn) throws SQLException {
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

    public static void insert(Product product) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(TABLE, Fields.toArray(), true));
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getWeight());
            stmt.setString(4, product.getUtc());
            stmt.setString(5, product.getEan());
            if (product.getCategory() != null)
                stmt.setLong(6, product.getCategory().getId());
            else
                stmt.setNull(6, Types.NULL);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                product.setId(rs.getLong(Fields.ID.toString()));
        }
    }

    public static void update(Product product) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(buildUpdateQuery(TABLE, Fields.toArray(), Fields.ID.toString()));
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getWeight());
            stmt.setString(4, product.getUtc());
            stmt.setString(5, product.getEan());
            if (product.getCategory() != null)
                stmt.setLong(6, product.getCategory().getId());
            else
                stmt.setNull(6, Types.NULL);
            stmt.setLong(7, product.getId());
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
