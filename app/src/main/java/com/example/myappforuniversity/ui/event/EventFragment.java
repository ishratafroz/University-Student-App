package com.example.myappforuniversity.ui.event;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
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

import com.example.myappforuniversity.R;
import com.example.myappforuniversity.Student;
import com.example.myappforuniversity.eventactivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.myappforuniversity.MainActivity.signine2;

public class EventFragment extends Fragment {
    private Button button,button2,button3;
    private EditText edittext1,edittext2,edittext3;
   DatabaseReference databaseReference;
     @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_fragment, container, false);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students");
        button = view.findViewById(R.id.savebuttonid);
        button2 = view.findViewById(R.id.savebutton2id);
        button3 = view.findViewById(R.id.savebutton3id);
        edittext1 = view.findViewById(R.id.editevent1); edittext2 = view.findViewById(R.id.editevent2);
        edittext3 = view.findViewById(R.id.editevent3);
        edittext1.addTextChangedListener(textWatcher);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mn=edittext2.getText().toString();
                String mg=edittext3.getText().toString();
                boolean installed=appInstalledOrNot("com.whatsapp");
                if(installed)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+88"+mn+"&text="+mg));
                    startActivity(intent);
                } else
                {Toast.makeText(getActivity(),"Whatsapp is not installed on your phone",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         Intent in=new Intent(getActivity(), eventactivity.class);
                startActivity(in);
                //Intent in1=new Intent(getActivity(), HomeFragment.class);
                //startActivity(in1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        }); return view;
    }
    private boolean appInstalledOrNot(String url)
    {
        PackageManager packageManager= getActivity().getPackageManager();
        boolean is_installed; try
    {
        getActivity().getPackageManager().getPackageInfo(url,PackageManager.GET_ACTIVITIES);
        is_installed=true;
    }catch(PackageManager.NameNotFoundException e) {is_installed=false;}
        return is_installed;
    }
    public void savedata() {
        String name = edittext1.getText().toString().trim();
       String key=databaseReference.push().getKey();
       Student student=new Student(name); databaseReference.child(key).setValue(student);
        Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG).show();
        edittext1.setText("");
    }
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          String s1= signine2.getText().toString().trim();
            button.setEnabled(s1.equals("1807043"));
         }
        @Override
        public void afterTextChanged(Editable s) { }
    };
}