package com.example.myappforuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
  public static EditText signine1, signine2, signine3;
  private TextView signupt1;
  private Button signinb1;
  ProgressBar progressbar;
  private FirebaseAuth mAuth;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().hide();
    mAuth = FirebaseAuth.getInstance();
    signine1 = (EditText) findViewById(R.id.signinedit1);
    signine2 = (EditText) findViewById(R.id.signinedit2);
    signine3 = (EditText) findViewById(R.id.signinedit3);
    signinb1 = (Button) findViewById(R.id.signinbutton1);
    signupt1 = (TextView) findViewById(R.id.signintextid1);
    signinb1.setOnClickListener(this); progressbar=findViewById(R.id.progressbarid);
    signupt1.setOnClickListener(this);
    }
  @Override
  public void onClick(View v) {
    switch (v.getId())
    {
      case R.id.signinbutton1:
        userLogin();
        break;

      case R.id.signintextid1:
        Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
        break;
    }
  }
  private void userLogin() {
    String email=signine1.getText().toString().trim();
    String password=signine3.getText().toString().trim();
    String roll_number=signine2.getText().toString().trim();
    if(email.isEmpty())
    {
      signine1.setError("Enter an email address"); signine1.requestFocus(); return;
    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
    {
      signine1.setError("Enter a valid email address"); signine1.requestFocus(); return;
    } if(password.isEmpty())
    {
      signine3.setError("Enter a password"); signine3.requestFocus(); return;
    }
    if(password.length()<6)
    {
      signine3.setError("Minimum length of a password should be 6"); return;
    }
    progressbar.setVisibility(View.VISIBLE);
    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
        progressbar.setVisibility(View.GONE);
        if(task.isSuccessful())
        {  //Toast.makeText(MainActivity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
          Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(intent);
          finish();Toast.makeText(MainActivity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
        }
        else {
          Toast.makeText(MainActivity.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     super.onOptionsItemSelected(item);
     switch (item.getItemId())
     {
       case android.R.id.home:
         finish();
         break;
     } return true;

  }
}