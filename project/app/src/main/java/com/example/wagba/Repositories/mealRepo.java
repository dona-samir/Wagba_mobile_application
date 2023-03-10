package com.example.wagba.Repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wagba.Model.meal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class mealRepo
{
    FirebaseDatabase db;
    DatabaseReference reference;
    OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;

    public mealRepo(OnRealtimeDbTaskComplete onRealtimeDbTaskComplete) {
        db =FirebaseDatabase.getInstance();
        reference = db.getReference("Meals_Node");
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
    }
    public void getallData( ){
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<meal> meals = new ArrayList<>();
                for (DataSnapshot userSnapshots : snapshot.getChildren()) {
                    for (DataSnapshot userSnapshot : userSnapshots.getChildren()) {
                        meal new_meal = new meal();
                        new_meal.setRestaurant_id(String.valueOf(userSnapshots.getKey()));
                        new_meal.setId(String.valueOf(userSnapshot.getKey()));
                        new_meal.setName(String.valueOf(userSnapshot.child("name").getValue()));
                        new_meal.setAvailable(userSnapshot.child("available").getValue(Integer.class));
                        new_meal.setDetails(String.valueOf(userSnapshot.child("details").getValue()));
                        new_meal.setPrice(String.valueOf(userSnapshot.child("price").getValue()));
                        new_meal.setImg(String.valueOf(userSnapshot.child("img").getValue()));
                        meals.add(new_meal);
                    }
                }
                onRealtimeDbTaskComplete.onSucesss(meals);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }
    public void getallData(String id ){
        DatabaseReference meal_reference =reference.child(id);
        meal_reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<meal> meals = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    meal new_meal = new meal();
                    new_meal.setRestaurant_id(String.valueOf(id));
                    new_meal.setId(String.valueOf(userSnapshot.getKey()));
                    new_meal.setName(String.valueOf(userSnapshot.child("name").getValue()));
                    new_meal.setDetails(String.valueOf(userSnapshot.child("details").getValue()));
                    new_meal.setPrice(String.valueOf(userSnapshot.child("price").getValue()));
                    new_meal.setImg(String.valueOf(userSnapshot.child("img").getValue()));
                    new_meal.setAvailable(userSnapshot.child("available").getValue(Integer.class));
                    meals.add(new_meal);
                }

                onRealtimeDbTaskComplete.onSucesss(meals);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }

    public interface  OnRealtimeDbTaskComplete{
        void onSucesss(ArrayList<meal> meals);
        void onFaliare(DatabaseError error);
    }

}
