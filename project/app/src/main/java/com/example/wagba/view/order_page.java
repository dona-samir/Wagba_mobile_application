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
import android.widget.TextView;

import com.example.wagba.Model.cart_data;
import com.example.wagba.R;
import com.example.wagba.Model.meal;
import com.example.wagba.ViewModel.cartViewModel;
import com.example.wagba.ViewModel.ordersViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link order_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class order_page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private cart_data cart_data;
    RecyclerView recyclerView;
    ArrayList<meal> meals ;
    ordersViewModel ordersViewModel;
    TextView restaurant_name , state , date , id , timme,gate,cart_total,delivery_fee,total_price;

    public order_page() {
        // Required empty public constructor
    }


    public static order_page newInstance(String param1, String param2) {
        order_page fragment = new order_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public cart_data getCart_data() {
        return cart_data;
    }

    public void setCart_data(cart_data cart_data) {
        this.cart_data = cart_data;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurant_name = view.findViewById(R.id.order_page_res_title);
        state = view.findViewById(R.id.order_page_order_status);
        date = view.findViewById(R.id.order_page_order_date);
        id = view.findViewById(R.id.order_page_order_id);
        timme = view.findViewById(R.id.order_page_time);
        gate = view.findViewById(R.id.order_page_gate);
        cart_total = view.findViewById(R.id.order_page_cart_price);
        delivery_fee = view.findViewById(R.id.order_page_delivery_price);
        total_price= view.findViewById(R.id.order_page_total_price);
        restaurant_name.setText(cart_data.getRestaurant_name());
        state.setText(cart_data.getState());
        date.setText(cart_data.getDate());
        id.setText(cart_data.getId());
        if(cart_data.getTime()=="pm"){
            timme.setText("3:00 PM");
        }else{
            timme.setText("12:00 PM");
        }
        gate.setText("Gate "+cart_data.getGate());
        cart_total.setText(String.valueOf(cart_data.getCart_totalexist()));
        delivery_fee.setText(String.valueOf(cart_data.getDelivery_fee()));
        total_price.setText(String.valueOf(cart_data.getTotalexist()));
        meal_past_order_adapter meals_ad = new meal_past_order_adapter(cart_data.getMeals_order());
        recyclerView = view.findViewById(R.id.order_summery);
        recyclerView.setAdapter(meals_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        meals =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        View view = inflater.inflate(R.layout.fragment_order_page, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}