package com.example.filemanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;



public class AccountManager {
    private static final String passwordsPath = "passwords.txt";
    private static final String authorsPath = "authors.txt";

    public static LinkedList<String> getAllUsers(){
        File file = new File(passwordsPath);
        LinkedList<String> users = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(":");
                users.add(tokens[0]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + passwordsPath);
        }
        return users;
    }
    public static void addUser(String username, String password) throws IOException {
        FileWriter fileWriter = new FileWriter(passwordsPath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(username + ":" + password + "\n");
        bufferedWriter.close();
    }

    public static boolean isCorrectPassword(String username, String password){
        File file = new File(passwordsPath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(":");
                if(Objects.equals(tokens[0], username) && Objects.equals(tokens[1], password)) return true;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + passwordsPath);
        }
        return false;
    }

    public static void addAuthorDependency(String author, String filename) throws IOException, FileException {
        File file = new File(passwordsPath);
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(":");
                if(Objects.equals(tokens[0], filename)) throw new FileException(filename + " already exists.");
            }
            scanner.close();

            FileWriter fileWriter = new FileWriter(authorsPath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(filename + ":" + author + "\n");
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + passwordsPath);
        }
    }
    public static void deleteAuthorDependency(String filename){
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(authorsPath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.startsWith(filename + ":")) {
                    fileContent.append(currentLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(authorsPath))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAuthor(String filename) throws IOException{
        File file = new File(authorsPath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(":");
            if (Objects.equals(tokens[0], filename)) return tokens[1];
        }
        scanner.close();
        return "unknown";
    }

}
