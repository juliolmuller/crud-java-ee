package br.com.beibe.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

    private static final String PROPS_FILE = "/db.properties";
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConnectionFactory.class.getResourceAsStream(PROPS_FILE)) {
            PROPS.load(is);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ConnectionFactory() {}

    public static Connection getConnection(boolean autoCommit) throws SQLException {
        String dbUrl = PROPS.getProperty("DB_URL")
            + PROPS.getProperty("DB_HOST") + ":"
            + PROPS.getProperty("DB_PORT") + "/"
            + PROPS.getProperty("DB_SCHEMA");
        String dbUser = PROPS.getProperty("DB_USER");
        String dbPassword = PROPS.getProperty("DB_PASSWORD");
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        conn.setAutoCommit(autoCommit);
        return conn;
    }
}
