package com.example.myappforuniversity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Customclass  extends ArrayAdapter {
    private Activity context;
    private List<Class1> studentsList;
    public Customclass(Activity context, List<Class1>studentsList) {
        super(context, R.layout.sample_layout2,studentsList);
        this.context = context;
        this.studentsList = studentsList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout2, null, true);
        Class1 students = studentsList.get(position);
        TextView t1 = view.findViewById(R.id.name1);
        t1.setText("SUB :"+students.getSubject());
        t1.isTextSelectable();
         TextView t3 =view.findViewById(R.id.cgpa1);
        t3.setText("LINK  :"+students.getLink());
        t3.isTextSelectable();
        return view;
    }
}
