package com.example.ashimi.androidlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class s_register extends AppCompatActivity {

    EditText name_Register;
    EditText email_Register;
    EditText tp_Register;
    EditText password_Register;
    EditText confirmPassword_Register;
    CardView register_Btn;
    TextView userLogin;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_s_register);

        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();

        register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //update the database

                    final String user_email = email_Register.getText().toString().trim();
                    String user_password = password_Register.getText().toString().trim();

                    progressDialog.setMessage("Registering");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(s_register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                if(saveUserInformation()){
                                    finish();
                                    startActivity(new Intent(s_register.this, s_login.class));
                                }
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(s_register.this, "Registration Failed, Always required strong password and unique email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(s_register.this, s_login.class));
            }
        });
    }

    private void setupUIViews() {
        name_Register = (EditText) findViewById(R.id.Name_Register);
        tp_Register = (EditText) findViewById(R.id.telephone_Register);
        email_Register = (EditText) findViewById(R.id.email_Register);
        password_Register = (EditText) findViewById(R.id.password);
        confirmPassword_Register = (EditText) findViewById(R.id.confirmPassword_Register);
        register_Btn = (CardView) findViewById(R.id.card_register);
        userLogin = (TextView)findViewById(R.id.text_alreadyLogin);
        progressDialog = new ProgressDialog(this);
    }

    private Boolean validate(){
        Boolean result = false;

        String name = name_Register.getText().toString();
        String password = password_Register.getText().toString();
        String confirm_pwd = confirmPassword_Register.getText().toString();
        String email = email_Register.getText().toString();
        String mobile = tp_Register.getText().toString();

        Pattern pattern = Pattern.compile("^+[0]\\d{2}\\d{7}");
        Matcher matcher = pattern.matcher(mobile);

        if(name.isEmpty() || password.isEmpty() || confirm_pwd.isEmpty() || email.isEmpty() || mobile.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }

        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }

        else if(!matcher.matches()){
            Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
        }

        else if (!password.equals(confirm_pwd)){
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        }

        else{
            result = true;
        }

        return result;
    }

    private Boolean saveUserInformation(){
        Boolean result = false;

        String user_name = name_Register.getText().toString().trim();
        String user_mobile = tp_Register.getText().toString().trim();
        String user_email = email_Register.getText().toString().trim();
        String status = "enabled";
        String mode = "user";

        FirebaseUser user=firebaseAuth.getCurrentUser();
        mData = FirebaseDatabase.getInstance().getReference();

        if(user != null){
            mData.child("users").child(user.getUid()).child("user_email").setValue(user_email);
            mData.child("users").child(user.getUid()).child("user_name").setValue(user_name);
            mData.child("users").child(user.getUid()).child("mobile_number").setValue(user_mobile);
            mData.child("users").child(user.getUid()).child("status").setValue(status);
            mData.child("users").child(user.getUid()).child("mode").setValue(mode);

            result = true;
        }

        firebaseAuth.signOut();
        return result;
    }

}


