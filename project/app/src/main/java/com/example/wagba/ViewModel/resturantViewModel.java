package com.example.wagba.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.database.restaurant;
import com.google.firebase.database.DatabaseError;
import com.example.wagba.Model.resturantRepo;

import java.io.Closeable;
import java.util.ArrayList;

public class resturantViewModel extends ViewModel implements resturantRepo.OnRealtimeDbTaskComplete{
    private MutableLiveData<ArrayList<restaurant>> resturants = new MutableLiveData<>();
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private resturantRepo RestaurantRepo ;

    public resturantViewModel( ) {
        RestaurantRepo = new resturantRepo(this);
    }


    public MutableLiveData<ArrayList<restaurant>> getResturants() {
        return resturants;
    }

    public MutableLiveData<DatabaseError> getErrorMutable() {
        return errorMutable;
    }

    public  void getAllData(){
        RestaurantRepo.getallData();
    }



    @Override
    public void onSucesss(ArrayList<restaurant> restaurants) {
        resturants.setValue(restaurants);
    }

    @Override
    public void onFaliare(DatabaseError error) {
        errorMutable.setValue(error);
    }
}

