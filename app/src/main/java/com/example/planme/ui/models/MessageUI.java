package com.example.planme.ui.models;

public class MessageUI {
    private String id;
    private String content;
    private String date;
    private String userId;
    private String groupId;
    public MessageUI(String id, String content, String date, String userId, String groupId) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
