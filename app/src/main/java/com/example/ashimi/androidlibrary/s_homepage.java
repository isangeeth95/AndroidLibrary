package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class s_homepage extends AppCompatActivity {

    LinearLayout category_view;
    LinearLayout login_view;
    LinearLayout inventory_view;
    LinearLayout location;
//    Toolbar toolbar;
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_select_drawer, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
//                finish();
//                startActivity(new Intent(s_homepage.this, s_login.class));
//                break;
//        }
//
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_s_homepage);

        category_view = (LinearLayout) findViewById(R.id.see_category);
        login_view = (LinearLayout) findViewById(R.id.membership);
        inventory_view = (LinearLayout) findViewById(R.id.inventory);
        location = (LinearLayout)findViewById(R.id.profile_form);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        category_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(s_homepage.this, CATEGORIES.class);
                startActivity(intent);
            }
        });

        login_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_view=new Intent(s_homepage.this, s_login.class);
                startActivity(intent_login_view);
            }
        });

        inventory_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_inventory_view=new Intent(s_homepage.this, Inventory_main.class);
                startActivity(intent_inventory_view);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(s_homepage.this, s_profile.class));
            }
        });

    }

}
