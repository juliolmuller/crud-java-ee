package br.com.beibe.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

    private static final String PROPS_FILE = "/db.properties";
    private static Properties props;

    static {
        try (InputStream is = ConnectionFactory.class.getResourceAsStream(PROPS_FILE)) {
            props = new Properties();
            props.load(is);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        String dbUrl = props.getProperty("DB_URL")
            + props.getProperty("DB_HOST") + ":"
            + props.getProperty("DB_PORT") + "/"
            + props.getProperty("DB_SCHEMA");
        String dbUser = props.getProperty("DB_USER");
        String dbPassword = props.getProperty("DB_PASSWORD");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
