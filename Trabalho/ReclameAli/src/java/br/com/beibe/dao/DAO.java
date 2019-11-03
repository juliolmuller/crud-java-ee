package br.com.beibe.dao;

public abstract class DAO {

    protected static String buildSelectQuery(String tableName, String[] columns) {
        return buildSelectQuery(tableName, columns, "");
    }
    protected static String buildSelectQuery(String tableName, String[] columns, String closure) {
        int colCount = columns.length;
        StringBuilder sb = new StringBuilder("SELECT ");
        for (int i = 0; i < colCount; i++) {
            sb.append(columns[i]);
            if (i < colCount - 1) {
                sb.append(", ");
            }
        }
        sb.append(" FROM ").append(tableName).append(" ").append(closure);
        return sb.toString();
    }

    protected static String buildInsertQuery(String tableName, String[] columns, boolean withReturn) {
        int colCount = columns.length;
        StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + "(");
        for (int i = 0; i < colCount; i++) {
            sb.append(columns[i]);
            if (i < colCount - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES(");
        for (int i = 0; i < colCount; i++) {
            sb.append("?");
            if (i < colCount - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        if (withReturn) sb.append(" RETURNING id");
        return sb.toString();
    }

    protected static String buildUpdateQuery(String tableName, String[] columns, long id) {
        int colCount = columns.length;
        StringBuilder sb = new StringBuilder("UPDATE " + tableName + " SET ");
        for (int i = 0; i < colCount; i++) {
            sb.append(columns[i]);
            sb.append(" = ?");
            if (i < colCount - 1) {
                sb.append(", ");
            }
        }
        sb.append(" WHERE id = ?");
        return sb.toString();
    }
}
