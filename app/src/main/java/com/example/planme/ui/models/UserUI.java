package com.example.planme.ui.models;

import android.net.Uri;

public class UserUI {
    private String id;
    private Uri urlImg;
    private String email;
    private String name;

    public UserUI(String id, Uri urlImg, String email, String name) {
        this.id = id;
        this.urlImg = urlImg;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(Uri urlImg) {
        this.urlImg = urlImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
