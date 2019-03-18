package com.example.ashimi.androidlibrary;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_register);



        final EditText fName_Register,lName_Register,address_Register,tp_Register,email_Register,password_Register,confirmPassword_Register;
        final Button register_Btn;
//                final SQLiteDatabase sqLiteDatabase;


        fName_Register = findViewById(R.id.firstName_Register);
        lName_Register = findViewById(R.id.lastName_Register);
        address_Register = findViewById(R.id.address_Register);
        tp_Register = findViewById(R.id.telephone_Register);
        email_Register = findViewById(R.id.email_Register);
        password_Register = findViewById(R.id.password);
        confirmPassword_Register = findViewById(R.id.confirmPassword_Register);
        register_Btn  = findViewById(R.id.registerButton);



        register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fName_Register.getText().toString().isEmpty()){

                    Toast.makeText(s_register.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (password_Register.getText().toString().equals(confirmPassword_Register.getText().toString())) {

                        try {

//                            Contact c = new Contact();
//                            c.setUsername(fName_Register.getText().toString());
//                            c.setlName(lName_Register.getText().toString());
//                            c.setAddress(address_Register.getText().toString());
//                            c.setTp(tp_Register.getText().toString());
//                            c.setEmail(email_Register.getText().toString());
//                            c.setPssword(password_Register.getText().toString());
//
//                            helper.insertContact(c);


                            Toast.makeText(s_register.this, "Registration Succesfull", Toast.LENGTH_LONG).show();


                        } catch (Exception e) {
                            Log.e("ERROR",e.getLocalizedMessage());
                            Toast.makeText(s_register.this, "error" + e, Toast.LENGTH_LONG).show();
                        }

                    } else {

                        Toast.makeText(s_register.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }



}


