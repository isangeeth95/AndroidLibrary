package com.example.ashimi.androidlibrary.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ashimi.androidlibrary.activities.MainActivity;
import com.example.ashimi.androidlibrary.models.Book;
import com.example.ashimi.androidlibrary.models.Borrowing;
import com.example.ashimi.androidlibrary.s_profile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class BorrowingDatabaseHelper {
    //private Context context;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Long maxID;
    //private Borrowing newBorrower;

    public BorrowingDatabaseHelper() {
        //this.context = context;
    }

    public void newBorrow(Borrowing newBorrower){
        //databaseReference=FirebaseDatabase.getInstance().getReference(Config.DATABASE_REFERENCE);
        //Config.showToast(Config.BOOK_ADDING_MESSAGE,context);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Borrowing");
        databaseReference.addValueEventListener(new ValueEventListener() {
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
//        int id=1;
//        databaseReference.child(String.valueOf( id )).child("user_name").setValue(user_name());
//        databaseReference.child(String.valueOf( id )).child("isbn").setValue(user_name);
        try{
            Borrowing brw = new Borrowing(newBorrower);


            brw.getISBN();
            brw.getBorrowerID();
            brw.getOutdate();

            databaseReference.child(String.valueOf(maxID + 1)).setValue(brw);
        }
        catch (Exception e){
            Log.e("Error", "- in borrowing helper"+e.getLocalizedMessage());

        }



//        databaseReference.child(String.valueOf(maxID + 1)).setValue(newBorrower);


    }
//    private String user_name(){
//       return "name";
//    }
}
