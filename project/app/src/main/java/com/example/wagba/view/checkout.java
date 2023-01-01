package com.example.wagba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.R;
import com.example.wagba.ViewModel.cartViewModel;
import com.example.wagba.ViewModel.mealViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class checkout extends AppCompatActivity {
    cartViewModel cartViewModel;
    TextView total , cart_total,delivery , restaurant_name;
    EditText notes;
    RadioButton time_am , time_pm , gate_3 , gate_4;
    Button place_Order;
    String choosen_gate ="";
    String choosen_time ="";
    cart_data data =new cart_data();
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        total = findViewById(R.id.check_out_total_price);
        cart_total = findViewById(R.id.check_out__cart_price);
        delivery = findViewById(R.id.check_out__delivery_price);
        time_am  =findViewById(R.id.AM);
        time_pm = findViewById(R.id.PM);
        gate_3 = findViewById(R.id.Gate_3);
        gate_4 = findViewById(R.id.Gate_4);
        place_Order = findViewById(R.id.place_order);
        notes = findViewById(R.id.notes);
        restaurant_name = findViewById(R.id.check_out_restaurant_title);
        long hour = Calendar.getInstance().getTime().getHours();
        long minutes = Calendar.getInstance().getTime().getMinutes();
        SimpleDateFormat dateOnly = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateOnly.format(Calendar.getInstance().getTime());


        user = FirebaseAuth.getInstance().getCurrentUser();


        place_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gate_3.isChecked()) {
                    choosen_gate = "3";
                } else if (gate_4.isChecked()) {
                    choosen_gate = "4";
                } else {
                    Toast.makeText(checkout.this, "Please, choose puck up location", Toast.LENGTH_SHORT).show();
                }
                if (time_am.isChecked()) {
                    if (12 - hour > 2) {
                        choosen_time = "am";
                    } else if (12 - hour == 2) {
                        if (minutes > 0) {
                            Toast.makeText(checkout.this, "Sorry you can't place order but you can place order for pm", Toast.LENGTH_LONG).show();
                        } else {
                            choosen_time = "am";
                        }
                    } else {
                        Toast.makeText(checkout.this, "Sorry you can't place order but you can place order for pm", Toast.LENGTH_LONG).show();
                    }
                } else if (time_pm.isChecked()) {
                    if (15 - hour > 2) {
                        choosen_time = "pm";

                    } else if (15 - hour == 2) {
                        if (minutes > 0) {
                            Toast.makeText(checkout.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                        } else {
                            choosen_time = "pm";
                        }
                    } else {

                        Toast.makeText(checkout.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(checkout.this, "Please, choose time of pick up", Toast.LENGTH_LONG).show();
                }
                data.setNote(notes.getText().toString());
                if (choosen_time != "" && choosen_gate != ""){
                    data.setState("placed");
                data.setTime(choosen_time);
                data.setGate(choosen_gate);
                data.setDate(date);
                Intent i = new Intent(checkout.this, Main.class);
                if (user != null) {

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Order_history");
                    reference = reference.child(user.getUid()).push();
                    reference.child("cart_total").setValue(data.getCart_totalexist());
                    reference.child("total").setValue(data.getTotalexist());
                    reference.child("delivery_fee").setValue(data.getDelivery_fee());
                    reference.child("state").setValue("placed");
                    reference.child("restaurant").setValue(data.getRestaurant_name());
                    reference.child("time").setValue(data.getTime());
                    reference.child("gate").setValue(data.getGate());
                    reference.child("note").setValue(data.getNote());
                    reference.child("date").setValue(data.getDate());
                    DatabaseReference reference_meal;
                    for (meal meal : data.getMeals_order()) {
                        reference_meal = reference.child("meals").child(meal.getId());
                        reference_meal.setValue(meal);
                    }

                    reference = db.getReference("Order");
                    DatabaseReference references = reference.child(user.getUid());
                    references.removeValue();

                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(i);
                } else {
                    Toast.makeText(checkout.this, "error while proceed card please try again later", Toast.LENGTH_LONG).show();
                }
            }
            }
        });
        cartViewModel = new ViewModelProvider(this).get(cartViewModel.class);
        cartViewModel.getAllData();
        cartViewModel.getcart_dataMutable().observe(this, new Observer<cart_data>() {
            @Override
            public void onChanged(cart_data cart_data) {
                data = cart_data;
                restaurant_name.setText(cart_data.getRestaurant_name());
                total.setText(String.valueOf(cart_data.getTotalexist()));
                cart_total.setText(String.valueOf(cart_data.getCart_totalexist()));
                delivery.setText(String.valueOf(cart_data.getDelivery_fee()));
            }
        });

    }
}