package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEBook extends AppCompatActivity {
    EditText ISBN,EBookTitle,Category,Author,Edition,PublishDate,Language,Picture;
    Button AddEbook;
    DatabaseReference ref;
    long maxID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ebook);

        ISBN = findViewById(R.id.ISBN);
        EBookTitle = findViewById(R.id.EBookTitle);
        Category = findViewById(R.id.Category);
        Author = findViewById(R.id.Author);
        Edition = findViewById(R.id.Edition);
        PublishDate = findViewById(R.id.PublishDate);
        Language = findViewById(R.id.Language);
        Picture = findViewById(R.id.Picture);
        AddEbook = findViewById(R.id.AddEbook);

        ref = FirebaseDatabase.getInstance().getReference().child("EBook");

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

        AddEbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ISBN.getText().toString().isEmpty()&& EBookTitle.getText().toString().isEmpty()&& Category.getText().toString().isEmpty()&&
                        Author.getText().toString().isEmpty()&& Edition.getText().toString().isEmpty()&& PublishDate.getText().toString().isEmpty()&&
                        Language.getText().toString().isEmpty()&& Picture.getText().toString().isEmpty()){
                    Toast.makeText(AddEBook.this,"Cannot have Emty Fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    try {

                        EBook ebook = new EBook();

                        ebook.setISBN(ISBN.getText().toString());
                        ebook.seteBookTitle(EBookTitle.getText().toString());
                        ebook.setCategory(Category.getText().toString());
                        ebook.setAuthor(Author.getText().toString());
                        ebook.setEdition(Edition.getText().toString());
                        ebook.setPublishDate(PublishDate.getText().toString());
                        ebook.setLanguage(Language.getText().toString());
                        ebook.setPicture(Picture.getText().toString());

                        ebook.getISBN();
                        ebook.geteBookTitle();
                        ebook.getCategory();
                        ebook.getAuthor();
                        ebook.getEdition();
                        ebook.getPublishDate();
                        ebook.getLanguage();
                        ebook.getPicture();

                        ref.child(String.valueOf(maxID + 1)).setValue(ebook);

                        Toast.makeText(AddEBook.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Log.e("Error", e.getLocalizedMessage());
                        Toast.makeText(AddEBook.this,"error"+ e, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}