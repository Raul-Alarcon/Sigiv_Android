package com.example.planme.ui.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class FlightUI {
    private String id;
    private String topic;
    private String txt;
    private String time;

    public FlightUI(String id, String topic, String txt, String time) {
        this.id = id;
        this.topic = topic;
        this.txt = txt;
        this.time = time;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public FlightUI() {
        this.id = "";
        this.topic = "";
        this.txt = "";
        this.time = null;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
