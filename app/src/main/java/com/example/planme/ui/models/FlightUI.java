package com.example.planme.ui.models;

import java.time.LocalDate;

public class FlightUI {
    private String id;
    private String topic;
    private String txt;
    private LocalDate time;

    public FlightUI(String id, String topic, String txt, LocalDate time) {
        this.id = id;
        this.topic = topic;
        this.txt = txt;
        this.time = time;
    }

    public FlightUI() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

}
