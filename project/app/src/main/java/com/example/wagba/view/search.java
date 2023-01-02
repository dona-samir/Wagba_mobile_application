package com.example.wagba.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.wagba.R;
import com.example.wagba.Model.card_restaurent_meal;
import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class search extends Fragment {


    RecyclerView recyclerView;
    ArrayList<card_restaurent_meal> searchresult;
    ArrayList<restaurant> restaurantslist;
    ArrayList<meal> mealslist ;
    SearchView searchView;
    search_adapter search_ad ;
    ArrayList<meal> ordermeal;


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public ArrayList<restaurant> getRestaurantslist() {
        return restaurantslist;
    }

    public void setRestaurantslist(ArrayList<restaurant> restaurantslist) {
        this.restaurantslist = restaurantslist;
    }

    public ArrayList<meal> getMealslist() {
        return mealslist;
    }

    public void setMealslist(ArrayList<meal> mealslist) {
        this.mealslist = mealslist;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public ArrayList<card_restaurent_meal> getSearchresult() {
        return searchresult;
    }

    public void setSearchresult(ArrayList<card_restaurent_meal> searchresult) {
        this.searchresult = searchresult;
    }

    public search(ArrayList<meal> ordermeal) {
        this.ordermeal = ordermeal;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search_ad = new search_adapter(searchresult);
        recyclerView = view.findViewById(R.id.search_view);
        recyclerView.setAdapter(search_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));
        searchView = view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String s) {
                                                  searchresult.clear();
                                                  s = s.toLowerCase();
                                                  String regex = ".*" + s + "*";
                                                  Pattern pattern = Pattern.compile(regex);
                                                  for (restaurant res : restaurantslist) {
                                                      if (pattern.matcher(res.getName().toLowerCase()).find() || pattern.matcher(res.getDetails().toLowerCase()).find()) {
                                                          searchresult.add(res);
                                                      }
                                                  }
                                                  for (com.example.wagba.Model.meal meal : mealslist) {
                                                      if (pattern.matcher(meal.getName().toLowerCase()).find() || pattern.matcher(meal.getDetails().toLowerCase()).find()) {
                                                          searchresult.add(meal);
                                                      }
                                                  }
                                                  Collections.shuffle(searchresult);
                                                  search_ad.notifyDataSetChanged();
                                                  if(searchresult.isEmpty()){
                                                      nosearch nosearch = new nosearch();
                                                      getParentFragmentManager().beginTransaction().add(R.id.container , nosearch).addToBackStack(null).commit();

                                                  }
                                                  return false;
                                              }

                                              @Override
                                              public boolean onQueryTextChange(String s) {
                                                  return false;
                                              }

                                          });

        search_ad.setListener(new search_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (searchresult != null) {
                    restaurant_page restaurant_page = new restaurant_page();
                    if (searchresult.get(position) instanceof restaurant){
                        restaurant_page.setmParam1(restaurantslist.get(Integer.valueOf(((restaurant) searchresult.get(position)).getId())));
                    }else{
                        restaurant_page.setmParam1(restaurantslist.get(Integer.valueOf(((meal) searchresult.get(position)).getRestaurant_id())));
                    }
                    restaurant_page.setOrdermeal(ordermeal);
                    getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).commit();
                }
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    for(int i = 0; i < fm.getBackStackEntryCount()-1; ++i) {
                        fm.popBackStack();
                    }
                    BottomNavigationView bottomNavigationView;
                    bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.nav_bar);
                    bottomNavigationView.setSelectedItemId(R.id.home);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TestFailed","OnCreate");
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}