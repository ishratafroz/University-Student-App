package com.example.myappforuniversity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class selflearn extends AppCompatActivity {
    private Button b1; Toolbar toolbar;
    private EditText e1, e2;
 private FirebaseAuth mAuth; private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selflearn);
         toolbar = findViewById(R.id.toolbar2);
          b1 = (Button) findViewById(R.id.editself3);
        e1 = (EditText) findViewById(R.id.editself1);
        e2 = (EditText) findViewById(R.id.editself2);
        mAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notepad").child(mAuth.getCurrentUser().getUid());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=e1.getText().toString().trim();
                String content=e2.getText().toString().trim();
                if(title.isEmpty())
                {
                    e1.setError("Please put the title correctly");e1.requestFocus();return;
                }
                if(content.isEmpty())
                {
                    e2.setError("Please put the content above");e2.requestFocus();return;
                }

                    createNote(title,content);
            }
        });

    }
  private  void createNote(String title,String content)
  {
      if(mAuth.getCurrentUser()!=null) {
          final DatabaseReference newNoteRef=databaseReference.push();
          Map noteMap = new HashMap();
          noteMap.put("title", title);
          noteMap.put("content", content);
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
          String formatted_date=sdf.format( Calendar.getInstance().getTime());
          noteMap.put("timestamp",formatted_date);
          newNoteRef.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull @NotNull Task<Void> task) {
                  if (task.isSuccessful()) {
                      Toast.makeText(selflearn.this, "Note added to database", Toast.LENGTH_LONG).show();
                  } else {
                      Toast.makeText(selflearn.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                  }
              }
          });
      }
      else {
          Toast.makeText(this,"USERS IS NOT SIGNED IN",Toast.LENGTH_LONG).show();
      }
  }
}