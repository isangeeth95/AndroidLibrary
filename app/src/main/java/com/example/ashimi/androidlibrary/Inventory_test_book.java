package com.example.ashimi.androidlibrary;

public class Inventory_test_book {
    String bookId;
    String BookName;
    String BookCat;

    public Inventory_test_book(String bookId, String bookName, String bookCat) {
        this.bookId = bookId;
        BookName = bookName;
        BookCat = bookCat;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookCat() {
        return BookCat;
    }

    public void setBookCat(String bookCat) {
        BookCat = bookCat;
    }
}
