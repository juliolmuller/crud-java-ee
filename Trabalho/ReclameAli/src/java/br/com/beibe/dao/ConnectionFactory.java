package br.ufpr.tads.web2.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

    private static final String PROPS_FILE = "/db.props";
    private static Properties props = null;

    private ConnectionFactory() {}

    public static Connection getConnection() {
        try {
            if (props == null) {
                props = new Properties();
                props.load(ConnectionFactory.class.getResourceAsStream(PROPS_FILE));
            }
            Class.forName(props.getProperty("DB_DRIVER"));
            String dbUrl = props.getProperty("DB_URL")
                + props.getProperty("DB_HOST") + ":"
                + props.getProperty("DB_PORT") + "/"
                + props.getProperty("DB_SCHEMA");
            String dbUser = props.getProperty("DB_USER");
            String dbPassword = props.getProperty("DB_PASSWORD");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao ler as propriedades: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver de conexao com banco de dados nao encontrado: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Falha na conexao com banco de dados: " + e.getMessage());
        }
    }
}
