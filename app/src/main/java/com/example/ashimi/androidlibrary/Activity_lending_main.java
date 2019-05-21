package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_lending_main extends AppCompatActivity {

    private Button lendingMainBorrowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_main);
        lendingMainBorrowBtn =(Button) findViewById(R.id.lendingMainBorrowBtn);
        lendingMainBorrowBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(Activity_lending_main.this, Sample.class));
            }
        });
    }
}
