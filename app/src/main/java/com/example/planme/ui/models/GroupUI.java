package com.example.planme.ui.models;

public class GroupUI extends EntityUI {
    private String name;
    private String description;
    private String date;
    private MessageUI lastMessage;

    public GroupUI(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupUI(String name, String description, MessageUI lastMessage, String date) {
        this.name = name;
        this.description = description;
        this.lastMessage = lastMessage;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MessageUI getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageUI lastMessage) {
        this.lastMessage = lastMessage;
    }
}
