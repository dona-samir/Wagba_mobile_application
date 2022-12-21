package com.example.wagba;

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

import java.util.ArrayList;


public class orders extends Fragment {


    // TODO: Rename and change types of parameters
    RecyclerView recyclerView;
    ArrayList<order> orders ;
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
        orders_list_adapter orders_list_adapter = new orders_list_adapter(orders);
        recyclerView = view.findViewById(R.id.orders_listview);
        recyclerView.setAdapter(orders_list_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL ,false));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        orders =new ArrayList<>();
        Log.d("TestFailed","OnCreate");
        restaurant kfc= new restaurant("kfc","fast food ",R.drawable.kfc);
        orders.add(new order(kfc.getName(),kfc.getImg(),"1cujvub ","12/10/22","on its way"));
        orders.add(new order(kfc.getName(),kfc.getImg(),"vkvblb ","1/10/22","delivered"));
        orders.add(new order(kfc.getName(),kfc.getImg(),"fvhjvkj ","12/8/22","delivered"));
        orders.add(new order(kfc.getName(),kfc.getImg(),"vkvblb ","1/4/22","delivered"));
        orders.add(new order(kfc.getName(),kfc.getImg(),"fvhjvkj ","12/1/22","delivered"));
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}