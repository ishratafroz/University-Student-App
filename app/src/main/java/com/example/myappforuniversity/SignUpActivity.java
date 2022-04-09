package com.example.myappforuniversity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText signupe1, signupe2, signupe3,signupe4;
    private TextView signint1,signuptext1;
    private Button signupb1; String imageURI;
    CircleImageView c1; Uri imageUri; FirebaseStorage storage;
    private FirebaseAuth mAuth; DatabaseReference databaseReference;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance(); storage=FirebaseStorage.getInstance();
        c1=(CircleImageView) findViewById(R.id.profile_image1);
        signupe1 = (EditText) findViewById(R.id.signupedit1);
        signupe2 = (EditText) findViewById(R.id.signupedit2);
        signupe3 = (EditText) findViewById(R.id.signupedit3);
        signupe4 = (EditText) findViewById(R.id.signupedit4);
        signupb1 = (Button) findViewById(R.id.signupbutton1);
        signint1=(TextView) findViewById(R.id.signuptextid1);
        signuptext1=(TextView) findViewById(R.id.signuptextid11);
        progressbar=findViewById(R.id.progressbarid);
        signupb1.setOnClickListener(this);
        signint1.setOnClickListener(this); c1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signupbutton1:
                userRegister();
                break;
            case R.id.signuptextid1:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_image1:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1, "Select Picture"), 10); } }
    private void userRegister() {
        String email=signupe3.getText().toString().trim();
        String password=signupe4.getText().toString().trim();
        String name=signupe1.getText().toString().trim();
        String roll_number=signupe2.getText().toString().trim();
        if(email.isEmpty())
        {
            signupe3.setError("Enter an email address"); signupe3.requestFocus(); return;
        }
        if(name.isEmpty())
        {
            signupe3.setError("Please enter your name"); signupe3.requestFocus(); return;
        }
        if(roll_number.isEmpty())
        {
            signupe3.setError("Please enter your university given roll"); signupe3.requestFocus(); return;
        }
         if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signupe3.setError("Enter a valid email address"); signupe3.requestFocus(); return;
        } if(password.isEmpty())
        {
            signupe4.setError("Enter a password"); signupe4.requestFocus(); return;
        }
         if(password.length()<6)
         {
             signupe4.setError("Minimum length of a password should be 6"); signupe4.requestFocus();return;
         }

         progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>task) {
                        progressbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser=mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();
                            databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            StorageReference storageReference=storage.getReference("Users").child(userid);
                            if(imageUri!=null)
                            {
                                storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                              imageURI= uri.toString();
                                              Users users=new Users(userid,name,email,imageURI,roll_number);
                                              databaseReference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                  @Override
    public void onComplete(@NonNull @NotNull Task<Void> task) {
                   if(task.isSuccessful()) { Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                                                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                          startActivity(intent);  finish();
                                                          Toast.makeText(SignUpActivity.this,"User created...!",Toast.LENGTH_SHORT).show(); }
                                        else {Toast.makeText(SignUpActivity.this,"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show(); } }
                                              });
                                                }}); } }}); } else{
                                imageURI= "https://firebasestorage.googleapis.com/v0/b/myappforuniversity-e931a.appspot.com/o/profile.png?alt=media&token=b51c23ff-1894-471e-bd4a-f2190cceab4d";
                                Users users=new Users(userid,name,email,imageURI,roll_number);
                                databaseReference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if(task.isSuccessful()) { Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);  finish();
                                            Toast.makeText(SignUpActivity.this,"User created...!",Toast.LENGTH_SHORT).show(); }
                                        else {Toast.makeText(SignUpActivity.this,"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show(); } }
                                }); } } else {
            Toast.makeText(SignUpActivity.this,"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        } }}); }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        { case android.R.id.home:
                finish();
                break;
        } return true; }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10)
        { if(data!=null)
            { imageUri=data.getData(); c1.setImageURI(imageUri); }}}}