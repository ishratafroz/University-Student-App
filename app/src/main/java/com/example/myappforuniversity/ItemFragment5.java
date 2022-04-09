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
public class ItemFragment5 extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] subject_name = {"CSE 3100","CSE 3101","CSE 3103","CSE 3104","CSE 3109","CSE 3110","CSE 3119","CSE 3120","ECE 3115","ECE 3116"};
        ListView listview = (ListView) view.findViewById(R.id.list1id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Toast.makeText(getActivity(), "Web Programming Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 1) {
            Toast.makeText(getActivity(), "Theory of Computation", Toast.LENGTH_SHORT).show();
        }
        if (position == 2) {
            Toast.makeText(getActivity(), "Peripherals and Interfacing", Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            Toast.makeText(getActivity(), "Peripherals and Interfacing Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            Toast.makeText(getActivity(), "Database Systems", Toast.LENGTH_SHORT).show();
        }
        if (position == 5) {
            Toast.makeText(getActivity(), "Database Systems Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 6) {
            Toast.makeText(getActivity(), "Software Engineering and Information Systems", Toast.LENGTH_SHORT).show();
        }
        if (position == 7) {
            Toast.makeText(getActivity(), "Software Engineering & Information Systems Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 8) {
            Toast.makeText(getActivity(), "Data Communication ", Toast.LENGTH_SHORT).show();
        }
        if(position==9){
            Toast.makeText(getActivity(), "Data Communication Laboratory ", Toast.LENGTH_SHORT).show();
        }
    }
}