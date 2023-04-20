package com.example.filemanager.servlets;

import com.example.filemanager.AccountManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signupServlet", value = "/signup")
public class SignupServlet extends Servlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("new-username");
        String password = request.getParameter("new-password");
        if(AccountManager.getAllUsers().contains(username)) {
            sendError(request.getSession(), response, "Such username already exists.");
        }
        else if(isValidPassword(password) && isValidUsername(username)) {
            try {
                AccountManager.addUser(username, password);
                response.sendRedirect("index.jsp");
            }
            catch (IOException e){
                sendError(request.getSession(), response, "Cannot create an account, try later.");
            }
        }
        else
            sendError(request.getSession(), response, "Input must not contain ':' and new line characters.");

    }
    private boolean isValidPassword(String password){
        return !password.contains(":") && !password.contains("\n");
    }
    private boolean isValidUsername(String username){
        return !username.contains(":") && !username.contains("\n");
    }
}
