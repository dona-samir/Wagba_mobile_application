package com.example.wagba.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba.Model.cart_data;
import com.example.wagba.Model.users;
import com.example.wagba.Repositories.ProfileRepo;
import com.example.wagba.Repositories.cartRepo;
import com.example.wagba.database.userDao;
import com.google.firebase.database.DatabaseError;

public class profieViewModel extends AndroidViewModel implements ProfileRepo.OnRealtimeDbTaskComplete{
    private MutableLiveData<users> mUser = new MutableLiveData<>();
    private MutableLiveData<DatabaseError> errorMutable = new MutableLiveData<>();
    private ProfileRepo ProfileRepo;
    LiveData<users> user_live;


    public profieViewModel(@NonNull Application application ) {
        super(application);
        ProfileRepo = new ProfileRepo(application,this);
        user_live =ProfileRepo.getuserbyID();
    }


    public MutableLiveData<users> getuserMutable() {
        return mUser;
    }

    public MutableLiveData<DatabaseError> getErrorMutable() {
        return errorMutable;
    }

    public LiveData<users> getAllData(){
        return user_live;
    }
    public void getAllDatafire(){
        ProfileRepo.getuserbyIDfire();
    }
    @Override
    public void onSucesss(users user) {
        mUser.setValue(user);
    }

    @Override
    public void onFaliare(DatabaseError error) {
        errorMutable.setValue(error);
    }
}
