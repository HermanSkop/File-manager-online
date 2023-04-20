package com.example.filemanager.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "bLogoutServlet", value = "/protected/logout")
public class LogoutServlet extends Servlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect("../index.jsp");
    }
}
