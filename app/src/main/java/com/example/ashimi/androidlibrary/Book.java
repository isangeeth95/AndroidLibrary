package com.example.ashimi.androidlibrary;

import android.widget.EditText;

public class Book {

    String book_id;
    String book_name;
    String category;
    String location;

    public Book(){}

    public Book(String book_id, String book_name, String category, String location){
        this.book_id = book_id;
        this.book_name = book_name;
        this.category = category;
        this.location = location;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
