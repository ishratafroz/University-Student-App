package com.example.myappforuniversity.ui.courses;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myappforuniversity.ItemFragment;
import com.example.myappforuniversity.ItemFragment2;
import com.example.myappforuniversity.ItemFragment3;
import com.example.myappforuniversity.ItemFragment4;
import com.example.myappforuniversity.ItemFragment5;
import com.example.myappforuniversity.ItemFragment6;
import com.example.myappforuniversity.ItemFragment7;
import com.example.myappforuniversity.ItemFragment8;
import com.example.myappforuniversity.R;
import com.example.myappforuniversity.databinding.CoursesFragmentBinding;
public class CoursesFragment extends Fragment {
    private CoursesViewModel mViewModel;
    CoursesFragmentBinding binding;
    //private Button button11, button22, button33, button44, button55, button66, button77, button88;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CoursesFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment = new ItemFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment2 = new ItemFragment2();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment3 = new ItemFragment3();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment4 = new ItemFragment4();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment4);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment5 = new ItemFragment5();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment5);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment6 = new ItemFragment6();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment6);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment7 = new ItemFragment7();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment7);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ItemFragment8 = new ItemFragment8();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, ItemFragment8);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    public void onDestroy() {
        super.onDestroy();
    }

}
