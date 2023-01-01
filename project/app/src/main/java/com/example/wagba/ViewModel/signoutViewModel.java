package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.restaurant;
import com.example.wagba.Model.users;
import com.example.wagba.Repositories.registrationRepo;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class signoutViewModel extends AndroidViewModel implements registrationRepo.OnRealtimeDbTaskComplete {
    private registrationRepo signupRepo;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedliverdata;

    public signoutViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application , this);
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

    @Override
    public void onSucesss(ArrayList<users> user) {

    }

    @Override
    public void onFaliare(DatabaseError error) {

    }
}
