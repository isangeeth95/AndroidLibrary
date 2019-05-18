package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.FirebaseApp;

public class s_homepage extends AppCompatActivity {

    LinearLayout category_view;
    LinearLayout login_view;
    LinearLayout inventory_view;
    LinearLayout location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_s_homepage);

        category_view = (LinearLayout) findViewById(R.id.see_category);
        login_view = (LinearLayout) findViewById(R.id.membership);
        inventory_view = (LinearLayout) findViewById(R.id.inventory);
        location = (LinearLayout)findViewById(R.id.profile_form);

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
