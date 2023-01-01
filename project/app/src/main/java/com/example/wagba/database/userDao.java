package com.example.wagba.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wagba.Model.users;

import java.util.List;

@Dao
public interface userDao {

    @Query("SELECT * FROM users WHERE user_id = :userid")
    LiveData<users> getuser(String userid);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(users user);

}
