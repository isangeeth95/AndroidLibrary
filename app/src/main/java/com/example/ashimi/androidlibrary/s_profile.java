package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
    }

    private void setupUIViews(){
        firebaseAuth = FirebaseAuth.getInstance();
        dbName = (TextView)findViewById(R.id.dbName);
        dbEmail = (TextView) findViewById(R.id.dbEmail);
        dbMobile = (TextView)findViewById(R.id.dbMobile);
        get_profileForm = (CardView)findViewById(R.id.card_getProfileForm);
    }
}
