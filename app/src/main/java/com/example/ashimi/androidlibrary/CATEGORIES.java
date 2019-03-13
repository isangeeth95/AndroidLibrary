package com.example.ashimi.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CATEGORIES extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(CATEGORIES.this,second.class);
                startActivity(int1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(CATEGORIES.this,third.class);
                startActivity(int2);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(this, "Information Technology clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                Toast.makeText(this, "Science and Research clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button3:
                Toast.makeText(this, "Business Management clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button4:
                Toast.makeText(this, "Politics and Law clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button5:
                Toast.makeText(this, "Hospitality Management clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button6:
                Toast.makeText(this,"Health and Fitness clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button7:
                Toast.makeText(this, "Fiction and Literature clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button8:
                Toast.makeText(this, "Other is still empty", Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
