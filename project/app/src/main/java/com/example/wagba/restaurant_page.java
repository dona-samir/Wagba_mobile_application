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
 * Use the {@link restaurant_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class restaurant_page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<meal> meals ;

    public restaurant_page() {
        // Required empty public constructor
    }


    public static restaurant_page newInstance(String param1, String param2) {
        restaurant_page fragment = new restaurant_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meal_adapter meals_ad = new meal_adapter(meals);
        recyclerView = view.findViewById(R.id.meals_listview);
        recyclerView.setAdapter(meals_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        meals =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        meals.add(new meal("kfc","fast food ",R.drawable.meal,60.50));
        meals.add(new meal("kfc","fast food ",R.drawable.meal,90.9));
        meals.add(new meal("kfc","fast food ",R.drawable.meal,150));
        View view = inflater.inflate(R.layout.fragment_restuaurant_page, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}