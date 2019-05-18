package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Inventory_main extends AppCompatActivity {

    LinearLayout view_book;
    LinearLayout add_book;
    LinearLayout manage_book;
    LinearLayout search_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_main);

        view_book = (LinearLayout) findViewById(R.id.view_book);
        add_book = (LinearLayout) findViewById(R.id.add_book);
        manage_book = (LinearLayout) findViewById( R.id.manage_book );
        search_book = (LinearLayout) findViewById( R.id.search_book );

        view_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_view_book=new Intent(Inventory_main.this, Inventory_view_book.class);
                startActivity(intent_view_book);
            }
        });
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_book=new Intent(Inventory_main.this, Inventory_add_book.class);
                startActivity(intent_add_book);
            }
        });
        manage_book.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent_manage_book=new Intent( Inventory_main.this, Inventory_manage_book.class );
                startActivity(intent_manage_book);
            }
        });
        search_book.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent_search_book=new Intent( Inventory_main.this, Inventory_test.class );
                startActivity(intent_search_book);
            }
        });
    }

}
