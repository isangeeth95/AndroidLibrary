package com.example.ashimi.androidlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class s_login extends AppCompatActivity {

    EditText email;
    EditText password;
    CardView login_Btn;
    TextView register_btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    TextView forgotPassword_btn;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_login);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(email.getText().toString(), password.getText().toString())){
                    login(email.getText().toString(), password.getText().toString());
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(s_login.this, s_register.class));
            }
        });

        forgotPassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgotPasswordEmail = email.getText().toString().trim();
                if(TextUtils.isEmpty(forgotPasswordEmail)){
                    Toast.makeText(s_login.this, "Please enter your valid email address", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(forgotPasswordEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(s_login.this, "Please check your mails, and reset your password", Toast.LENGTH_SHORT).show();
                            }else{
                                String message = task.getException().getMessage();
                                Toast.makeText(s_login.this, "Error occurred. " +message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(s_login.this, s_homepage.class));
            Toast.makeText(s_login.this, "You have aleary logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupUIViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_Btn = (CardView) findViewById(R.id.card_login);
        register_btn = (TextView) findViewById(R.id.register_text);
        progressDialog = new ProgressDialog(this);
        forgotPassword_btn = (TextView)findViewById(R.id.forgotPassword);
    }

    private void login(String user_email, String user_password){

        progressDialog.setMessage("Login");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    final FirebaseUser user = firebaseAuth.getCurrentUser();
                    mData = FirebaseDatabase.getInstance().getReference().child("users");
                    mData.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = (String)dataSnapshot.child(user.getUid()).child("mode").getValue();
                            System.out.println(value);
                            if( value.equalsIgnoreCase("admin")){
                                Toast.makeText(s_login.this, "Login Successful as ADMIN", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(s_login.this, s_adminDashboard.class));
                            }
                            else{
                                Toast.makeText(s_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(s_login.this, s_homepage.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(s_login.this, "Login Failed, Check credentials again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validate(String email, String password){
        Boolean result = false;
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }

        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }

        else{
            result = true;
        }

        return result;
    }

}
