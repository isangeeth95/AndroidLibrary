package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class s_register extends AppCompatActivity {

    DataBaseHelper db;
    EditText fName_Register;
    EditText lName_Register;
    EditText address_Register;
    EditText email_Register;
    EditText password_Register;
    EditText confirmPassword_Register;
    Button register_Btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_register);

        db = new DataBaseHelper(this);
        fName_Register = (EditText) findViewById(R.id.firstName_Register);
        lName_Register = (EditText) findViewById(R.id.lastName_Register);
        address_Register = (EditText) findViewById(R.id.address_Register);
        //tp_Register = findViewById(R.id.telephone_Register);
        email_Register = (EditText) findViewById(R.id.email_Register);
        password_Register = (EditText) findViewById(R.id.password);
        confirmPassword_Register = (EditText) findViewById(R.id.confirmPassword_Register);
        register_Btn  = (Button) findViewById(R.id.registerButton);



        register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_name = fName_Register.getText().toString().trim();
                String l_name = lName_Register.getText().toString().trim();
                String address = address_Register.getText().toString().trim();
                String email = email_Register.getText().toString().trim();
                String password = password_Register.getText().toString().trim();
                String conf_password = confirmPassword_Register.getText().toString().trim();

                if(password.equals(conf_password)){
                    long val = db.addUser(f_name, l_name, address, email, password);

                    if(val > 0){
                        Toast.makeText(s_register.this, "Successfully registered as "+email+" .", Toast.LENGTH_SHORT).show();

                        Intent moveToLogin = new Intent(s_register.this, s_login.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(s_register.this, "Registration failed"+email+" .", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(s_register.this, "Password is not matching"+email+" .", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }



}


