package com.example.wagba.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wagba.R;
import com.example.wagba.ViewModel.*;
import com.example.wagba.database.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class restaurant_page extends Fragment {



    // TODO: Rename and change types of parameters
    private restaurant restaurant;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<meal> meals ;
    TextView name,details , delivery;
    ImageView img;
    mealViewModel mealViewModel;
    meal_adapter meals_ad;

    public restaurant_page() {
        // Required empty public constructor
    }

    public void setmParam1(restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img= view.findViewById(R.id.resturant_page_img);
        name= view.findViewById(R.id.resturant_page_title);
        details= view.findViewById(R.id.resturant_page_details);
        delivery= view.findViewById(R.id.restaurant_page_delivery_price);
        Picasso.get().load(restaurant.getImg_bg()).into(img);
        name.setText(restaurant.getName());
        delivery.setText(restaurant.getDelivery_fee());
        details.setText(restaurant.getDetails());

        recyclerView = view.findViewById(R.id.meals_listview);
        recyclerView.setAdapter(meals_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mealViewModel = new ViewModelProvider(restaurant_page.this).get(mealViewModel.class);
        mealViewModel.getAllData(restaurant.getId());
        meals_ad = new meal_adapter();
        mealViewModel.getMeals().observe(getViewLifecycleOwner(), new Observer<ArrayList<meal>>() {
            @Override
            public void onChanged(ArrayList<meal> meals) {
                meals_ad.setList(meals);
                meals_ad.notifyDataSetChanged();
            }
        });
        View view = inflater.inflate(R.layout.fragment_restuaurant_page, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}