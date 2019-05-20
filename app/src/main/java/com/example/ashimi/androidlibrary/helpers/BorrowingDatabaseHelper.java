package com.example.ashimi.androidlibrary.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.ashimi.androidlibrary.activities.MainActivity;
import com.example.ashimi.androidlibrary.models.Book;
import com.example.ashimi.androidlibrary.models.Borrowing;
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

    public void newBorrow(final Borrowing newBorrower){
        //databaseReference=FirebaseDatabase.getInstance().getReference(Config.DATABASE_REFERENCE);
        //Config.showToast(Config.BOOK_ADDING_MESSAGE,context);

        databaseReference=FirebaseDatabase.getInstance().getReference(Config.DATABASE_REFERENCE).child("Borrowing");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                maxID = (long) 1;
                if(dataSnapshot.exists()){
                    maxID = (dataSnapshot.getChildrenCount());

                }
//                databaseReference.child(String.valueOf(maxID + 1)).setValue(newBorrower);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(maxID == null){
            maxID = (long) 0;
        }
        databaseReference.child(String.valueOf(maxID + 1)).setValue(newBorrower);


    }
}
