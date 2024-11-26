package com.example.planme.ui.models;


public class MessageException {
    private String content;
    private String method;
    private String stack;
    private boolean isActive;

    public MessageException(){}

    public MessageException(String content, String method, String stack) {
        this.content = content;
        this.method = method;
        this.stack = stack;
        this.isActive = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
