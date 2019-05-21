package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashimi.androidlibrary.helpers.BorrowingDatabaseHelper;
import com.example.ashimi.androidlibrary.models.Borrowing;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class Activity_Borrowing extends AppCompatActivity {
    EditText borrowISDM,borrowUserID;
    Button borrowIssueButton;
    DatabaseReference ref;
    //    BorrowingDatabaseHelper borrowTable;
    long maxID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__borrowing);

        borrowISDM = findViewById( R.id.borrowISDM );
        borrowUserID = findViewById( R.id.borrowUserID );
        borrowIssueButton = findViewById( R.id.borrowIssueButton );

        ref = FirebaseDatabase.getInstance().getReference().child("Borrowing");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxID = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        borrowIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(borrowISDM.getText().toString().isEmpty()&& borrowUserID.getText().toString().isEmpty()){
                    Toast.makeText(Activity_Borrowing.this,"Cannot have Emty Fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    try {

                        Borrowing brw = new Borrowing();

                        brw.setISBN( (borrowISDM.getText().toString()));
                        brw.setBorrowerID(borrowUserID.getText().toString());
                        brw.setOutdate("fgfg");

//                        borrowTable.newBorrow(brw);
//
                        brw.getISBN();
                        brw.getBorrowerID();
                        brw.getOutdate();

                        ref.child(String.valueOf(maxID + 1)).setValue(brw);

                        Toast.makeText(Activity_Borrowing.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Log.e("Error", e.getLocalizedMessage());
                        Toast.makeText(Activity_Borrowing.this,"error"+ e, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
