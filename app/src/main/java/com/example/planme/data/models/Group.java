package com.example.planme.data.models;

import java.util.ArrayList;
import java.util.Date;

public class Group extends Entity{
    private String name;
    private String description;
    private String code;
    private final ArrayList<Message> messages;
    private final ArrayList<User> users;
    private final Date date;


    public Group(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        this.messages =  new ArrayList<>();
        this.users = new ArrayList<>();
        this.date = new Date();
    }

    public String getName() { return  this.name;}
    public void setName(String name){
        this.name = name;
    }

    public String getDescription() { return  this.description; }
    public void setDescription(String description){
        this.description = description;
    }

    public ArrayList<Message> getMessages() { return this.messages; }
    public void addMessage(Message message){
        this.messages.add(message);
    }

    public ArrayList<User> getUser() { return this.users; }
    public void setUser(User user){
        this.users.add(user);
    }
    public Date getDate() { return this.date; }

    public Message getLastMessage(){
        int index = !this.messages.isEmpty() ? this.messages.size() - 1 : -1;
        return index == -1 ? null : this.messages.get(index);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
