package com.example.wagba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.Model.restaurant;
import com.google.firebase.database.DatabaseError;
import com.example.wagba.Repositories.resturantRepo;

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


    public  void getAllData(String id){
        RestaurantRepo.getAllData(id);
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

