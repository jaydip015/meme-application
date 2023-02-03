package com.example.memeapplication;

public class row_model {
    String title;
    String link;
    String author;

    public row_model(String title, String link, String author) {
        this.title = title;
        this.link = link;
        this.author=author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
