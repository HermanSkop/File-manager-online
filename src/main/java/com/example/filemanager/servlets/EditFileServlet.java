package com.example.filemanager.servlets;

import com.example.filemanager.AccountManager;
import com.example.filemanager.PageConstructor;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

@WebServlet(name = "editFile", value = "/protected/edit-file")
public class EditFileServlet extends Servlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getParameter("file-name");
        HttpSession session = request.getSession();

        File file = new File(this.getServletContext().getRealPath("/Folder") + "\\" + filename);

        if (!session.getAttribute("username").equals(AccountManager.getAuthor(filename.split("\\.")[0])))
            sendError(session, response, "Only authors can edit their files.");
        else {
            if (file.exists())
                print(response.getWriter(), filename, readFile(file));
            else
                sendError(session, response, "File does not exist.");
        }
    }
    private void print(PrintWriter out, String filename, String content){
        PageConstructor pc = new PageConstructor(this.getServletContext().getRealPath("/") + "/WEB-INF/classes/editFile.html");
        pc.insertFile(filename, content);
        out.println(pc);
    }

    public String readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
