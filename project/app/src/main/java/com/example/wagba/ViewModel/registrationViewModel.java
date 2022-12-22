package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.registrationRepo;
import com.google.firebase.auth.FirebaseUser;

public class registrationViewModel extends AndroidViewModel {
    private registrationRepo signupRepo;
    private MutableLiveData<FirebaseUser> userlivedata;


    public registrationViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application);
        userlivedata= signupRepo.getUserlivedata();
    }
    public void signup(String email ,String password  ){
        signupRepo.signup(email,password);
    }

    public void login(String email ,String password ){
        signupRepo.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }
}
