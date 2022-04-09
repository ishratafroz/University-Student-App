package com.example.myappforuniversity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
public class Setting extends AppCompatActivity {
    CircleImageView setting_image; EditText setting_name,setting_status,e1; FirebaseAuth auth; Button b1,b2;
    FirebaseDatabase database; FirebaseStorage storage; Uri selctedImageUri; String email;Dialog dialog;ImageButton b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        dialog=new Dialog(Setting.this);
        auth=FirebaseAuth.getInstance(); database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        setting_image=findViewById(R.id.profile_image6);
        e1=findViewById(R.id.settingedittext);
        e1.setText("https://m.facebook.com/groups/419300751943885/?tsid=0.40974706343166956&source=result");
        setting_name=findViewById(R.id.settingeditname);setting_status=findViewById(R.id.settingeditroll);
    b1=findViewById(R.id.settingeditbutton); b2=findViewById(R.id.settingeditbutton2);
    b3=findViewById(R.id.settingeditbutton3);
        DatabaseReference reference=database.getReference("Users").child(auth.getCurrentUser().getUid());
        StorageReference storageReference=storage.getReference("uplod").child(auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
           String name=snapshot.child("name").getValue().toString();
                email=snapshot.child("email").getValue().toString();
                String imageURI=snapshot.child("image").getValue().toString();
                String roll_number=snapshot.child("roll").getValue().toString();
                setting_name.setText(name);
                setting_status.setText(roll_number);
                Picasso.get().load(imageURI).into(setting_image);
                 }@Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    String name= setting_name.getText().toString();
     String roll_number=setting_status.getText().toString();
     if(selctedImageUri!=null)
     {
         storageReference.putFile(selctedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                 storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {
                    String finalImageUri=uri.toString();
                         Users users=new Users(auth.getCurrentUser().getUid(),name,email,finalImageUri,roll_number);
 reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
     @Override
     public void onComplete(@NonNull @NotNull Task<Void> task) {
    if(task.isSuccessful())
    {
        Toast.makeText(Setting.this,"Data Successfully Updated",Toast.LENGTH_LONG).show();
        startActivity(new Intent(Setting.this,MainActivity2.class));
    } else {
        Toast.makeText(Setting.this,"Something went wrong",Toast.LENGTH_LONG).show();
    }
     }
 }); } });}});
     }
     else {
         storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                 String finalImageUri=uri.toString();
                 Users users=new Users(auth.getCurrentUser().getUid(),name,email,finalImageUri,roll_number);
                 reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull @NotNull Task<Void> task) {
                         if(task.isSuccessful())
                         {
                             Toast.makeText(Setting.this,"Data Successfully Updated",Toast.LENGTH_LONG).show();
                             startActivity(new Intent(Setting.this,MainActivity2.class));
                         } else {
                             Toast.makeText(Setting.this,"Something went wrong",Toast.LENGTH_LONG).show();
                         }
                     }
                 });
             }
         });
     }
     }
    });
b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String s1=e1.getText().toString().trim();
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(s1));
             startActivity(intent); } });
 b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TableLayout t1=dialog.findViewById(R.id.tableLayoutsetting);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Toast.makeText(Setting.this,"Routine",Toast.LENGTH_LONG).show();
        dialog.show();
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(false); // dialog.show();
    }});
setting_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent = new Intent();
            intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10); }}); }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        { if(data!=null)
        { selctedImageUri=data.getData(); setting_image.setImageURI(selctedImageUri); }}}}