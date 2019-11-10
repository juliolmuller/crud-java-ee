package br.com.beibe.dao;

import java.util.stream.Stream;

public abstract class DAO {

    protected static String buildSelectQuery(String tableName, String[] columns) {
        return buildSelectQuery(tableName, columns, "");
    }

    protected static String buildSelectQuery(String tableName, String[] columns, String closure) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(String.join(", ", columns));
        sb.append(" FROM ").append(tableName);
        if (!closure.isEmpty())
            sb.append(" ").append(closure);
        return sb.toString();
    }

    protected static String buildInsertQuery(String tableName, String[] columns) {
        return buildInsertQuery(tableName, columns, false);
    }

    protected static String buildInsertQuery(String tableName, String[] columns, boolean returningId) {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(tableName).append("(");
        sb.append(String.join(", ", Stream.of(columns).filter(column -> !column.equals("id")).toArray(String[]::new)));
        sb.append(") VALUES(");
        sb.append(String.join(", ", Stream.of(new String[columns.length]).map(q -> "?").toArray(String[]::new)));
        sb.append(")");
        if (returningId)
            sb.append(" RETURNING id");
        return sb.toString();
    }

    protected static String buildUpdateQuery(String tableName, String[] columns, String idColumn) {
        StringBuilder sb = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        sb.append(String.join(", ", Stream.of(String.join(", ", Stream.of(columns).filter(column -> !column.equals(idColumn)).toArray(String[]::new))).map(column -> column + " = ?").toArray(String[]::new)));
        sb.append(" WHERE ").append(idColumn).append(" = ?");
        return sb.toString();
    }

    protected static String buildDeleteQuery(String tableName, String idColumn) {
        StringBuilder sb = new StringBuilder("DELETE FROM ").append(tableName);
        sb.append(" WHERE ").append(idColumn).append(" = ?");
        return sb.toString();
    }
}
