package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ashimi.androidlibrary.R;
import com.example.ashimi.androidlibrary.BookGalleryAdapter;
import com.example.ashimi.androidlibrary.helpers.BookDatabaseHelper;
import com.example.ashimi.androidlibrary.Book;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;

public class Inventory_view_book extends AppCompatActivity implements View.OnClickListener {
//    DatabaseReference databaseReference;
//    ListView listViewBook;
//    List<Book> bookList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.inventory_view_book );
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Book");
//
//        bookList = new ArrayList<>( );
//
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        Toast.makeText( this, "Your code suuhugsghghg", Toast.LENGTH_LONG ).show();
//
//        databaseReference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                bookList.clear();
//
//                for(DataSnapshot bookSnapshot1 : dataSnapshot.getChildren()){
//                    Book book = bookSnapshot1.getValue(Book.class);
//
//                    bookList.add( book );
//                }
//                Inventory_list adapter = new Inventory_list( Inventory_view_book.this, bookList );
//                listViewBook.setAdapter( adapter );
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        } );
//    }


    private GridView gallery;
    private BookGalleryAdapter bookGalleryAdapter;
    private ArrayList<Book> books;
    private FloatingActionButton addBook;
    private BookDatabaseHelper bookDatabaseHelper;
    private AVLoadingIndicatorView loader;
    private TextView check_availibity,checkInternet;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_view_book);
       // setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        loader=(AVLoadingIndicatorView)findViewById(R.id.loader);
        gallery=(GridView)findViewById(R.id.book_gallery);
        books=new ArrayList<>();
        bookDatabaseHelper=new BookDatabaseHelper(this);
        bookGalleryAdapter=new BookGalleryAdapter(books,this);
        addBook=(FloatingActionButton)findViewById(R.id.add_book);
        check_availibity=(TextView)findViewById(R.id.book_check_availability);
        checkInternet=(TextView)findViewById(R.id.check_connectivity);
        gallery.setAdapter(bookGalleryAdapter);
        bookDatabaseHelper.all(checkInternet,check_availibity,loader,books,this,gallery);
        addBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_book:
                startActivity(new Intent(Inventory_view_book.this,Inventory_add_book.class));
                break;
        }
    }
}





















