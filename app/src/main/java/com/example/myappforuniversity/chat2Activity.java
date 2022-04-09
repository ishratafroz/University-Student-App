package com.example.myappforuniversity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
public class chat2Activity extends AppCompatActivity {
  String ReceiverImage,ReceiverUID,ReceiverName,SenderUID;
  CircleImageView c1; TextView receiverName;
    FirebaseDatabase database; FirebaseAuth mAuth;  ArrayList<Messages> messagesArrayList;
    public static String sImage,rImage; String senderRoom,recieverRoom; RecyclerView messageAdapter;
    MessagesAdapter adapter;CardView sendbtn;EditText editMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);c1=findViewById(R.id.profile_image4);
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        ReceiverName=getIntent().getStringExtra("name");
        ReceiverUID=getIntent().getStringExtra("Uid"); messagesArrayList=new ArrayList<>();
        ReceiverImage=getIntent().getStringExtra("ReceiverImage");receiverName=findViewById(R.id.tetx1);
        sendbtn=findViewById(R.id.chat2recycle4);
        editMessage=findViewById(R.id.chat2recycle3);
        messageAdapter=findViewById(R.id.chat2recycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);messageAdapter.setLayoutManager(linearLayoutManager);
        adapter=new MessagesAdapter(chat2Activity.this,messagesArrayList);
        messageAdapter.setAdapter(adapter);
        SenderUID=mAuth.getCurrentUser().getUid();
        senderRoom=SenderUID+ReceiverUID; recieverRoom=ReceiverUID+SenderUID;
        Picasso.get().load(ReceiverImage).into(c1);
        receiverName.setText(""+ReceiverName);
        DatabaseReference chatReference= database.getReference("CHATS").child(senderRoom).child("messages");
        DatabaseReference databaseReference= database.getReference("Users").child(mAuth.getCurrentUser().getUid());
     databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
     public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) { sImage= snapshot.child("image").getValue().toString(); rImage=ReceiverImage; }
                     @Override
    public void onCancelled(@NonNull @NotNull DatabaseError error) { }});
           chatReference.addValueEventListener(new ValueEventListener() {
     @Override
     public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) { messagesArrayList.clear();
      for(DataSnapshot dataSnapshot:snapshot.getChildren()) { Messages messages=dataSnapshot.getValue(Messages.class);
          messagesArrayList.add(messages); }adapter.notifyDataSetChanged(); }
     @Override
     public void onCancelled(@NonNull @NotNull DatabaseError error) { }
 });
 sendbtn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         String message=editMessage.getText().toString();if(message.isEmpty())
  { Toast.makeText(chat2Activity.this,"Please enter valid message",Toast.LENGTH_LONG).show();return; }
  editMessage.setText("");Date date=new Date();
     Messages messages=new Messages(message,SenderUID,date.getTime());
     database.getReference("CHATS").child(senderRoom).child("messages").push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull @NotNull Task<Void> task) {
             database.getReference("CHATS").child(recieverRoom).child("messages").push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull @NotNull Task<Void> task) { }}); }}); }}); }}