package com.example.wagba.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.ViewModel.resturantViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ArrayList<meal> ordermeal= new ArrayList<meal>();
    ArrayList<restaurant> restaurantslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    orders order = new orders();
    profile profile = new profile();
    Home home = new Home();
    Cart cart = new Cart(ordermeal);

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profile).addToBackStack(null).commit();

                return true;

            case R.id.home:
                home.setOrdermeal(ordermeal);
                home.setRestaurantslist(restaurantslist);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
                return true;

            case R.id.orders:

                getSupportFragmentManager().beginTransaction().replace(R.id.container, order).addToBackStack(null).commit();
                return true;

            case R.id.cart_icon:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cart).addToBackStack(null).commit();
                return true;


        }
        return false;
    }
}