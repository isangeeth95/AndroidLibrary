package com.example.ashimi.androidlibrary;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class s_profile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView dbName, dbEmail, dbMobile, email_verification;
    CardView get_profileForm;
    DatabaseReference mData;
    ProgressDialog progressDialog;
    ImageView image, deleteUser, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_profile);
        FirebaseApp.initializeApp(this);

        setupUIViews();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user.isEmailVerified()){
            email_verification.setText("Verified");
        }else{
            email_verification.setText("Not Verified, Click to verify!");
            email_verification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(s_profile.this, "Verification EMail sent", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

        mData = FirebaseDatabase.getInstance().getReference().child("users");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dbName.setText((String)dataSnapshot.child(user.getUid()).child("user_name").getValue());
                dbEmail.setText((String)dataSnapshot.child(user.getUid()).child("user_email").getValue());
                dbMobile.setText((String)dataSnapshot.child(user.getUid()).child("mobile_number").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(s_profile.this, "Sorry..... something is wrong.", Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(s_profile.this, s_login.class));
                Toast.makeText(s_profile.this, "Successfully singed out", Toast.LENGTH_SHORT).show();
            }
        });

        get_profileForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(dbName.getText().toString().trim());
                System.out.println(dbMobile.getText().toString().trim());

                String user_name = dbName.getText().toString().trim();
                String mobile_number = dbMobile.getText().toString().trim();

                if(validate(user_name, mobile_number)){
                    mData.child(user.getUid()).child("user_name").setValue(user_name);
                    mData.child(user.getUid()).child("mobile_number").setValue(mobile_number);
                    progressDialog.dismiss();
                    Toast.makeText(s_profile.this, "Successfully updated the profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(s_profile.this, s_homepage.class));
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(s_profile.this);
                alert.setMessage("Do you really want to remove your account and leave us ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mData.child(user.getUid()).child("status").setValue("disabled");
                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(s_profile.this, "User successfully removed from the database", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(s_profile.this, s_login.class));
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alert.create();
                alertDialog.setTitle("Think thousand times");
                alertDialog.show();
            }
        });
    }

    private void setupUIViews(){
        firebaseAuth = FirebaseAuth.getInstance();
        dbName = (TextView)findViewById(R.id.dbName);
        dbEmail = (TextView) findViewById(R.id.dbEmail);
        dbMobile = (TextView)findViewById(R.id.dbMobile);
        get_profileForm = (CardView)findViewById(R.id.card_getProfileForm);
        progressDialog = new ProgressDialog(this);
        image = (ImageView)findViewById(R.id.imageView2);
        deleteUser = (ImageView)findViewById(R.id.deleteUserImage);
        logout = (ImageView)findViewById(R.id.logout);
        email_verification = (TextView)findViewById(R.id.email_verification);
    }

    private Boolean validate(String user, String mobile){
        progressDialog.setMessage("Validating");
        progressDialog.show();
        Boolean result = false;

        Pattern pattern = Pattern.compile("^+[0]\\d{2}\\d{7}");
        Matcher matcher = pattern.matcher(mobile);

        if(user == null || mobile == null){
            progressDialog.dismiss();
            Toast.makeText(s_profile.this, "Dont keep blank lines", Toast.LENGTH_SHORT).show();
        }

        else if(!matcher.matches()){
            progressDialog.dismiss();
            Toast.makeText(s_profile.this, "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
        }

        else{
            progressDialog.dismiss();
            progressDialog.setMessage("Updating database");
            progressDialog.show();
            result = true;
        }
        return result;
    }
}
