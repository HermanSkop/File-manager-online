package com.example.filemanager.servlets;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.example.filemanager.PageConstructor;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "folderServlet", value = "/protected/main-folder")
public class FolderServlet extends Servlet {
    private ServletContext context;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        context = this.getServletContext();
        PrintWriter out = response.getWriter();
        print(out);
        out.close();
    }
    private List<String> listResources()  {
        List<String> resources = new LinkedList<>();
        Set<String> res = context.getResourcePaths("/Folder");
        if(res==null) return resources;
        for (String resItem : res)
            if (!resItem.endsWith("/")) {
                resources.add(resItem);
            }
        return resources;
    }
    public void destroy() {}
    private void print(PrintWriter out) throws IOException {
        PageConstructor pc = new PageConstructor(context.getRealPath("/") + "/WEB-INF/classes/folder.html");
        for(String path: listResources()) {
            pc.addFile(path);
        }
        out.println(pc);
    }

}