package com.example.planme.data.models;

public class Message extends  Entity{
    private String content;
    private String date;
    private String userId;
    private String groupId;
    private String urlImg;
    private String userName;

    public Message(){
        this.id = "";
        this.content = "";
        this.date = "";
        this.userId = "";
        this.groupId = "";
        this.urlImg = "";
        this.userName = "";
    }

    public Message(String id, String content, String date, String userId, String groupId, String urlImg, String userName) {
        super(id);
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.groupId = groupId;
        this.urlImg = urlImg;
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
