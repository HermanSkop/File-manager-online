package com.example.filemanager.servlets;

import com.example.filemanager.PageConstructor;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "fillFile", value = "/protected/fill-file")
public class FillFileServlet extends Servlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        print(response.getWriter());
    }

    private void print(PrintWriter out){
        PageConstructor pc = new PageConstructor(this.getServletContext().getRealPath("/") + "/WEB-INF/classes/addFile.html");
        out.println(pc);
    }
}
