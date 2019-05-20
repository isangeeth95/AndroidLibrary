package com.example.ashimi.androidlibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class s_adminGetUserList extends AppCompatActivity {

    DatabaseReference mData;
    ListView listView;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_admin_get_user_list);

        listView = (ListView)findViewById(R.id.list_users);
        mData = FirebaseDatabase.getInstance().getReference().child("users");
        System.out.println("inside adminGetUserList.java");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("inside add value event listener");
                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    String details = ds.child("user_name").getValue()+" - "+ds.child("user_email").getValue()+" - "+ds.child("mode").getValue();
//                    System.out.println(details);
//
//                    arrayList.add(details);

                }
                adapter = new ArrayAdapter<String>(s_adminGetUserList.this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
