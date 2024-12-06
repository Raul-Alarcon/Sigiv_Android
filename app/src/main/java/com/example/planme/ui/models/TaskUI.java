package com.example.planme.ui.models;

public class TaskUI {
    private final String id;
    private final String name;
    private final String description;
    private final String userId;
    private final String status;

    public TaskUI(String id, String name, String description, String userId, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }
}
