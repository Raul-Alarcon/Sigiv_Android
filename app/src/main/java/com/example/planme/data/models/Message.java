package com.example.planme.data.models;

import java.util.Date;

public class Message extends  Entity{
    private String content;
    private Date date;
    private final User user;

    public Message(String id, User user, Date date) {
        super(id);
        this.user = user;
    }

    public User getUser() {return this.user; }
    public String getContent() { return this.content; }
    public Date getDate() { return this.date; }
}
