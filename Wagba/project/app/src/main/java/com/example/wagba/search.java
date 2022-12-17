package com.example.wagba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {


    RecyclerView recyclerView;
    ArrayList<restaurant> restaurants;
    public search() {
        // Required empty public constructor
    }

    public static search newInstance( ) {
        search fragment = new search();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search_adapter search_ad = new search_adapter(restaurants);
        recyclerView = view.findViewById(R.id.search_view);
        recyclerView.setAdapter(search_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restaurants =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}