package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.AppRepository;
import com.google.firebase.auth.FirebaseUser;

public class signout extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedliverdata;

    public signout(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        userlivedata= appRepository.getUserlivedata();
        looggedliverdata = appRepository.getLooggedoutliverdata();
    }
    public void signout(){
        appRepository.signOut();
    }
    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }

    public MutableLiveData<Boolean> getLooggedliverdata() {
        return looggedliverdata;
    }
}
