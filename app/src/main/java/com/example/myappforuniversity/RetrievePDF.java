package com.example.myappforuniversity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RetrievePDF extends AppCompatActivity {
   ListView listView; DatabaseReference databaseReference; List<putPDF> uploadedPDF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pdf);
        listView=findViewById(R.id.retrievepdf1);uploadedPDF=new ArrayList<>(); retrievePDFFiles();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  putPDF putPDF = uploadedPDF.get(position);
                        Intent intent = new Intent();
                        intent.setData(Uri.parse(putPDF.getUrl()));
                        startActivity(intent); }
                });
    }
    private void retrievePDFFiles(){
        databaseReference= FirebaseDatabase.getInstance().getReference("uploadPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    putPDF putPDF=ds.getValue(com.example.myappforuniversity.putPDF.class);
                    uploadedPDF.add(putPDF); }
                String[] uploadsName=new String[uploadedPDF.size()];
                for(int i=0;i<uploadsName.length;i++)
                {uploadsName[i]=uploadedPDF.get(i).getName();  }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploadsName){
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view= super.getView(position, convertView, parent);
                    TextView textView=(TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(20); return view; } };
                    listView.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}