package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_lending_main extends AppCompatActivity {

    private Button lendingMainBorrowBtn;
    private Button issueBooksBtn;
    private Button availableBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_main);
        lendingMainBorrowBtn =(Button) findViewById(R.id.retunBtn);
        issueBooksBtn = (Button) findViewById(R.id.issueBooksBtn);
        availableBtn = findViewById(R.id.availableBtn);

        lendingMainBorrowBtn.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(Activity_lending_main.this, Activity_Borrowing.class));
            }
        });

        issueBooksBtn.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(Activity_lending_main.this, Activity_Issue_books.class));
            }
        });
    }
}
