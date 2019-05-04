package com.example.ashimi.androidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Inventory_add_book extends AppCompatActivity {
    EditText book_id,book_name,category,location;
    Button add_book_button;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_add_book);

        book_id = findViewById(R.id.book_id);
        book_name = findViewById(R.id.book_name);
        category = findViewById(R.id.category);
        location = findViewById(R.id.location);
        add_book_button  = findViewById(R.id.add_book_button);

        ref = FirebaseDatabase.getInstance().getReference().child("Book");

       add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (book_id.getText().toString().isEmpty() && book_name.getText().toString().isEmpty() &&
                        category.getText().toString().isEmpty() && location.getText().toString().isEmpty()){

                    Toast.makeText(Inventory_add_book.this, "Cannot have Empty Field", Toast.LENGTH_SHORT).show();
                }
                else {

                    try {
                        Book book = new Book();

                        book.setBook_id(book_id.getText().toString());
                        book.setBook_name(book_name.getText().toString());
                        book.setCategory(category.getText().toString());
                        book.setLocation(location.getText().toString());

                        book.getBook_id();
                        book.getBook_name();
                        book.getCategory();
                        book.getCategory();
                        book.getLocation();

                        ref.push().setValue(book);

                        Toast.makeText(Inventory_add_book.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        Log.e("ERROR",e.getLocalizedMessage());
                        Toast.makeText(Inventory_add_book.this, "error" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




    }



}


