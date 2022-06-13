package com.cg.controller;

import com.cg.model.User;
import com.cg.service.IUserService;
import com.cg.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {

    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "logOut":
                logout(request, response);
                break;
            case "signUp":
                break;
            case "showUsers":
                break;
            default:
                showSignInForm(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "signUp":
                break;
            default:
                signIn(request, response);
                break;
        }
    }

    private void showSignInForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
        String operation = null;

        if (this.getServletContext().getAttribute("operation") != null)
            operation = (String) this.getServletContext().getAttribute("operation");
        request.setAttribute("operation", operation);
        this.getServletContext().removeAttribute("operation");

        dispatcher.forward(request, response);
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");

        List<String> errorMessages = new ArrayList<>();
        String operation;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!userService.exist(username) || !userService.checkPassword(username, password)) {
            errorMessages.add("Wrong username or password. Please try again.");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("errorMessages", errorMessages);
            dispatcher.forward(request, response);
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", userService.getRole(username));

        operation = "Hello, " + username + "!";
        session.setAttribute("operation", operation);
        response.sendRedirect("/products");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        String operation = "Have a good day";
        getServletContext().setAttribute("operation", operation);
        response.sendRedirect("/users");
    }

    private void showUser(HttpServletRequest request, HttpServletResponse response) {

    }
}
