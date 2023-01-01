package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.*;
import com.example.wagba.Repositories.registrationRepo;
import com.example.wagba.Repositories.resturantRepo;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class registrationViewModel extends AndroidViewModel implements registrationRepo.OnRealtimeDbTaskComplete{
    private registrationRepo signupRepo;
    private MutableLiveData<users> user ;
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> userlivedata;


    public void getuserbyID(){

        signupRepo.getallData("id");
    }
    public LiveData<users> getuser() {
        return user;
    }

    public registrationViewModel(@NonNull Application application) {
        super(application);
        signupRepo = new registrationRepo(application,  this);
        userlivedata= signupRepo.getUserlivedata();
    }
    public void signup(String email ,String password, String name , String phone_no){
        signupRepo.signup(email,password,name , phone_no);
    }

    public void login(String email ,String password ){
        signupRepo.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }



    @Override
    public void onSucesss(ArrayList<users> user) {

    }

    @Override
    public void onFaliare(DatabaseError error) {

    }
}
