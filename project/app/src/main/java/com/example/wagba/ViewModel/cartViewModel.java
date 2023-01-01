package com.example.wagba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Repositories.cartRepo;
import com.google.firebase.database.DatabaseError;

public class cartViewModel extends ViewModel implements cartRepo.OnRealtimeDbTaskComplete{
    private MutableLiveData<cart_data> cart_dataMutable = new MutableLiveData<>();
    private MutableLiveData<cart_data> dataMutable = new MutableLiveData<>();
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private cartRepo cartRepo ;
    //private currentCartRepo currentCartRepo;

    public cartViewModel( ) {
        cartRepo = new cartRepo(this);
    }


    public MutableLiveData<cart_data> getcart_dataMutable() {
        return cart_dataMutable;
    }
//    public MutableLiveData<cart_data> getdataMutable() {
//        return dataMutable;
//    }


    public MutableLiveData<DatabaseError> getErrorMutable() {
        return errorMutable;
    }

    public  void getAllData(){
        cartRepo.getallData();
    }
//    public  void getAll(){
//        currentCartRepo.getallData();
//    }


    @Override
    public void onSucesss(cart_data cart) {
        cart_dataMutable.setValue(cart);
    }

    @Override
    public void onFaliare(DatabaseError error) {
        errorMutable.setValue(error);
    }
}
