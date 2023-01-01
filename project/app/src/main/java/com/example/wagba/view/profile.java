package com.example.wagba.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.users;
import com.example.wagba.R;
import com.example.wagba.Repositories.ProfileRepo;
import com.example.wagba.ViewModel.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class profile extends Fragment {

    private Button signout_butn;
    private signoutViewModel signoutViewModel;
    private profieViewModel profieViewModel;
    private ordersViewModel ordersViewModel;
    TextView name,email,phone_no;
    Button tracking;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.profilr_name);
        email = view.findViewById(R.id.profile_email);
        phone_no =view.findViewById(R.id.profile_phone_no);
        tracking = view.findViewById(R.id.profile_track_order);
        tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersViewModel = new ViewModelProvider(profile.this).get(ordersViewModel.class);
                ordersViewModel.getAllData();
                ordersViewModel.getcart_dataMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<cart_data>>() {
                    @Override
                    public void onChanged(ArrayList<cart_data> cart_data) {
                        for (cart_data cart_dataa: cart_data) {
                            if (cart_dataa.getState() != "delivered"){
                                order_page order_page = new order_page();
                                order_page.setCart_data(cart_dataa);
                                getParentFragmentManager().beginTransaction().add(R.id.container , order_page).addToBackStack(null).commit();
                            }
                        }
                    }
                });
            }
        });
        profieViewModel = new ViewModelProvider(this).get(profieViewModel.class);
        profieViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<users>() {
            @Override
            public void onChanged(users users) {
                if(users !=null) {
                    name.setText(users.getUser_name());
                    email.setText(users.getUser_email());
                    phone_no.setText(users.getPhone_no());
                }else{
                    profieViewModel.getAllDatafire();
                    profieViewModel.getuserMutable().observe(getViewLifecycleOwner(), new Observer<users>() {
                        @Override
                        public void onChanged(users users) {
                            name.setText(users.getUser_name());
                            email.setText(users.getUser_email());
                            phone_no.setText(users.getPhone_no());
                        }
                    });
                }

            }
        });


        signout_butn = view.findViewById(R.id.signout);
        signoutViewModel = new ViewModelProvider(this).get(signoutViewModel.class);

        signout_butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                signoutViewModel.signout();
                startActivity(new Intent(getContext(), login.class));
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}