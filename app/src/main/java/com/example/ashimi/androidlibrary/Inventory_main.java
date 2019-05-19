package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ashimi.androidlibrary.activities.AddBook;
import com.example.ashimi.androidlibrary.activities.MainActivity;

public class Inventory_main extends AppCompatActivity {

    LinearLayout view_book;
    LinearLayout add_book;
    LinearLayout search_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_main);

        view_book = (LinearLayout) findViewById(R.id.view_book);
        add_book = (LinearLayout) findViewById(R.id.add_book);
        search_book = (LinearLayout) findViewById( R.id.search_book );

        view_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_view_book=new Intent(Inventory_main.this, MainActivity.class);
                startActivity(intent_view_book);
            }
        });
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_book=new Intent(Inventory_main.this, AddBook.class);
                startActivity(intent_add_book);
            }
        });
        search_book.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent_search_book=new Intent( Inventory_main.this, Inventory_main.class );
                startActivity(intent_search_book);
            }
        });
    }

}
