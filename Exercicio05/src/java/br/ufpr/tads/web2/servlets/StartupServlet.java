
package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.ConfigBean;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigBean config = new ConfigBean();
        config.setEmailAdmin("the.boss@web2.ufpr.br");
        ServletContext ctx = getServletContext();
        ctx.setAttribute("configuracao", config);
    }
}
