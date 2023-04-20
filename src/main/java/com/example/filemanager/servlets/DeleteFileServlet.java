package com.example.filemanager.servlets;

import com.example.filemanager.AccountManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "deleteFile", value = "/protected/delete-file")
public class DeleteFileServlet extends Servlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getParameter("file-name");
        HttpSession session = request.getSession();

        File file = new File(this.getServletContext().getRealPath("/Folder") + "\\" + filename);
        if (!session.getAttribute("username").equals(AccountManager.getAuthor(filename.split("\\.")[0])))
            sendError(session, response, "Only authors can delete their files.");
        else {
            if (file.exists()) {
                if (file.delete()) {
                    AccountManager.deleteAuthorDependency(filename.split("\\.")[0]);
                    response.sendRedirect("main-folder");
                } else {
                    sendError(session,response,"An error occurred while deleting a file. Try later.");
                }
            } else {
                sendError(session,response,"File does not exist.");
            }
        }
    }

}
