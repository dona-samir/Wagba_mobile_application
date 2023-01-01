package com.example.wagba.Repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.meal_order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ordersRepo {
    FirebaseDatabase db;
    DatabaseReference reference;
    ordersRepo.OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;
    FirebaseUser user;

    public ordersRepo(ordersRepo.OnRealtimeDbTaskComplete onRealtimeDbTaskComplete) {
        db =FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = db.getReference("Order_history").child(user.getUid());
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;

    }

    public void getallData(){
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ArrayList<cart_data> cart_datas = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        Log.d("mealss",snapshot.toString());
                        cart_data cart_data = new cart_data();
                        DataSnapshot sanp = snapshot1.child("meals");
                        ArrayList<meal_order> meal_orders = new ArrayList<>();
                        for (DataSnapshot userSnapshot : sanp.getChildren()) {
                            meal_order meal_order = new meal_order();
                            meal_order.setId(String.valueOf(userSnapshot.child("id").getValue()));
                            meal_order.setName(String.valueOf(userSnapshot.child("name").getValue()));
                            meal_order.setImg(String.valueOf(userSnapshot.child("img").getValue()));
                            meal_order.setDetails(String.valueOf(userSnapshot.child("details").getValue()));
                            meal_order.setPrice(String.valueOf(userSnapshot.child("price").getValue()));
                            meal_order.setQuantity(String.valueOf(userSnapshot.child("quantity").getValue()));
                            meal_order.setRestaurant_id(String.valueOf(userSnapshot.child("restaurant_id").getValue()));
                            meal_orders.add(meal_order);
                        }
                        cart_data.setId(snapshot1.getKey());
                        cart_data.setMeals_order(meal_orders);
                        cart_data.setCart_total(Double.valueOf(String.valueOf(snapshot1.child("cart_total").getValue())));
                        cart_data.setDelivery_fee(Double.valueOf(String.valueOf(snapshot1.child("delivery_fee").getValue())));
                        cart_data.setRestaurant_name(snapshot1.child("restaurant").getValue().toString());
                        cart_data.setState(String.valueOf(snapshot1.child("state").getValue()));
                        cart_data.setTotal(Double.valueOf((String.valueOf(snapshot1.child("total").getValue()))));
                        cart_data.setDate(snapshot1.child("date").getValue().toString());
                        cart_data.setGate(snapshot1.child("gate").getValue().toString());
                        cart_data.setNote(snapshot1.child("note").getValue().toString());
                        cart_data.setTime(snapshot1.child("time").getValue().toString());
                        cart_datas.add(cart_data);
                    }
                    onRealtimeDbTaskComplete.onSucesss(cart_datas);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }




    public void getallData(String order_id){
        reference.child(order_id);
        reference.addValueEventListener(new ValueEventListener(){
            cart_data cart_data = new cart_data();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<cart_data> cart_datas = new ArrayList<>();
                if (snapshot.exists()){
                    DataSnapshot sanp = snapshot.child("meals");
                    ArrayList<meal_order> meal_orders = new ArrayList<>();
                    for (DataSnapshot userSnapshot : sanp.getChildren()) {
                        meal_order meal_order =new meal_order();
                        meal_order.setId(String.valueOf(userSnapshot.child("id").getValue()));
                        meal_order.setName( String.valueOf(userSnapshot.child("name").getValue()));
                        meal_order.setImg(String.valueOf(userSnapshot.child("img").getValue()));
                        meal_order.setDetails(String.valueOf(userSnapshot.child("details").getValue()));
                        meal_order.setPrice(String.valueOf(userSnapshot.child("price").getValue()));
                        meal_order.setQuantity(String.valueOf(userSnapshot.child("quantity").getValue()));
                        meal_order.setRestaurant_id(String.valueOf(userSnapshot.child("restaurant_id").getValue()));
                        meal_orders.add(meal_order);
                    }
                    cart_data.setMeals_order(meal_orders);
                    cart_data.setCart_total(Double.valueOf(String.valueOf(snapshot.child("cart_total").getValue())));
                    cart_data.setDelivery_fee(Double.valueOf(String.valueOf(snapshot.child("delivery_fee").getValue())));
                    cart_data.setRestaurant_name(snapshot.child("restaurant").getValue().toString());
                    cart_data.setState(String.valueOf(snapshot.child("state").getValue()));
                    cart_data.setTotal(Double.valueOf((String.valueOf(snapshot.child("total").getValue()))));
                    cart_data.setDate(snapshot.child("date").getValue().toString());
                    cart_data.setGate(snapshot.child("gate").toString());
                    cart_data.setNote(snapshot.child("note").toString());
                    cart_data.setTime(snapshot.child("time").toString());
                    cart_datas.add(cart_data);
                    onRealtimeDbTaskComplete.onSucesss(cart_datas);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }




    public interface  OnRealtimeDbTaskComplete{
        void onSucesss(ArrayList<cart_data> cart);
        void onFaliare(DatabaseError error);
    }

}
