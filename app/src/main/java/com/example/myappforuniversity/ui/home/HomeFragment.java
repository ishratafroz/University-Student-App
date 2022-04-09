package com.example.myappforuniversity.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.MainActivity;
import com.example.myappforuniversity.R;
import com.example.myappforuniversity.Setting;
import com.example.myappforuniversity.chatActivity;

import static com.example.myappforuniversity.MainActivity.signine1;

public class HomeFragment extends Fragment {

    ImageView e1,e2,e3,e4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
         e1=view.findViewById(R.id.fab);
        e2=view.findViewById(R.id.homeimageView4);
        e3=view.findViewById(R.id.homeimageView3);
        e4=view.findViewById(R.id.homeimageView5);
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);startActivity(intent); }});
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e1=signine1.getText().toString().trim();
                Toast.makeText(getActivity(),"Welcome "+e1,Toast.LENGTH_LONG).show();
              }});
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Setting.class);startActivity(intent); }});
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), chatActivity.class);
                startActivity(intent);
            }});
        return view;
    }}