package com.example.planme.ui.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NoteUI extends EntityUI {
    private String date;
    private String shortContent;
    private String title;

    public NoteUI(String id, String date, String shortContent, String title) {
        super.setId(id);
        this.date = date;
        this.shortContent = shortContent;
        this.title = title;
    }
    public NoteUI(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toJsonString() throws JSONException {
        String _this;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.getId());
        jsonObject.put("date", this.date);
        jsonObject.put("shortContent", this.shortContent);
        jsonObject.put("title", this.title);
        _this = jsonObject.toString();
        return _this;
    }

    public void jsonStringToNote(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        this.setId(jsonObject.getString("id"));
        this.date = jsonObject.getString("date");
        this.shortContent = jsonObject.getString("shortContent");
        this.title = jsonObject.getString("title");

    }
}
