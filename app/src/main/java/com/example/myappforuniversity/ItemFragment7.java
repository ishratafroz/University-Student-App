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
public class ItemFragment7 extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] subject_name = {"CSE 4000", "CSE 4105", "CSE 4106", "CSE 4109", "CSE 4110", "CSE 4111", "CSE 4112", "CSE 4115", "CSE 4116", "IEM 4127"};
        ListView listview = (ListView) view.findViewById(R.id.list1id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Toast.makeText(getActivity(), "System Development Project", Toast.LENGTH_SHORT).show();
        }
        if (position == 1) {
            Toast.makeText(getActivity(), "Operating Systems", Toast.LENGTH_SHORT).show();
        }
        if (position == 2) {
            Toast.makeText(getActivity(), "Operating Systems Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            Toast.makeText(getActivity(), "Applied Statistics and Queuing Theory", Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            Toast.makeText(getActivity(), " Compiler Design", Toast.LENGTH_SHORT).show();
        }
        if (position == 5) {
            Toast.makeText(getActivity(), "Compiler Design Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 6) {
            Toast.makeText(getActivity(), "Mobile Computing", Toast.LENGTH_SHORT).show();
        }
        if (position == 7) {
            Toast.makeText(getActivity(), "Mobile Computing Laboratory", Toast.LENGTH_SHORT).show();
        }
        if (position == 8) {
            Toast.makeText(getActivity(), "Sociology and Government", Toast.LENGTH_SHORT).show();
        }
    }
}