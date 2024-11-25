package com.example.planme.data.models;

public class MemberGroup {
    private String id;
    private String groupId;
    private String date;
    private String member; // id user

    public MemberGroup(){

    }
    public MemberGroup(String id, String groupId, String date, String member) {
        this.id = id;
        this.groupId = groupId;
        this.date = date;
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
