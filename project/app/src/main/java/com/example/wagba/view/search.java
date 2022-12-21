package com.example.wagba.view;

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
import android.widget.SearchView;

import com.example.wagba.R;
import com.example.wagba.database.card_restaurent_meal;
import com.example.wagba.database.meal;
import com.example.wagba.database.restaurant;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {


    RecyclerView recyclerView;
    ArrayList<card_restaurent_meal> searchresult;
    ArrayList<restaurant> restaurantslist;
    ArrayList<meal> mealslist ;
    SearchView searchView;
    search_adapter search_ad ;

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
                                                  for (com.example.wagba.database.meal meal : mealslist) {
                                                      if (pattern.matcher(meal.getName().toLowerCase()).find() || pattern.matcher(meal.getDetails().toLowerCase()).find()) {
                                                          searchresult.add(meal);
                                                      }
                                                  }
                                                  search_ad.notifyDataSetChanged();
                                                  Log.d("heyyyyy", searchresult.toString());
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
                    getParentFragmentManager().beginTransaction().replace(R.id.container, restaurant_page).commit();
                }
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