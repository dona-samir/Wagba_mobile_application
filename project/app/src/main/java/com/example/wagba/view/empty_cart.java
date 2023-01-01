package com.example.wagba.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link empty_cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class empty_cart extends Fragment {


    public empty_cart() {
        // Required empty public constructor
    }


    public static empty_cart newInstance(String param1, String param2) {
        empty_cart fragment = new empty_cart();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_cart, container, false);
    }
}