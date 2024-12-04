package com.example.planme.ui.models;

public class NoteUI {
    private String id;
    private String date;
    private String shortContent;
    private String title;

    public NoteUI(String id, String date, String shortContent, String title) {
        this.id = id;
        this.date = date;
        this.shortContent = shortContent;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
