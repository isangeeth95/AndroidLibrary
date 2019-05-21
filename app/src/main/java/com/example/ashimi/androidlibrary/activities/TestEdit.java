package com.example.ashimi.androidlibrary.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashimi.androidlibrary.EBook;
import com.example.ashimi.androidlibrary.EditEBook;
import com.example.ashimi.androidlibrary.R;
import com.example.ashimi.androidlibrary.models.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestEdit extends AppCompatActivity {

    private EditText titleField,authorField,categoryField,locationField,ISBNField,quantityField;
    private RatingBar ratingField;
    private Button editBook;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sample_book_update );

        titleField = (EditText) findViewById( R.id. book_title_field);
        authorField = (EditText) findViewById( R.id.book_author_field );
        categoryField = (EditText) findViewById( R.id.book_category );
        locationField = (EditText) findViewById( R.id.book_location );
        ISBNField= (EditText) findViewById(R.id.book_ISBN_field);
        quantityField = (EditText) findViewById( R.id.book_quantity );
        ratingField = (RatingBar) findViewById( R.id.rating );
        editBook= (Button) findViewById(R.id.edit_book);

        editBook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( TestEdit.this, "Clicked", Toast.LENGTH_LONG).show();
                ref = FirebaseDatabase.getInstance().getReference().child("Book").child("1");
                ref.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Book book = new Book();

                        book.setISBM( dataSnapshot.child( "isbm" ).getValue().toString() );
                        book.setTitle( dataSnapshot.child( "title" ).getValue().toString() );
                        book.setAuthor( dataSnapshot.child( "author" ).getValue().toString() );
                        book.setCategory( dataSnapshot.child( "category" ).getValue().toString() );
                        book.setLocation( dataSnapshot.child( "location" ).getValue().toString() );
//                        book.setQuantity( dataSnapshot.child( "quantity" ).getValue().toString() );


                        titleField.setText( book.getTitle() );
                        authorField.setText( book.getAuthor() );
                        categoryField.setText( book.getCategory() );
                        locationField.setText( book.getLocation() );
                        ISBNField.setText( book.getISBM() );
//                        quantityField.setText( book.getQuantity() );

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

            }
        } );
    }
}
