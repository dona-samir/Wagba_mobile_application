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
 * Use the {@link main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<restaurant> restaurants;
    RecyclerView recyclerView_meal;
    ArrayList<meal> meals ;


    public main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main.
     */
    // TODO: Rename and change types and number of parameters
    public static main newInstance(String param1, String param2) {
        main fragment = new main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurant_adapter res_ad = new restaurant_adapter(restaurants);
        recyclerView = view.findViewById(R.id.resturent_list);
        recyclerView.setAdapter(res_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));
        featured_meal_adapter meals_ad = new featured_meal_adapter(meals);
        recyclerView_meal = view.findViewById(R.id.featured_meal_list);
        recyclerView_meal .setAdapter(meals_ad);
        recyclerView_meal .setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL ,false));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        meals =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        meals.add(new meal("mighty zingear","fast food ",R.drawable.meal,60.50));
        meals.add(new meal("mighty zingear","fast food ",R.drawable.meal,90.9));
        meals.add(new meal("mighty zingear","fast food ",R.drawable.meal,150));
        meals.add(new meal("mighty zingear","fast food ",R.drawable.meal,150));
        restaurants =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.pizza_hut_bg));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        restaurants.add(new restaurant("kfc","fast food ",R.drawable.kfc));
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}