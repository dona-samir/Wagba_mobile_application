package com.example.wagba.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.meal_order;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.Model.meal;
import com.example.wagba.ViewModel.cartViewModel;
import com.example.wagba.ViewModel.resturantViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Cart extends Fragment {

    private ArrayList<restaurant> restaurants_list;
    RecyclerView recyclerView;
    ArrayList<meal> ordermeals ;
    TextView name,cart_total , delivery,total;
    cart_data cart;
    resturantViewModel resturantViewModel;
    Map<meal, Long> counted;
    Button checkout;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseUser user;
    cartViewModel cartViewModel;
    restaurant restaurant;


    public Cart() {
        // Required empty public constructor

    }

    public Cart( ArrayList<meal> ordermeals) {
        //this.restaurants = restaurant;
        resturantViewModel = new resturantViewModel();
        this.ordermeals = ordermeals;
    }

    public static Cart newInstance(ArrayList<meal> ordermeals, restaurant restaurant) {
        Cart fragment = new Cart();
        Bundle args = new Bundle();
        args.putString("meals", ordermeals.toString());
        args.putString("restaurant", restaurant.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("ordermeal",ordermeals.toString());

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(counted!=null){counted.clear();}
        if (ordermeals != null ) {
            if (!ordermeals.isEmpty()){
            counted = ordermeals.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }else{
                Log.d("heyy","ana ");
                cartViewModel = new ViewModelProvider(this).get(cartViewModel.class);
                cartViewModel.getAllData();
                cartViewModel.getcart_dataMutable().observe(getViewLifecycleOwner(), new Observer<cart_data>() {
                    @Override
                    public void onChanged(cart_data cart_data) {
                        Log.d("heyy","ana 22");

                        for (meal_order meal_order:cart_data.getMeals_order() ) {
                            meal meal = new meal();
                            meal.setId(meal_order.getId());
                            meal.setName(meal_order.getName());
                            meal.setPrice(meal_order.getPrice());
                            meal.setRestaurant_id(meal_order.getRestaurant_id());
                            meal.setDetails(meal_order.getDetails());
                            meal.setImg(meal_order.getImg());
                            ordermeals.add(meal);
                            Log.d("fsf",ordermeals.toString());
                        }
                        for (meal_order meal: cart_data.getMeals_order()) {
                            counted.put(meal,Long.valueOf(meal.getQuantity()));
                        }
                        if(restaurants_list == null){
                            restaurant = new restaurant();
                            restaurant.setName(cart_data.getRestaurant_name());
                            restaurant.setDelivery_fee(String.valueOf(cart_data.getDelivery_fee()));
                            restaurant.setId(cart_data.getMeals_order().get(0).getRestaurant_id());
                            restaurants_list.add(restaurant);
                        }
                    }
                });
            }
        } else{
            Log.d("fsf","fyug");
            cartViewModel = new ViewModelProvider(this).get(cartViewModel.class);
            cartViewModel.getAllData();
            cartViewModel.getcart_dataMutable().observe(getViewLifecycleOwner(), new Observer<cart_data>() {
                @Override
                public void onChanged(cart_data cart_data) {
                    Log.d("fsffff",cart_data.toString());

                    for (meal_order meal_order:cart_data.getMeals_order() ) {
                        meal meal = new meal();
                        meal.setId(meal_order.getId());
                        meal.setName(meal_order.getName());
                        meal.setPrice(meal_order.getPrice());
                        meal.setRestaurant_id(meal_order.getRestaurant_id());
                        meal.setDetails(meal_order.getDetails());
                        meal.setImg(meal_order.getImg());
                        ordermeals.add(meal);
                        Log.d("fsf",ordermeals.toString());
                    }
                    for (meal_order meal: cart_data.getMeals_order()) {
                        counted.put(meal,Long.valueOf(meal.getQuantity()));
                    }
                    if(restaurants_list == null){
                        restaurant = new restaurant();
                        restaurant.setName(cart_data.getRestaurant_name());
                        restaurant.setDelivery_fee(String.valueOf(cart_data.getDelivery_fee()));
                        restaurant.setId(cart_data.getMeals_order().get(0).getRestaurant_id());
                        restaurants_list.add(restaurant);
                    }
                }
            });
        }
            add_to_card_adapter meals_ad = new add_to_card_adapter(counted);
            name = view.findViewById(R.id.add_to_cart_resturant_title);
            cart_total = view.findViewById(R.id.add_to_cart_cart_total);
            delivery = view.findViewById(R.id.add_to_cart_delivery_fee);
            total = view.findViewById(R.id.add_to_cart_total_price);
            checkout = view.findViewById(R.id.add_to_cart_check_out);

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), checkout.class);
                    if (user != null) {
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("Order");
                        reference = reference.child(user.getUid());
                        reference.removeValue();
                        reference.child("cart_total").setValue(cart.getCart_total());
                        reference.child("total").setValue(cart.getTotal());
                        reference.child("delivery_fee").setValue(cart.getDelivery_fee());
                        reference.child("state").setValue("draft");
                        reference.child("restaurant").setValue(restaurants_list.get(0).getName());
                        reference = reference.child("meals");
                        DatabaseReference meal_db;
                        for (Map.Entry<meal, Long> entry : counted.entrySet()) {
                            meal_db = reference.child(entry.getKey().getId());
                            meal_db.setValue(entry.getKey());
                            meal_db.child("quantity").setValue(entry.getValue());
                        }


                    }
                    startActivity(i);
                }
            });
            meals_ad.setAddlistener(new add_to_card_adapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    meals_ad.add(position);
                    meal key = (meal) counted.keySet().toArray()[position];
                    ordermeals.add(key);
                    cart.setMeals(ordermeals);
                    cart_total.setText(String.valueOf(cart.getCart_total()));
                    delivery.setText(String.valueOf(cart.getDelivery_fee()));
                    total.setText(String.valueOf(cart.getTotal()));

                }
            });
            meals_ad.setRemovelistener(new add_to_card_adapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (meals_ad.getItemCount() != 0) {
                        meal key = (meal) counted.keySet().toArray()[position];
                        ordermeals.remove(key);
                        Long num = counted.get(key);
                        if (num - 1 == 0) {
                            counted.remove(key);
                            meals_ad.notifyDataSetChanged();
                        } else {
                            counted.replace(key, num - 1);
                            meals_ad.notifyDataSetChanged();
                        }
                        cart.setMeals(ordermeals);
                        cart_total.setText(String.valueOf(cart.getCart_total()));
                        delivery.setText(String.valueOf(cart.getDelivery_fee()));
                        total.setText(String.valueOf(cart.getTotal()));

                    } else {
                        ordermeals.clear();
                        counted.clear();
                    }
                }
            });
            resturantViewModel.getAllData(ordermeals.get(0).getRestaurant_id());
            resturantViewModel.getResturants().observe(getViewLifecycleOwner(), new Observer<ArrayList<restaurant>>() {
                @Override
                public void onChanged(ArrayList<restaurant> restaurants) {
                    if (restaurants != null) {
                        restaurants_list = restaurants;
                        name.setText(restaurants.get(0).getName());
                        cart = new cart_data(ordermeals, restaurants);
                        cart.setDelivery_fee(Double.valueOf(restaurants.get(0).getDelivery_fee()));
                        cart_total.setText(String.valueOf(cart.getCart_total()));
                        delivery.setText(String.valueOf(cart.getDelivery_fee()));
                        total.setText(String.valueOf(cart.getTotal()));
                    }
                }
            });


            recyclerView = view.findViewById(R.id.order_details_container);
            recyclerView.setAdapter(meals_ad);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //meals =new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }

}