package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fourth extends AppCompatActivity {

    DatabaseReference ref;
    RecyclerView recyclerView;
    ArrayList<EBook> list;
    EBookAdapter eBookAdapter;

    TextView isbn,eBookTitle,category,author,edition,publishDate,language;
    ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        recyclerView = (RecyclerView) findViewById(R.id.myrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<EBook>();

        isbn = (TextView)findViewById(R.id.isbn);
        eBookTitle = (TextView)findViewById( R.id.name );
        category = (TextView)findViewById( R.id.category );
        author= (TextView)findViewById( R.id.author);
        edition= (TextView)findViewById(R.id.edition);
        publishDate=(TextView)findViewById(R.id.publishDate);
        language=(TextView)findViewById(R.id.language);
        picture=(ImageView) findViewById(R.id.picture);

        ref = FirebaseDatabase.getInstance().getReference().child("EBook");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    EBook e = dataSnapshot1.getValue(EBook.class);
                    list.add(e);
                }

                eBookAdapter = new EBookAdapter(fourth.this, list);
                recyclerView.setAdapter(eBookAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(fourth.this, "Sorry..... something is wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
