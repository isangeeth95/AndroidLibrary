package com.example.ashimi.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class third extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mRecyclerView = findViewById(R.id.recyclerview_ebooks);
        new FirebaseDatabaseHelper().readEBoks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<EBook> EBook, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,third.this,EBook, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
