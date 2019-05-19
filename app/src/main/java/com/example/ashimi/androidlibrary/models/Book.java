package com.example.ashimi.androidlibrary.models;

public class Book {
    private String id,title,author,category,location,coverPhotoURL;
    private float rating;

    public Book(){}

    public Book(String title, String author, float rating,String location,String category ,String coverPhotoURL) {
        this.title = title;
        this.author = author;
        this.location = location;
        this.rating = rating;
        this.category = category;
        this.coverPhotoURL = coverPhotoURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
    }
}
