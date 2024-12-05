package com.example.planme.ui.models;

public class NoteUI extends EntityUI {
    private String date;
    private String shortContent;
    private String title;

    public NoteUI(String id, String date, String shortContent, String title) {
        super.setId(id);
        this.date = date;
        this.shortContent = shortContent;
        this.title = title;
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
