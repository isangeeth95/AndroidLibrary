package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class s_adminDashboard extends AppCompatActivity {

    LinearLayout bookManage;
    LinearLayout userManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);

        bookManage = (LinearLayout) findViewById(R.id.bookManage);
        userManage = (LinearLayout) findViewById(R.id.userManage);

        bookManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_view_book=new Intent(s_adminDashboard.this, Inventory_main.class);
                startActivity(intent_view_book);
            }
        });
//        userManage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent_add_book=new Intent(s_adminDashboard.this, AddBook.class);
////                startActivity(intent_add_book);
//            }
//        });
    }
}
