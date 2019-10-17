package br.com.beibe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class GerenteController {

    private static GerenteController classInstance;

    public static GerenteController getInstance() {
        if (classInstance == null)
            classInstance = new GerenteController();
        return classInstance;
    }

    public void displayHome(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

    public void displayTickets(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-index.jsp").forward(request, response);
    }

    public void displayExistingTicketForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/tickets-form.jsp").forward(request, response);
    }

    public void displayUsers(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/users-index.jsp").forward(request, response);
    }

    public void displayUsersForm(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/users-form.jsp").forward(request, response);
    }

    public void generateReport(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gerente");
    }

    public void processNewUser(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gerente/colaboradores");
    }

    public void processExistingUser(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gerente/colaboradores");
    }

    public void deleteUser(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/gerente/colaboradores");
    }
}
