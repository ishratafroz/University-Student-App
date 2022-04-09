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
public class ItemFragment8 extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] subject_name = {"CSE 4000","CSE 4207","CSE 4208","CSE 4211","CSE 4212","CSE 4215","CSE 4239","HUM 4207","IEM 4227"};
        ListView listview = (ListView) view.findViewById(R.id.list1id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Toast.makeText(getActivity(), "Project /Thesis", Toast.LENGTH_SHORT).show();
        }
        if (position == 1) {
            Toast.makeText(getActivity(), "Computer Graphics ", Toast.LENGTH_SHORT).show();
        }
        if (position == 2) {
            Toast.makeText(getActivity(), "Computer Graphics Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            Toast.makeText(getActivity(), "Machine Learning", Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            Toast.makeText(getActivity(), "Machine Learning Laboratory ", Toast.LENGTH_SHORT).show();
        }
        if (position == 5) {
            Toast.makeText(getActivity(), "Information Security and Control", Toast.LENGTH_SHORT).show();
        }
        if (position == 6) {
            Toast.makeText(getActivity(), "Data Mining", Toast.LENGTH_SHORT).show();
        }
        if (position == 7) {
            Toast.makeText(getActivity(), "Sociology and Government", Toast.LENGTH_SHORT).show();
        }
        if (position == 8) {
            Toast.makeText(getActivity(), "Industrial Management ", Toast.LENGTH_SHORT).show();
        }
    }
}