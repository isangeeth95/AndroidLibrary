package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class s_adminDashboard extends AppCompatActivity {

    LinearLayout bookManage;
    LinearLayout userManage;
    LinearLayout lendingManage;
    ImageView logout;
    FirebaseAuth firebaseAuth;
    LinearLayout EBookmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        firebaseAuth = FirebaseAuth.getInstance();
        bookManage = (LinearLayout) findViewById(R.id.bookManage);
        userManage = (LinearLayout) findViewById(R.id.userManage);
        logout = (ImageView)findViewById(R.id.logout);
        lendingManage = (LinearLayout) findViewById(R.id.lendingManage);
        EBookmanager = (LinearLayout) findViewById(R.id.EBookmanager);

        bookManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_view_book=new Intent(s_adminDashboard.this, Inventory_main.class);
                startActivity(intent_view_book);
            }
        });

        userManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(s_adminDashboard.this, s_adminManageUsers.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(s_adminDashboard.this, s_login.class));
                Toast.makeText(s_adminDashboard.this, "Successfully singed out", Toast.LENGTH_SHORT).show();
            }
        });

        lendingManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("i am working");
                startActivity(new Intent(s_adminDashboard.this, Activity_lending_main.class));
            }
        });

        EBookmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("i am working");
                startActivity(new Intent(s_adminDashboard.this, select.class));
            }
        });

//        lendingManage.setOnClickListener({
//                startActivity(new Intent(s_adminDashboard.this, Activity_lending_main.class));
//
//        })
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
