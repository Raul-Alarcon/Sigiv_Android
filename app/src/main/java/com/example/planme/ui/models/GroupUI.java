package com.example.planme.ui.models;

public class GroupUI extends EntityUI {
    private String name;
    private String description;

    public GroupUI(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupUI(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
