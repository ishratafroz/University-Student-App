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
public class linkcreate extends AppCompatActivity {
    private ListView listview; private List<Class1> studentList;
    DatabaseReference databaseReference; private Customclass customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkcreate);
            databaseReference= FirebaseDatabase.getInstance().getReference("Class link");
            studentList=new ArrayList<>();
            customAdapter=new Customclass(linkcreate.this,studentList);
            listview=findViewById(R.id.listviewclass); }
        @Override
        protected void onStart() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot snapshot) {
                    studentList.clear();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        Class1 class1=dataSnapshot.getValue(Class1.class);
                        studentList.add(class1);
                    }
                    listview.setAdapter(customAdapter);
                }
                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                }
            });
            super.onStart();
        }
    }