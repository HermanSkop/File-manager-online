package com.example.filemanager.servlets;

import com.example.filemanager.PageConstructor;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class Servlet extends HttpServlet {
    String pagePath;

    private void print(PrintWriter out){
        out.println( new PageConstructor(pagePath));
    }

    protected void sendError(HttpSession session, HttpServletResponse response, String message) throws IOException {
        if(session != null){
            session.setAttribute("error", message);
            response.sendRedirect("error");
        }
        else response.sendRedirect("index.jsp");
    }
}
