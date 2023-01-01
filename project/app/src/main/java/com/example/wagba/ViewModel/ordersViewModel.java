package com.example.wagba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Repositories.cartRepo;
import com.example.wagba.Repositories.ordersRepo;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class ordersViewModel extends ViewModel implements ordersRepo.OnRealtimeDbTaskComplete{
        private MutableLiveData<ArrayList<cart_data>> cart_dataMutable = new MutableLiveData<>();
        private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
        private ordersRepo ordersRepo ;

    public ordersViewModel( ) {
        ordersRepo = new ordersRepo(this);
        }


        public MutableLiveData<ArrayList<cart_data>> getcart_dataMutable() {
            return cart_dataMutable;
        }

        public MutableLiveData<DatabaseError> getErrorMutable() {
            return errorMutable;
        }

        public  void getAllData(){
            ordersRepo.getallData();
        }

        @Override
        public void onSucesss(ArrayList<cart_data> cart) {
            cart_dataMutable.setValue(cart);
        }

        @Override
        public void onFaliare(DatabaseError error) {
            errorMutable.setValue(error);
        }
    }

