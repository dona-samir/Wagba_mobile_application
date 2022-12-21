package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.AppRepository;
import com.google.firebase.auth.FirebaseUser;

public class registration extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> userlivedata;


    public registration(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        userlivedata= appRepository.getUserlivedata();
    }
    public void signup(String email ,String password  ){
        appRepository.signup(email,password);
    }

    public void login(String email ,String password ){
        appRepository.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }
}
