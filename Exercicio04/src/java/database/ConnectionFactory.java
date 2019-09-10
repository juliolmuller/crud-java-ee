package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPS_FILE = "/database/db.properties";

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            props.load(ConnectionFactory.class.getResourceAsStream(PROPS_FILE));
            String dbUrl = props.getProperty("db.url")
                + props.getProperty("db.host") + ":"
                + props.getProperty("db.port") + "/"
                + props.getProperty("db.schema");
            String dbUser = props.getProperty("db.user");
            String dbPassword = props.getProperty("db.password");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read database properties!");
        } catch (SQLException ex) {
            throw new RuntimeException("Connection to database failed!");
        }
    }
}
