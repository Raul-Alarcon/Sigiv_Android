package com.example.planme.data.models;

public class Entity {
    protected String id;
    public Entity() {
    }
    public Entity(String id) {
        this.id = id;
    }
    public String getId(){ return this.id; }
    public void setId(String id){
        this.id = id;
    }

}
