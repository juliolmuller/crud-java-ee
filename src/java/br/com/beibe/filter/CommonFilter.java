package br.com.beibe.filter;

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
import br.com.beibe.beans.User;

@WebFilter(filterName = "CommonFilter", urlPatterns = {
    "/api/tickets/*",
    "/api/products/*"
})
public class CommonFilter implements Filter {

    public void doFilter(
        HttpServletRequest request, 
        HttpServletResponse response,
        FilterChain next
    ) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("userCredentials");
        if (user == null) {
            request.setAttribute("accessDenied", true);
            request.setAttribute("roleRequired", "usuário");
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
            response.setStatus(403);
        } else {
            next.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(
        ServletRequest request, 
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    @Override
    public void destroy() {}
}
