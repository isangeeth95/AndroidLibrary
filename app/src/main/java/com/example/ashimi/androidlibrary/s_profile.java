package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class s_profile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView dbName, dbEmail, dbMobile;
    CardView get_profileForm;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_profile);

        setupUIViews();

        mData = FirebaseDatabase.getInstance().getReference().child("users");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                dbName.setText((String)dataSnapshot.child(user.getUid()).child("user_name").getValue());
                dbEmail.setText((String)dataSnapshot.child(user.getUid()).child("user_email").getValue());
                dbMobile.setText((String)dataSnapshot.child(user.getUid()).child("mobile_number").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(s_profile.this, "Sorry..... something is wrong.", Toast.LENGTH_SHORT).show();
            }
        });

        get_profileForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                System.out.println(dbName.getText().toString().trim());
                System.out.println(dbMobile.getText().toString().trim());

                String user_name = dbName.getText().toString().trim();
                String mobile_number = dbMobile.getText().toString().trim();

                if(validate(user_name, mobile_number)){
                    mData.child(user.getUid()).child("user_name").setValue(user_name);
                    mData.child(user.getUid()).child("mobile_number").setValue(mobile_number);
                }
            }
        });
    }

    private void setupUIViews(){
        firebaseAuth = FirebaseAuth.getInstance();
        dbName = (TextView)findViewById(R.id.dbName);
        dbEmail = (TextView) findViewById(R.id.dbEmail);
        dbMobile = (TextView)findViewById(R.id.dbMobile);
        get_profileForm = (CardView)findViewById(R.id.card_getProfileForm);
    }

    private Boolean validate(String user, String mobile){
        Boolean result = false;

        if(user == null || mobile == null){
            Toast.makeText(s_profile.this, "Dont keep blank lines", Toast.LENGTH_SHORT).show();
        }

        else if(!android.util.Patterns.PHONE.matcher(mobile).matches()){
            Toast.makeText(s_profile.this, "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
        }

        else{
            result = true;
        }
        return result;
    }
}
