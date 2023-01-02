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
import android.widget.SearchView;

import com.example.wagba.Model.card_restaurent_meal;
import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.ViewModel.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;


public class Home extends Fragment {

    RecyclerView recyclerView;
    ArrayList<restaurant> restaurantslist;
    resturantViewModel resturantViewModel;
    restaurant_adapter res_ad;
    ArrayList<meal> mealslist ;
    RecyclerView recyclerView_meal;
    mealViewModel mealViewModel;
    featured_meal_adapter meals_ad;
    SearchView searchView;
    ArrayList<meal> ordermeal;



    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    public ArrayList<meal> getOrdermeal() {
        return ordermeal;
    }

    public void setOrdermeal(ArrayList<meal> ordermeal) {
        this.ordermeal = ordermeal;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<restaurant> getRestaurantslist() {
        return restaurantslist;
    }

    public void setRestaurantslist(ArrayList<restaurant> restaurantslist) {
        this.restaurantslist = restaurantslist;
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
                if(search.isEmpty()){
                    nosearch nosearch = new nosearch();
                    getParentFragmentManager().beginTransaction().replace(R.id.container , nosearch).addToBackStack(null).commit();
                    return false;
                }else {
                    search searchpage = new search(ordermeal);
                    searchpage.setRestaurantslist(restaurantslist);
                    searchpage.setMealslist(mealslist);
                    Collections.shuffle(search);
                    searchpage.setSearchresult(search);
                    getParentFragmentManager().beginTransaction().replace(R.id.container, searchpage).addToBackStack(null).commit();
                    return false;
                }
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
        resturantViewModel = new ViewModelProvider(Home.this).get(resturantViewModel.class);
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
                            restaurant_page.setOrdermeal(ordermeal);
                            getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).addToBackStack(null).commit();
                        }
                    }
                });
            }
        });

        mealViewModel = new ViewModelProvider(Home.this).get(mealViewModel.class);
        mealViewModel.getAllData();
        meals_ad = new featured_meal_adapter();
        mealViewModel.getMeals().observe(getViewLifecycleOwner(), new Observer<ArrayList<meal>>() {
            @Override
            public void onChanged(ArrayList<meal> meals) {
                mealslist = meals;
                Collections.shuffle(meals);
                meals_ad.setList(meals);
                meals_ad.notifyDataSetChanged();
                meals_ad.setListener(new featured_meal_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (meals != null) {
                            restaurant_page restaurant_page = new restaurant_page();
                            restaurant_page.setmParam1(restaurantslist.get((Integer.valueOf(meals.get(position).getRestaurant_id()))));
                            restaurant_page.setOrdermeal(ordermeal);

                            getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).addToBackStack(null).commit();
                        }
                    }
                });
            }
        });

        View view = inflater.inflate(R.layout.fargment_home, container, false);
        return view;
    }
}