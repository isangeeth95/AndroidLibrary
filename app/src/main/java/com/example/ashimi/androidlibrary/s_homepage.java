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
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class s_homepage extends AppCompatActivity {

    LinearLayout category_view;
    LinearLayout profile_view;
    LinearLayout inventory_view;
    LinearLayout logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_s_homepage);

        firebaseAuth = FirebaseAuth.getInstance();
        category_view = (LinearLayout) findViewById(R.id.see_category);
        profile_view = (LinearLayout) findViewById(R.id.profile_view);
        inventory_view = (LinearLayout) findViewById(R.id.inventory);
        logout = (LinearLayout)findViewById(R.id.logout);



        category_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(s_homepage.this, CATEGORIES.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(s_homepage.this, s_login.class));
                Toast.makeText(s_homepage.this, "Successfully singed out", Toast.LENGTH_SHORT).show();
            }
        });

        inventory_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_inventory_view=new Intent(s_homepage.this, Inventory_main.class);
                startActivity(intent_inventory_view);
            }
        });

        profile_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(s_homepage.this, s_profile.class));
            }
        });

    }

}
