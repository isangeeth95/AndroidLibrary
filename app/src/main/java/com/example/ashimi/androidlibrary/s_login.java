package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class s_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_login);

        TextView registration;
        final Button register_btn;
        final EditText userName,passWord;
        final Button login_btn;

        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.password);
//        registration = findViewById(R.id.registration);
        login_btn = findViewById(R.id.loginButton);
        register_btn =findViewById(R.id.registerButton);

//        registration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registration_page = new Intent(s_login.this,s_register.class);
//                startActivity(registration_page);
//            }
//        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration_page = new Intent(s_login.this,s_register.class);
                startActivity(registration_page);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String s_user = userName.getText().toString();
                String s_pass = passWord.getText().toString();

//                String pass = helper.searchPass(s_user);
//
//                if ( s_pass.equals(pass) ){
//
//                    Intent intent = new Intent(login.this,home.class);
//                    startActivity(intent);
//                }
//                else {
//
//                    Toast.makeText(login.this, "Username Password Incorrect", Toast.LENGTH_SHORT).show();
//                }




            }
        });








    }
}
