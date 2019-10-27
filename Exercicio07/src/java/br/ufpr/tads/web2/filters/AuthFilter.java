package br.ufpr.tads.web2.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
    "/jsp/*",
    "/api/*",
    "/logout",
    "/portal.jsp",
    "/usuarios/*",
    "/clientes/*"
})
public class AuthFilter implements Filter {


    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        // Converter instâncias para HttpServlet*
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Se autorizado, processar a requisição
        if (req.getSession().getAttribute("login") != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("msg", "Área restrita. Efetue o login!");
            req.setAttribute("cor", "danger");
            res.setStatus(403);
            req.getRequestDispatcher("/index.jsp").forward(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
