package com.example.ashimi.androidlibrary.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ashimi.androidlibrary.R;
import com.example.ashimi.androidlibrary.adapters.BookGalleryAdapter;
import com.example.ashimi.androidlibrary.helpers.BookDatabaseHelper;
import com.example.ashimi.androidlibrary.models.Book;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class User_view_book extends AppCompatActivity  {
    private GridView gallery;
    private BookGalleryAdapter bookGalleryAdapter;
    private ArrayList<Book> books;
    private FloatingActionButton addBook;
    private BookDatabaseHelper bookDatabaseHelper;
    private AVLoadingIndicatorView loader;
    private TextView check_availibity,checkInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.book_view_user);
        // setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        loader=(AVLoadingIndicatorView)findViewById( R.id.loader);
        gallery=(GridView)findViewById( R.id.book_gallery);
        books=new ArrayList<>();
        bookDatabaseHelper=new BookDatabaseHelper(this);
        bookGalleryAdapter=new BookGalleryAdapter(books,this);
        addBook=(FloatingActionButton)findViewById( R.id.add_book);
        check_availibity=(TextView)findViewById( R.id.book_check_availability);
        checkInternet=(TextView)findViewById( R.id.check_connectivity);
        gallery.setAdapter(bookGalleryAdapter);
        bookDatabaseHelper.all(checkInternet,check_availibity,loader,books,this,gallery);


    }
}
