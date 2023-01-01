package com.example.wagba.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wagba.Model.users;

@Database(entities = {users.class}, version = 3 ,exportSchema = false)
public abstract class RoomDataBase extends RoomDatabase {

    public abstract userDao userDao();
    private static RoomDataBase INSTANCE;
    public static  RoomDataBase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RoomDataBase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDataBase.class , "room_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };
}
