package com.example.filemanager.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/protected/*", "/Folder/*"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if(session != null && session.getAttribute("username") != null)
            filterChain.doFilter(servletRequest, servletResponse);
        else sendError(session, res);
    }
    protected void sendError(HttpSession session, HttpServletResponse response) throws IOException {
        if(session != null){
            response.sendRedirect("../must-login.jsp");
        }
        else response.sendRedirect("../index.jsp");
    }
}
