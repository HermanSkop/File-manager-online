package com.example.filemanager;

public class FileException extends Exception{
    private final String message;

    public FileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
