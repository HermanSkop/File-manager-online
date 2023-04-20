package com.example.filemanager.servlets;

import com.example.filemanager.AccountManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "bLoginServlet", value = "/blogin-servlet")
public class BasicLoginServlet extends Servlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (isValidUser(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", "admin");
            resp.sendRedirect("protected/main-folder");
        } else sendError(req.getSession(), resp, "Access denied");
    }

    private boolean isValidUser(String username, String password){
        return AccountManager.isCorrectPassword(username, password);
    }
}
