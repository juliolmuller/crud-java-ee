package br.com.beibe.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.beibe.service.ConnectionFactory;

@WebServlet(name = "ResetDatabaseServlet", urlPatterns = {"/reset-database"})
public class ResetDatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        StringBuilder sql = new StringBuilder();
        try (Scanner scn = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/db_all.sql"), "UTF-8")) {
            while (scn.hasNextLine()) {
                sql.append(scn.nextLine()).append("\n");
            }
        }
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.createStatement().executeUpdate(sql.toString());
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
