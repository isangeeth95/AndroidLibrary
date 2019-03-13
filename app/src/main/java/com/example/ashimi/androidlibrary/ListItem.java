package com.example.ashimi.androidlibrary;

public class ListItem {
    private String head;
    private String des;
    private String team;
    private String firstappearance;
    private String createdby;
    private String publisher;
    private String imageurl;
    private String bio;

    public ListItem(String head, String des, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
        this.head = head;
        this.des = des;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.bio = bio;
    }

    public String getHead() {
        return head;
    }

    public String getDes() {
        return des;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getBio() {
        return bio;
    }
}

