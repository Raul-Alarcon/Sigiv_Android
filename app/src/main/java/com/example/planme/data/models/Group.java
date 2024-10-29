package com.example.planme.data.models;

import java.util.ArrayList;

public class Group extends Entity{
    private String name;
    private String description;
    private final ArrayList<Message> messages;

    public Group(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        this.messages =  new ArrayList<>();
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

}
