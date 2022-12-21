package com.example.wagba.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.wagba.R;
import com.example.wagba.ViewModel.*;
import com.example.wagba.database.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.GenericTypeIndicator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class main extends Fragment {

    RecyclerView recyclerView;
    ArrayList<restaurant> restaurantslist;
    resturantViewModel resturantViewModel;
    restaurant_adapter res_ad;
    ArrayList<meal> mealslist ;
    RecyclerView recyclerView_meal;
    mealViewModel mealViewModel;
    featured_meal_adapter meals_ad;
    SearchView searchView;


    public main() {
        // Required empty public constructor
    }

    public static main newInstance() {
        main fragment = new main();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.resturent_list);
        recyclerView.setAdapter(res_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));

        recyclerView_meal = view.findViewById(R.id.featured_meal_list);
        recyclerView_meal .setAdapter(meals_ad);
        recyclerView_meal .setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL ,false));
        searchView = view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<card_restaurent_meal> search = new ArrayList<card_restaurent_meal>();
                s = s.toLowerCase();
                String regex = ".*"+s+"*" ;
                Pattern pattern = Pattern.compile(regex);
                for (restaurant res:restaurantslist){
                    if ( pattern.matcher(res.getName().toLowerCase()).find() || pattern.matcher(res.getDetails().toLowerCase()).find()) {
                        search.add(res);
                    }
                }
                for (meal meal:mealslist){
                    if ( pattern.matcher(meal.getName().toLowerCase()).find() || pattern.matcher(meal.getDetails().toLowerCase()).find()) {
                        search.add(meal);
                    }
                }
                search searchpage = new search();
                searchpage.setRestaurantslist(restaurantslist);
                searchpage.setMealslist(mealslist);
                searchpage.setSearchresult(search);
                getParentFragmentManager().beginTransaction().replace(R.id.container, searchpage).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        resturantViewModel = new ViewModelProvider(main.this).get(resturantViewModel.class);
        resturantViewModel.getAllData();
        res_ad = new restaurant_adapter();
        resturantViewModel.getResturants().observe(getViewLifecycleOwner(), new Observer<ArrayList<restaurant>>() {
            @Override
            public void onChanged(ArrayList<restaurant> restaurants) {
                restaurantslist = restaurants;
                res_ad.setList(restaurants);
                res_ad.notifyDataSetChanged();
                res_ad.setListener(new restaurant_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (restaurants != null) {
                            restaurant_page restaurant_page = new restaurant_page();
                            restaurant_page.setmParam1(restaurants.get(position));
                            getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).commit();
                        }
                    }
                });
            }
        });

        mealViewModel = new ViewModelProvider(main.this).get(mealViewModel.class);
        mealViewModel.getAllData();
        meals_ad = new featured_meal_adapter();
        mealViewModel.getMeals().observe(getViewLifecycleOwner(), new Observer<ArrayList<meal>>() {
            @Override
            public void onChanged(ArrayList<meal> meals) {
                mealslist = meals;
                meals_ad.setList(meals);
                meals_ad.notifyDataSetChanged();
                meals_ad.setListener(new featured_meal_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (meals != null) {
                            restaurant_page restaurant_page = new restaurant_page();
                            restaurant_page.setmParam1(restaurantslist.get((Integer.valueOf(meals.get(position).getRestaurant_id()))));
                            getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).commit();
                        }
                    }
                });
            }
        });

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}