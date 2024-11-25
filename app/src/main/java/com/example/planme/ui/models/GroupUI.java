package com.example.planme.ui.models;

public class GroupUI extends EntityUI {
    private String name;
    private String description;
    private String date;
    private String contentLastMessage;
    private String dateLastMessage;
    private String urlImg;
    private String code;

    public GroupUI(){
        this.name = "";
        this.description = "";
        this.date = "";
        this.contentLastMessage = "";
        this.dateLastMessage = "";
        this.urlImg = "";
        this.code = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupUI(String name, String description, String contentLastMessage,String dateLastMessage, String date) {
        this.name = name;
        this.description = description;
        this.contentLastMessage = contentLastMessage;
        this.dateLastMessage = dateLastMessage;
        this.date = date;
        this.urlImg = "";
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

    public String getContentLastMessage() {
        return contentLastMessage;
    }

    public void setContentLastMessage(String lastMessage) {
        this.contentLastMessage = lastMessage;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getDateLastMessage() {
        return dateLastMessage;
    }

    public void setDateLastMessage(String dateLastMessage) {
        this.dateLastMessage = dateLastMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
