package com.example.myappforuniversity.ui.classlink;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.Class1;
import com.example.myappforuniversity.R;
import com.example.myappforuniversity.linkcreate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.myappforuniversity.MainActivity.signine2;

public class ClassLinkFragment extends Fragment {
    Button b1,b2;     EditText e1, e2;
    //FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String subject, link;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_link_fragment, container, false);
        b1 = view.findViewById(R.id.editsubjlink3);
        e1 = view.findViewById(R.id.editsubjlink);
        e2 = view.findViewById(R.id.editsubjlink2);
        b2=view.findViewById(R.id.editsubjlink4);
        e1.addTextChangedListener(textWatcher);
        e2.addTextChangedListener(textWatcher);
        //mAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Class link");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"a",Toast.LENGTH_LONG).show();
                Intent in=new Intent(getActivity(), linkcreate.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = e1.getText().toString().trim();
                link = e2.getText().toString().trim();
                //String link2="<a href="+'"'+link+'"'+">"+link+"</a>";
                if (!TextUtils.isEmpty(subject) && !TextUtils.isEmpty(link)) {
                    //id = database_student.push().getKey();
                    Class1 student = new Class1(subject, link);
                    databaseReference.child(subject).setValue(student);
                    Toast.makeText(getActivity(), "Added.", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                } else {
                    Toast.makeText(getActivity(), "Fill up correctly please", Toast.LENGTH_LONG).show();
                }
            }
        });
     return view;
}
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String s1= signine2.getText().toString().trim();
            b1.setEnabled(s1.equals("1807043"));
        }
        @Override
        public void afterTextChanged(Editable s) { }
    };





}