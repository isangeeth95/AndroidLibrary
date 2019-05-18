package com.example.ashimi.androidlibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Inventory_test extends AppCompatActivity {

    EditText id,name,location;
    Button buttonAdd;
    Spinner spinnerCat;

    DatabaseReference databaseReference;
//    ListView listViewBook;
//    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.inventory_test );

        databaseReference = FirebaseDatabase.getInstance().getReference("Book");

        id = (EditText)findViewById( R.id.testbookID ) ;
        name = (EditText)findViewById( R.id.testbookName );
        location=(EditText)findViewById( R.id.testbookLocation ) ;
        buttonAdd = (Button)findViewById( R.id.addcat );
        spinnerCat=(Spinner)findViewById( R.id.category );

//        bookList = new ArrayList<>(  );

        buttonAdd.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                addBook();
            }
        } );
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        databaseReference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                bookList.clear();
//
//                for(DataSnapshot bookSnapshot1 : dataSnapshot.getChildren()){
//                    Book book = bookSnapshot1.getValue(Book.class);
//
//                    bookList.add( book );
//                }
//                Inventory_list adapter = new Inventory_list( Inventory_test.this, bookList );
//                listViewBook.setAdapter( adapter );
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        } );
//    }

    private void addBook(){
        String bookID= id.getText().toString().trim();
        String bookName = name.getText().toString().trim();
        String loc = location.getText().toString().trim();
        String category = spinnerCat.getSelectedItem().toString();

        if(!TextUtils.isEmpty( bookID )){
            String id = databaseReference.push().getKey();
            Book book = new Book( bookID, bookName,loc,category );
            databaseReference.child(id).setValue( book );
            Toast.makeText( this,"Added book", Toast.LENGTH_LONG ).show();
        }
        else{
            Toast.makeText( this, "You should enter a name", Toast.LENGTH_LONG ).show();
        }
    }
}


































