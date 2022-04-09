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
public class ItemFragment2 extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] subject_name = {"CSE 1201", "CSE 1202", "CSE 1203", "CHEM 1207", "CSE 1204", "CHEM 1208", "EEE 1217", "EEE 1218", "MATH 1207","ME 1270"};
        ListView listview = (ListView) view.findViewById(R.id.list1id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Toast.makeText(getActivity(), "Structured Programming", Toast.LENGTH_SHORT).show();
        }
        if (position == 1) {
            Toast.makeText(getActivity(), "Structured Programming Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 2) {
            Toast.makeText(getActivity(), "Digital Logic Design", Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            Toast.makeText(getActivity(), "Chemistry", Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            Toast.makeText(getActivity(), "Digital Logic Design Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 5) {
            Toast.makeText(getActivity(), "Chemistry Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 6) {
            Toast.makeText(getActivity(), "Analog Electronics", Toast.LENGTH_SHORT).show();
        }
        if (position == 7) {
            Toast.makeText(getActivity(), "Analog Electronics Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 8) {
            Toast.makeText(getActivity(), "Coordinate Geometry and Differential Equations", Toast.LENGTH_SHORT).show();
        }
        if (position == 9) {
            Toast.makeText(getActivity(), "Computer Aided Design Laboratory", Toast.LENGTH_SHORT).show();
        }
    }
}



