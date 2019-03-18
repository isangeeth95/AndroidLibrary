package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class s_homepage extends AppCompatActivity {

    LinearLayout category_view;
    LinearLayout login_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_homepage);

        category_view = (LinearLayout) findViewById(R.id.see_category);
        login_view = (LinearLayout) findViewById(R.id.membership);

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
    }

}