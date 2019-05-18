package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditEBook extends AppCompatActivity  {

    TextView isbn,ebooktitle,category,author,edition,publish,language,picture;
    Button button2;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_ebook );

        isbn = (TextView)findViewById( R.id.isbn );
        ebooktitle = (TextView)findViewById( R.id.ebooktitle );
        category = (TextView)findViewById( R.id.category );
        author = (TextView)findViewById( R.id.author );
        edition= (TextView)findViewById(R.id.edition);
        publish = (TextView)findViewById( R.id.publish );
        language = (TextView)findViewById( R.id.language );
        picture= (TextView)findViewById(R.id.picture);
        button2 = (Button)findViewById( R.id.button2 );

        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditEBook.this, "Clicked", Toast.LENGTH_LONG).show();
                ref = FirebaseDatabase.getInstance().getReference().child("EBook").child("1");
                ref.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        EBook ebook = new EBook();

                        ebook.setISBN( dataSnapshot.child( "isbn" ).getValue().toString() );
                        ebook.seteBookTitle( dataSnapshot.child( "eBookTitle" ).getValue().toString() );
                        ebook.setCategory( dataSnapshot.child( "category" ).getValue().toString() );
                        ebook.setAuthor( dataSnapshot.child( "author" ).getValue().toString() );
                        ebook.setEdition( dataSnapshot.child( "edition" ).getValue().toString() );
                        ebook.setPublishDate( dataSnapshot.child( "publishDate" ).getValue().toString() );
                        ebook.setLanguage( dataSnapshot.child( "language" ).getValue().toString() );
                        ebook.setPicture( dataSnapshot.child( "picture" ).getValue().toString() );


                        isbn.setText( ebook.getISBN() );
                        ebooktitle.setText( ebook.geteBookTitle() );
                        category.setText( ebook.getCategory() );
                        author.setText( ebook.getAuthor() );
                        edition.setText( ebook.getEdition() );
                        publish.setText( ebook.getPublishDate() );
                        language.setText( ebook.getLanguage() );
                        picture.setText( ebook.getPicture() );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

            }
        } );
    }
}
