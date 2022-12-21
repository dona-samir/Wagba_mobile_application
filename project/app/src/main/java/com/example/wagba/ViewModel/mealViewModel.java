package com.example.wagba.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.database.*;
import com.google.firebase.database.DatabaseError;
import com.example.wagba.Model.*;
import com.google.firebase.database.core.Repo;

import java.io.Closeable;
import java.util.ArrayList;

public class mealViewModel extends ViewModel implements mealRepo.OnRealtimeDbTaskComplete{
    private MutableLiveData<ArrayList<meal>> mealsMutable = new MutableLiveData<>();
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private mealRepo mealRepo ;

    public mealViewModel( ) {
        mealRepo = new mealRepo(this);
    }


    public MutableLiveData<ArrayList<meal>> getMeals() {
        return mealsMutable;
    }

    public MutableLiveData<DatabaseError> getErrorMutable() {
        return errorMutable;
    }

    public  void getAllData(){
        mealRepo.getallData();
    }

    public  void getAllData(String id){
        mealRepo.getallData(id);
    }

    @Override
    public void onSucesss(ArrayList<meal> meals) {
        mealsMutable.setValue(meals);
    }


    @Override
    public void onFaliare(DatabaseError error) {
        errorMutable.setValue(error);
    }
}

