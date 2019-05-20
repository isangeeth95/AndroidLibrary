package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class s_adminManageUsers extends AppCompatActivity {

    LinearLayout add_note;
    LinearLayout user_list;
    LinearLayout profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_admin_manage_users);
        setupUIViews();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(s_adminManageUsers.this, s_profile.class));
            }
        });

        user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(new Intent(s_adminManageUsers.this, s_adminGetUserList.class));
                Toast.makeText(s_adminManageUsers.this,"Admin get user list clicked", Toast.LENGTH_SHORT).show();
            }
        });

        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(s_adminManageUsers.this, s_imageCapture.class));
            }
        });
    }

    private void setupUIViews(){
        add_note = (LinearLayout)findViewById(R.id.add_note);
        user_list = (LinearLayout)findViewById(R.id.user_list);
        profile = (LinearLayout)findViewById(R.id.profile);
    }
}
