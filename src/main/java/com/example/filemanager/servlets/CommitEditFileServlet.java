package com.example.filemanager.servlets;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "commitEditFile", value = "/protected/commit-file")
public class CommitEditFileServlet extends Servlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String content = request.getParameter("content");
        String filename = request.getParameter("file-name");

        File file = new File(this.getServletContext().getRealPath("/Folder") + "\\" + filename);
        editFile(file, content);

        response.sendRedirect("main-folder");
    }
    private void editFile(File file, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(content);
        fileWriter.close();
    }
}
