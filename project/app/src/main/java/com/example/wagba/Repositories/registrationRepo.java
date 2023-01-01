package com.example.wagba.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.meal;
import com.example.wagba.Model.restaurant;
import com.example.wagba.Model.users;
import com.example.wagba.database.userDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.wagba.database.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class registrationRepo {
    private Application app;
    private FirebaseAuth firebaseauth;
    private MutableLiveData<FirebaseUser> userlivedata;
    private MutableLiveData<Boolean> looggedoutliverdata;
    private userDao userDao;
    private  MutableLiveData<users> user ;
    FirebaseDatabase db;
    DatabaseReference reference;
    OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;




    public registrationRepo(Application app , OnRealtimeDbTaskComplete onRealtimeDbTaskComplete) {
        this.app = app;
        RoomDataBase db_ROOM = RoomDataBase.getDatabase(app);
        userDao = db_ROOM.userDao();
        firebaseauth = FirebaseAuth.getInstance();
        userlivedata = new MutableLiveData<>();
        looggedoutliverdata = new MutableLiveData<>();
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
        db =FirebaseDatabase.getInstance();
        reference = db.getReference("Users_Node");
        user =  new MutableLiveData<>();

    }

    public LiveData<users> getUserById(String id){
        return userDao.getuser(id);
    }

    public void  insert (users user){
        new insertAsyncTask(userDao).execute( user);
    }

    private static class insertAsyncTask extends AsyncTask<users, Void,Void>{

        private userDao AsyncTaskDao;
        insertAsyncTask(userDao dao){
            AsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final users... users) {
            AsyncTaskDao.insert(users[0]);
            return null;
        }
    }


    public void signup(String email , String password ,String name ,String phone_no){
        firebaseauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            users user = new users(firebaseauth.getCurrentUser().getUid(),name,email,phone_no);
                            insert(user);
                            userlivedata.postValue(firebaseauth.getCurrentUser());
                        }{
                   Toast.makeText(app, " faild due to "+task.getException().getMessage().toString() , Toast.LENGTH_SHORT).show();
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


    public void getallData(String id ){
        FirebaseUser firebaseUser = firebaseauth.getCurrentUser();
       String idu =firebaseUser.getUid().toString();
        reference =reference.child(idu);
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<users> users = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    users user = new users();
                    user.setUser_email(String.valueOf(userSnapshot.child("email").getValue()));
                    user.setPhone_no(String.valueOf(userSnapshot.child("phone_no").getValue()));
                    user.setUser_name(String.valueOf(userSnapshot.child("name").getValue()));
                    users.add(user);
                    Log.d("meals_adapteee",users.toString());
                }

                onRealtimeDbTaskComplete.onSucesss(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFaliare(error);
            }
        });
    }

//    public void getuserbyID(){
//
//        FirebaseUser firebaseUser = firebaseauth.getCurrentUser();
//        String id =firebaseUser.getUid().toString();
//
//        LiveData<users> user_live = userDao.getuser(id);
//        users user_data = user_live.getValue();
//        user.postValue(user_data);
//        if(user == null){
//            db =FirebaseDatabase.getInstance();
//            reference = db.getReference("Users_Node");
//            reference.addValueEventListener(new ValueEventListener(){
//
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    user.postValue(snapshot.child(id).getValue(users.class));
//                    onRealtimeDbTaskComplete.onSucesss(user);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    onRealtimeDbTaskComplete.onFaliare(error);
//                }
//            });
//        }else{
//            onRealtimeDbTaskComplete.onSucesss(user);
//
//        }
//    }
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

    public interface  OnRealtimeDbTaskComplete{
        void onSucesss(ArrayList<users> user);
        void onFaliare(DatabaseError error);
    }
}
