package com.example.ashimi.androidlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class s_login extends AppCompatActivity {

    EditText email;
    EditText password;
    CardView login_Btn;
    TextView register_btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_login);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(s_login.this, s_register.class));
            }
        });
    }

    private void setupUIViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_Btn = (CardView) findViewById(R.id.card_login);
        register_btn = (TextView) findViewById(R.id.register_text);
        progressDialog = new ProgressDialog(this);
    }

    private void validate(String user_email, String user_password){

        progressDialog.setMessage("Login");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(s_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(s_login.this, s_homepage.class));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(s_login.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
