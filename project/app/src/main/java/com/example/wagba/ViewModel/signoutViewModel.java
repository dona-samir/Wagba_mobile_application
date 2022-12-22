package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.registrationRepo;
import com.google.firebase.auth.FirebaseUser;

public class signoutViewModel extends AndroidViewModel {
    private registrationRepo signupRepo;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedliverdata;

    public signoutViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application);
        userlivedata= signupRepo.getUserlivedata();
        looggedliverdata = signupRepo.getLooggedoutliverdata();
    }
    public void signout(){
        signupRepo.signOut();
    }
    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }

    public MutableLiveData<Boolean> getLooggedliverdata() {
        return looggedliverdata;
    }
}
