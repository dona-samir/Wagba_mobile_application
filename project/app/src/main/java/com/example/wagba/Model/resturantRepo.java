package com.example.wagba.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wagba.database.restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class resturantRepo
{
    FirebaseDatabase db;
    DatabaseReference reference;
    OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;

    public resturantRepo(OnRealtimeDbTaskComplete onRealtimeDbTaskComplete) {
        db =FirebaseDatabase.getInstance();
        reference = db.getReference("Restaurants_Node");
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
    }

    public void getallData(){
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<restaurant> restaurants = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    restaurant new_restaurant =new restaurant();
                    new_restaurant.setId(String.valueOf(userSnapshot.getKey()));
                    new_restaurant.setName( String.valueOf(userSnapshot.child("name").getValue()));
                    new_restaurant.setImg(String.valueOf(userSnapshot.child("small_img").getValue()));
                    new_restaurant.setDetails(String.valueOf(userSnapshot.child("details").getValue()));
                    new_restaurant.setDelivery_fee(String.valueOf(userSnapshot.child("delivery_fee").getValue()));
                    new_restaurant.setImg_bg(String.valueOf(userSnapshot.child("background_img").getValue()));
                    restaurants.add(new_restaurant);
                    Log.d("adapter_img",restaurants.get(0).toString());
                }
                onRealtimeDbTaskComplete.onSucesss(restaurants);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }

    public interface  OnRealtimeDbTaskComplete{
        void onSucesss(ArrayList<restaurant> restaurants);
        void onFaliare(DatabaseError error);
    }

}
