package com.example.filemanager.servlets;

import com.example.filemanager.PageConstructor;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "error", value = {"/protected/error", "/error"})
public class ErrorServlet extends Servlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if(session != null && session.getAttribute("error") != null)
            print(response.getWriter(), (String) session.getAttribute("error"));
        else
            response.sendRedirect("../index.jsp");
    }

    private void print(PrintWriter out, String message){
        PageConstructor pc = new PageConstructor(this.getServletContext().getRealPath("/") + "/WEB-INF/classes/error.html");
        pc.editMessage(message);
        out.println(pc);
    }
}
