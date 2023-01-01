package com.example.wagba.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.Model.users;
import com.example.wagba.database.RoomDataBase;
import com.example.wagba.database.userDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileRepo {
    FirebaseDatabase db;
    private Application app;
    DatabaseReference reference;
    ProfileRepo.OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;
    FirebaseUser firebaseUser;
    private userDao userDao;
    private  MutableLiveData<users> user ;
    LiveData<users> user_live;


    public ProfileRepo(Application app , ProfileRepo.OnRealtimeDbTaskComplete onRealtimeDbTaskComplete) {
        db =FirebaseDatabase.getInstance();
        reference = db.getReference("User_Node");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
        this.app = app;
        RoomDataBase db_ROOM = RoomDataBase.getDatabase(app);
        userDao = db_ROOM.userDao();
        user = new MutableLiveData<>();
        String id = firebaseUser.getUid();
        user_live = userDao.getuser(id);
    }
    public void  insert (users user){
        new ProfileRepo.insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<users, Void,Void>{

        private userDao AsyncTaskDao;
        insertAsyncTask(userDao dao){
            AsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final users... user) {
            AsyncTaskDao.insert(user[0]);
            return null;
        }
    }
        public LiveData<users> getuserbyID() {
            return user_live;
        }

        public void getuserbyIDfire(){
            if (firebaseUser != null) {
                String id = firebaseUser.getUid();
                    Log.d("usersss","da5al");
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users_Node");
                    reference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            users user_s = snapshot.child(id).getValue(users.class);
                            user_s.setUser_id(id);
                            insert(user_s);
                            onRealtimeDbTaskComplete.onSucesss(user_s);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            onRealtimeDbTaskComplete.onFaliare(error);
                        }
                    });
            }
        }


    public interface  OnRealtimeDbTaskComplete{
        void onSucesss(users user);
        void onFaliare(DatabaseError error);
    }
}
