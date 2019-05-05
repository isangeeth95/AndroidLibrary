package com.example.ashimi.androidlibrary;

public class EBook {

    private String ISBN;
    private String eBookTitle;
    private String category;
    private String author;
    private String edition;
    private String publishDate;
    private String language;
    private String picture;

    public EBook(){}

    public EBook(String ISBN, String eBookTitle, String category, String author, String edition, String publishDate, String language, String picture) {
        this.ISBN = ISBN;
        this.eBookTitle = eBookTitle;
        this.category = category;
        this.author = author;
        this.edition = edition;
        this.publishDate = publishDate;
        this.language = language;
        this.picture = picture;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String geteBookTitle() {
        return eBookTitle;
    }

    public void seteBookTitle(String eBookTitle) {
        this.eBookTitle = eBookTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
