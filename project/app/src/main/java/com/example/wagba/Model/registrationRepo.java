package com.example.wagba.Model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registrationRepo {
    private Application app;
    private FirebaseAuth firebaseauth;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedoutliverdata;


    public registrationRepo(Application app) {
        this.app = app;
        firebaseauth = FirebaseAuth.getInstance();
        userlivedata = new MutableLiveData<>();
        looggedoutliverdata = new MutableLiveData<>();
    }

    public void signup(String email , String password ){
        firebaseauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   userlivedata.postValue(firebaseauth.getCurrentUser());
               }else{
                   Toast.makeText(app, "registrationViewModel faild", Toast.LENGTH_SHORT).show();
                    Log.d("login","registrationViewModel faild"+task.getException().getMessage().toString());

                }
            }
        });
    }
    public void login(String email , String password){
        firebaseauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userlivedata.postValue(firebaseauth.getCurrentUser());
                        }else {
                            Toast.makeText(app, "wrong email or password", Toast.LENGTH_SHORT).show();
                            Log.d("login","wrong email or password"+task.getException().getMessage().toString());
                        }
                    }
                });
    }
    public void signOut(){
        firebaseauth.signOut();
        looggedoutliverdata.postValue(true);
    }

    public MutableLiveData<Boolean> getLooggedoutliverdata() {
        return looggedoutliverdata;
    }

    public MutableLiveData<FirebaseUser> getUserlivedata() {
        return userlivedata;
    }
}
