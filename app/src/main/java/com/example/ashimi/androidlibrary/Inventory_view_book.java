package com.example.ashimi.androidlibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Inventory_view_book extends AppCompatActivity {
    TextView id,name,cat,loc;
    Button btn;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.inventory_view_book );

        id = (TextView)findViewById( R.id.book_id );
        name = (TextView)findViewById( R.id.book_name_view );
        cat = (TextView)findViewById( R.id.category_view );
        loc = (TextView)findViewById( R.id.location_view );
        btn = (Button)findViewById( R.id.btn );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Inventory_view_book.this, "Clicked", Toast.LENGTH_LONG).show();
                ref = FirebaseDatabase.getInstance().getReference().child("Book").child("1");
                ref.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Book book = new Book(  );
                        book.setBook_id( dataSnapshot.child( "book_id" ).getValue().toString() );
                        book.setBook_name( dataSnapshot.child( "book_name" ).getValue().toString() );
                        book.setCategory( dataSnapshot.child( "category" ).getValue().toString() );
                        book.setLocation( dataSnapshot.child( "location" ).getValue().toString() );

                        id.setText( book.getBook_id() );
                        name.setText( book.getBook_name() );
                        cat.setText( book.getCategory() );
                        loc.setText( book.getLocation() );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

            }
        } );
    }
}





















