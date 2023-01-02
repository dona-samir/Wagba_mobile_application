package com.example.wagba.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.ViewModel.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    private ArrayList<meal> ordermeal;


    public restaurant_page(){

    }
    public static restaurant_page newInstance(restaurant restaurant,ArrayList<meal> ordermeal ) {
        restaurant_page fragment = new restaurant_page();
        Bundle args = new Bundle();
        args.putString("res", restaurant.toString());
        args.putString("meal", ordermeal.toString());
        fragment.setArguments(args);
        return fragment;
    }
    public ArrayList<meal> getOrdermeal() {

        return ordermeal;
    }

    public void setOrdermeal(ArrayList<meal> ordermeal) {
        if(ordermeal!=null ){
            if(!ordermeal.isEmpty()){
                if(ordermeal.get(0).getRestaurant_id() !=restaurant.getId()){
                    ordermeal.clear();
                }
            }
        }
        this.ordermeal = ordermeal;
    }
    public void setmParam1(restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            restaurant_page fragment = new restaurant_page();
            fragment.setOrdermeal(ordermeal);
            this.restaurant =( restaurant) savedInstanceState.getSerializable("restaurant");
            fragment.setmParam1(restaurant);
            getParentFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
            getParentFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }

    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("restaurant",restaurant);
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


        mealViewModel = new ViewModelProvider(restaurant_page.this).get(mealViewModel.class);
        mealViewModel.getAllData(restaurant.getId());
        meals_ad = new meal_adapter();
        mealViewModel.getMeals().observe(getViewLifecycleOwner(), new Observer<ArrayList<meal>>() {
            @Override
            public void onChanged(ArrayList<meal> meals) {
                Log.d("meals0",meals.toString());
                meals_ad.setList(meals);
                meals_ad.notifyDataSetChanged();
                meals_ad.setListener(new meal_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if(meals!=null) {
                            if (meals.get(position).getAvailable() == 0) {
                                Toast.makeText(getActivity(), "sorry this " +meals.get(position).getName() +"is not available" , Toast.LENGTH_LONG).show();

                            } else {
                                ordermeal.add(meals.get(position));
                                Toast.makeText(getActivity(), meals.get(position).getName() + " add to cart", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });


        View view = inflater.inflate(R.layout.fragment_restuaurant_page, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}