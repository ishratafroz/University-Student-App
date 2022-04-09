package com.example.myappforuniversity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class eventactivity extends AppCompatActivity {
    private ListView listview; private List<Student> studentList;
    private CustomAdapter customAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventactivity);
    databaseReference= FirebaseDatabase.getInstance().getReference("Students");
        studentList=new ArrayList<>();
        customAdapter=new CustomAdapter(this,studentList);
        listview = findViewById(R.id.text_home1);
    }
    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                studentList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Student student=dataSnapshot.getValue(Student.class);
                    studentList.add(student);
                } listview.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
        super.onStart();
    }
}