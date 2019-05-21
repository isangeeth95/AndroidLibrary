package com.example.ashimi.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashimi.androidlibrary.helpers.BorrowingDatabaseHelper;
import com.example.ashimi.androidlibrary.models.Borrowing;

import java.util.Date;

public class Activity_Borrowing extends AppCompatActivity {
    EditText borrowISDM, borrowUserID;
    Button borrowIssueButton;
    BorrowingDatabaseHelper borrowTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__borrowing);

        borrowISDM = findViewById(R.id.borrowISDM);
        borrowUserID = findViewById(R.id.borrowUserID);
        borrowIssueButton = findViewById(R.id.borrowIssueButton);

        borrowIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (borrowISDM.getText().toString().isEmpty() && borrowUserID.getText().toString().isEmpty()){
                    Toast.makeText(Activity_Borrowing.this,"Cannot have Emty Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("values","values come");
                    Log.d("values1",borrowISDM.getText().toString()+ borrowUserID.getText().toString());
                    try{
                        Date today = new Date();
                        Borrowing newBorrower = new Borrowing(borrowISDM.getText().toString(), borrowUserID.getText().toString(),"today" );
                        borrowTable.newBorrow(newBorrower);
                        Toast.makeText(Activity_Borrowing.this,"Added", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Log.e("Error", e.getLocalizedMessage());
                        Toast.makeText(Activity_Borrowing.this,"error", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }
}
