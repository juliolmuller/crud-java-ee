package br.com.beibe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "AppConfig", loadOnStartup = 1)
public class AppConfigServlet extends HttpServlet {

    private static final String PROPS_FILE = "/app.properties";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (InputStream is = this.getClass().getResourceAsStream(PROPS_FILE)) {
            Properties props = new Properties();
            props.load(is);
            props.forEach((key, prop) -> config.getServletContext().setAttribute((String) key, (String) prop));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
