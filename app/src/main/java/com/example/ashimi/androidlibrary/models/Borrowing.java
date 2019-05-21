package com.example.ashimi.androidlibrary.models;

import java.util.Date;

public class Borrowing {
    private String ISBN;
    private String borrowerID;
    private String outdate;

    public Borrowing(){}

    public Borrowing(String ISBN, String borrowerID, String outdate) {
        this.ISBN = ISBN;
        this.borrowerID = borrowerID;
        this.outdate = outdate;
    }

    public Borrowing(Borrowing bwr) {
        this.ISBN = bwr.ISBN;
        this.borrowerID = bwr.borrowerID;
        this.outdate = bwr.outdate;
    }

    //setters

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }


    //getters


    public String getISBN() {
        return ISBN;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public String getOutdate() {
        return outdate;
    }
}
