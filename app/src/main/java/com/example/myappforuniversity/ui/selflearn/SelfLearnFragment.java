package com.example.myappforuniversity.ui.selflearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.NoteRetrieveshow;
import com.example.myappforuniversity.R;
import com.example.myappforuniversity.selflearn;

public class SelfLearnFragment extends Fragment {
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.self_learn_fragment, container, false);
      Button button=(Button)view.findViewById(R.id.openactivity);
       TextView t1=(TextView) view.findViewById(R.id.openactivity2);
       Button button2=(Button)view.findViewById(R.id.openactivity3);
       EditText e1=view.findViewById(R.id.openactivity4);
       Button e2=view.findViewById(R.id.openactivity5);
       e1.setText("https://drive.google.com/drive/u/0/folders/1DFwPLm2m-DdImonfwD78P3Ord2PPjj4C");
       e2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s1=e1.getText().toString().trim();
               Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(s1));
               startActivity(intent);
           }
       });
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity(), selflearn.class);
                startActivity(in);
            }
        });
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
          Intent in=new Intent(getActivity(), NoteRetrieveshow.class); startActivity(in);
           }
       });
       return view;
    }
}
