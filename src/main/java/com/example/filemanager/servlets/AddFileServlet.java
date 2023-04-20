package com.example.filemanager.servlets;

import com.example.filemanager.AccountManager;
import com.example.filemanager.FileException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "addFile", value = "/protected/add-file")
public class AddFileServlet extends Servlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getParameter("filename");
        String content = request.getParameter("content");
        String username = (String) request.getSession().getAttribute("username");

        HttpSession session = request.getSession();
        session.setAttribute("new-file-name", filename);
        session.setAttribute("new-file-content", content);
        if(isValidFile(filename)){
            try {
                createFile(this.getServletContext(), username, filename, content);
                response.sendRedirect("main-folder");
            }
            catch (FileException e) {
                sendError(session, response, e.getMessage());
            }
        }
        else sendError(session, response, "Name field cannot be empty.");
    }

    private void createFile(ServletContext context, String author, String filename, String content) throws IOException, FileException {
        File file = new File( context.getRealPath("/Folder") + "\\" + filename + ".txt");

        if (file.createNewFile()) {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();

            AccountManager.addAuthorDependency(author, filename);
        } else
            throw new FileException("File already exists");
    }

    private boolean isValidFile(String filename) {
        return !Objects.equals(filename, "");
    }
}
