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
import android.widget.Toast;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.Model.order;
import com.example.wagba.ViewModel.mealViewModel;
import com.example.wagba.ViewModel.ordersViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class orders extends Fragment {


    // TODO: Rename and change types of parameters
    RecyclerView recyclerView;
    ArrayList<order> orders ;
    ordersViewModel ordersViewModel;
    orders_list_adapter orders_list_adapter;
    public orders() {
        // Required empty public constructor
    }


    public static orders newInstance() {
        orders fragment = new orders();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.orders_listview);
        recyclerView.setAdapter(orders_list_adapter);
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
        orders =new ArrayList<>();
        ordersViewModel = new ViewModelProvider(orders.this).get(ordersViewModel.class);
        ordersViewModel.getAllData();
        orders_list_adapter = new orders_list_adapter();
        ordersViewModel.getcart_dataMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<cart_data>>() {
            @Override
            public void onChanged(ArrayList<cart_data> cart_data) {
                Log.d("orders",cart_data.toString());
                orders_list_adapter.setList(cart_data);
                orders_list_adapter.notifyDataSetChanged();
                orders_list_adapter.setListener(new orders_list_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        order_page order_page = new order_page();
                        order_page.setCart_data(cart_data.get(position));
                        getParentFragmentManager().beginTransaction().add(R.id.container , order_page).addToBackStack(null).commit();
                    }
                });

            }
        }
    );

        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}