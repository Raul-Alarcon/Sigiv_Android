package com.example.planme.data.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group extends Entity{
    private String name;
    private String description;
    private String code;
    private final String date;
    private List<Message> messages;
    private List<User> users;

    public Group(){
        messages = new ArrayList<>();
        users = new ArrayList<>();
        date = (new Date()).toString();
        code = "NO CODE";
        description = "";
    }

    public Group(String id, String name, String description, List<Message> messages, List<User> users) {
        super(id);
        this.name = name;
        this.description = description;
        this.messages = messages;
        this.users = users;
        this.date = (new Date()).toString();
    }

    public String getName() { return  this.name;}
    public void setName(String name){
        this.name = name;
    }

    public String getDescription() { return  this.description; }
    public void setDescription(String description){
        this.description = description;
    }

    public String getDate() { return this.date; }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
