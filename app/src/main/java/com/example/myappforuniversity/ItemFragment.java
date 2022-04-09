package com.example.myappforuniversity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment implements AdapterView.OnItemClickListener{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] subject_name={"CSE 1100","CSE 1107", "EEE 1107", "EEE 1108", "HUM 1107","HUM 1108", "MATH 1107", "PHY 1107","PHY 1108"};
        ListView listview=(ListView) view.findViewById(R.id.list1id);
        ArrayAdapter<String>adapter= new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,subject_name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
}@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0)
        {
            Toast.makeText(getActivity(),"Introduction to Computer Systems",Toast.LENGTH_SHORT).show();
        }
        if(position==1 )
        {
            Toast.makeText(getActivity(),"Discrete Mathematics",Toast.LENGTH_SHORT).show();
        }
        if(position== 2)
        {
            Toast.makeText(getActivity(),"Basic Electrical Engineering",Toast.LENGTH_SHORT).show();
        }if(position== 3)
        {
            Toast.makeText(getActivity(),"Basic Electrical Engineering Laboratory",Toast.LENGTH_SHORT).show();
        }if(position== 4)
        {
            Toast.makeText(getActivity(),"English and Human Communication",Toast.LENGTH_SHORT).show();
        }if(position== 5)
        {
            Toast.makeText(getActivity(),"English Skills Laboratory",Toast.LENGTH_SHORT).show();
        }if(position== 6)
        {
            Toast.makeText(getActivity(),"Differential and Integral Calculus",Toast.LENGTH_SHORT).show();
        }if(position== 7)
        {
            Toast.makeText(getActivity(),"Physics",Toast.LENGTH_SHORT).show();
        }
        if(position== 8)
        {
            Toast.makeText(getActivity(),"Physics Laboratory",Toast.LENGTH_SHORT).show();
        }
    }

}