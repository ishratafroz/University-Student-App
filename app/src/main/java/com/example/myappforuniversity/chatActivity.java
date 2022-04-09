package com.example.myappforuniversity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class chatActivity extends AppCompatActivity {
 FirebaseAuth auth; RecyclerView minUserRecyclerView; UseAdapter adapter; FirebaseDatabase database;
 ArrayList<Users> usersArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat); auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
     usersArrayList=new ArrayList<>();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        String userid=firebaseUser.getUid();
        DatabaseReference reference=database.getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
           for(DataSnapshot dataSnapshot:snapshot.getChildren())
           { Users users=dataSnapshot.getValue(Users.class); usersArrayList.add(users); } adapter.notifyDataSetChanged(); }
           @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

           }});
    minUserRecyclerView=findViewById(R.id.userrecyclerview);
    minUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter=new UseAdapter(chatActivity.this,usersArrayList);
    minUserRecyclerView.setAdapter(adapter); }

}