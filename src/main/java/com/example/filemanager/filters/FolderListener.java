package com.example.filemanager.filters;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.File;

@WebListener
public class FolderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        jakarta.servlet.ServletContext context = event.getServletContext();
        String folderPath = context.getRealPath("/Folder");
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
