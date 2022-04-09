package com.example.myappforuniversity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
public class CustomAdapter  extends ArrayAdapter {
    private Activity context;
    private List<Student> studentList;
    //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    public CustomAdapter(Activity context,List<Student> studentList) {
        super(context,R.layout.sample_layout,studentList);
        this.context=context;
        this.studentList = studentList; }
    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
       View view= layoutInflater.inflate(R.layout.sample_layout,null,true);
       Student Student=studentList.get(position);
        TextView t1=view.findViewById(R.id.samplelayout1);
        TextView t2=view.findViewById(R.id.samplelayout2);
        t1.setText(Student.getName());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String formatted_date=sdf.format( Calendar.getInstance().getTime());
        t2.setText(formatted_date);
        //long x=new Date().getTime();
        //t2.setText(String.valueOf(x));

        return  view;}
}
