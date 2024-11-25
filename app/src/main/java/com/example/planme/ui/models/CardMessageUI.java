package com.example.planme.ui.models;


public class CardMessageUI {
    private String id;
    private String content;
    private String date;
    private String userId;
    private String urlImg;
    private String userSessionId;
    private String userName;

    public CardMessageUI(String id, String content, String date, String userId, String urlImg, String userSessionId, String userName) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.urlImg = urlImg;
        this.userSessionId = userSessionId;
        this.userName = userName;
    }
    public boolean isMe(){
        return this.userId.equals(this.userSessionId);
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

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CardMessageUI that = (CardMessageUI) obj;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
