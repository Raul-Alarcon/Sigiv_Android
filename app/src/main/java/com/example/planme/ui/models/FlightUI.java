package com.example.planme.ui.models;

public class FlightUI {
    private String time;
    private String topic;
    private String txt;
    //private String id;
    private int color;

    public FlightUI() {
        this.time = time;
        this.topic = topic;
        this.txt = txt;
        //this.id = id;
        this.color = color;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String text) {
        this.txt = text;
    }

   /* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
