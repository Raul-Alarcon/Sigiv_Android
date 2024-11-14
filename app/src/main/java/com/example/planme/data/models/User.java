package com.example.planme.data.models;

public class User extends  Entity{
    private String photoURL;
    private String email;
    private String userName;

    public User(String id, String photoURL, String email, String userName) {
        super(id);
        this.photoURL = photoURL;
        this.email = email;
        this.userName = userName;
    }

    public String getPhotoURL() { return  this.photoURL; }
    public void setPhotoURL(String photoURL){
        this.photoURL = photoURL;
    }
    public String getEmail() { return  this.email; }
    public void setEmail(String email){
        this.email = email;
    }
    public String getUserName() { return  this.userName; }
    public void setUserName(String userName){
        this.userName = userName;
    }

}
