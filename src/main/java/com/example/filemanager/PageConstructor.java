package com.example.filemanager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class PageConstructor {
    private final String path;
    private Document doc;

    public PageConstructor(String path) {
        this.path = path;
        try {
            doc = Jsoup.parse(new File(path), "UTF-8");
        } catch(IOException e) {
            System.out.println("Something went wrong while opening " + path);
            System.exit(1);
        }
    }


    public String getPath() {
        return path;
    }

    public Document getDoc() {
        return doc;
    }

    public void addFile(String path) throws IOException {

        Element btnCont = new Element(Tag.valueOf("div"), "");
        Element hoverBtns = new Element(Tag.valueOf("div"), "");

        Element filename = new Element("a");
        Element author = new Element("a");
        List<String> pathParts = new ArrayList<>(Arrays.asList(path.split(Pattern.quote("/"))));

        String fileName = pathParts.get(pathParts.size()-1);
        path = "../Folder/" + fileName;

        String username = AccountManager.getAuthor(fileName.split("\\.")[0]);

        filename.text(fileName);
        filename.attr("href", path);

        author.attr("id", "author");
        author.text(username);

        btnCont.addClass("button-container");
        hoverBtns.addClass("hover-buttons");

        Element editForm = new Element(Tag.valueOf("form"), "");
        Element deleteForm = new Element(Tag.valueOf("form"), "");

        editForm.attr("method", "post");
        deleteForm.attr("method", "post");
        editForm.attr("action", "edit-file");
        deleteForm.attr("action", "delete-file");

        Element hiddenEditFile = new Element(Tag.valueOf("input"), "");
        Element hiddenEditAuthor = new Element(Tag.valueOf("input"), "");
        Element hiddenDeleteFile = new Element(Tag.valueOf("input"), "");
        Element hiddenDeleteAuthor = new Element(Tag.valueOf("input"), "");

        hiddenEditFile.attr("type", "hidden");
        hiddenDeleteFile.attr("type", "hidden");
        hiddenEditAuthor.attr("type", "hidden");
        hiddenDeleteAuthor.attr("type", "hidden");

        hiddenEditFile.attr("name", "file-name");
        hiddenDeleteFile.attr("name", "file-name");
        hiddenEditAuthor.attr("name", "author");
        hiddenDeleteAuthor.attr("name", "author");

        hiddenEditFile.attr("value", fileName);
        hiddenDeleteFile.attr("value", fileName);
        hiddenEditAuthor.attr("value", username);
        hiddenDeleteAuthor.attr("value", username);

        Element editBtn = new Element(Tag.valueOf("input"), "");
        Element deleteBtn = new Element(Tag.valueOf("input"), "");

        editBtn.attr("type", "submit");
        deleteBtn.attr("type", "submit");
        editBtn.attr("name", "action");
        deleteBtn.attr("name", "action");
        editBtn.attr("value", "edit");
        deleteBtn.attr("value", "delete");

        deleteForm.appendChild(hiddenDeleteFile);
        deleteForm.appendChild(hiddenDeleteAuthor);
        deleteForm.appendChild(deleteBtn);

        editForm.appendChild(hiddenEditFile);
        editForm.appendChild(hiddenEditAuthor);
        editForm.appendChild(editBtn);

        hoverBtns.appendChild(author);
        hoverBtns.appendChild(editForm);
        hoverBtns.appendChild(deleteForm);

        btnCont.appendChild(filename);
        btnCont.appendChild(hoverBtns);

        Objects.requireNonNull(doc.getElementById("file-list")).appendChild(btnCont);
    }

    public void editMessage(String message){
        Objects.requireNonNull(doc.getElementById("message")).text(message);
    }

    public void insertFile(String filename, String content){
        Objects.requireNonNull(doc.getElementById("filename")).attr("value", filename);
        Objects.requireNonNull(doc.getElementById("content")).text(content);
    }

    @Override
    public String toString() {
        return doc.html();
    }
}
