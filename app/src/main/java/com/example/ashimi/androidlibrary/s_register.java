package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;


public class s_register extends AppCompatActivity {

    EditText name_Register;
    EditText email_Register;
    EditText tp_Register;
    EditText password_Register;
    EditText confirmPassword_Register;
    CardView register_Btn;
    TextView userLogin;
    FirebaseAuth firebaseAuth;

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
                    String user_email = email_Register.getText().toString().trim();
                    String user_password = password_Register.getText().toString().trim();
                    String user_name = name_Register.getText().toString().trim();
                    String user_mobile = tp_Register.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(s_register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(s_register.this, s_login.class));
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(s_register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

    private Boolean validate(){
        Boolean result = false;

        String name = name_Register.getText().toString();
        String password = password_Register.getText().toString();
        String confirm_pwd = confirmPassword_Register.getText().toString();
        String email = email_Register.getText().toString();
        String mobile = tp_Register.getText().toString();

        if(name.isEmpty() || password.isEmpty() || confirm_pwd.isEmpty() || email.isEmpty() || mobile.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }

        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }

        else if(!android.util.Patterns.PHONE.matcher(mobile).matches()){
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

}


