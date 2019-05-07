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

    EditText email;
    EditText password;
    Button login_btn;
    Button register_btn;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_login);

        db = new DataBaseHelper(this);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.loginButton);
        register_btn = (Button) findViewById(R.id.registerButton);

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
                String mail = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(mail, pwd);

                if(res == true){
                    Toast.makeText(s_login.this, "Successfully logged in as "+email, Toast.LENGTH_SHORT).show();
                    Intent moveToHome=new Intent(s_login.this, s_homepage.class);
                    startActivity(moveToHome);
                }
                else{
                    Toast.makeText(s_login.this, "Login error", Toast.LENGTH_SHORT).show();
                    Intent refresh = getIntent();
                    startActivity(refresh);//Start the same Activity
                    finish();
                }
            }
        });
    }
}
