package com.example.planme.data.models;

import java.util.Date;

public class Message extends  Entity{
    private String content;
    private String date;
    private String userId;
    private String groupId;

    public Message(){
        this.id = "";
        this.content = "";
        this.date = "";
        this.userId = "";
        this.groupId = "";
    }

    public Message(String id, String content, String date, String userId, String groupId) {
        super(id);
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.groupId = groupId;
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
}
