package com.example.mentorship.model;

import android.arch.persistence.room.Room;
import android.support.multidex.MultiDexApplication;

import com.example.mentorship.model.MyDB;

/**
 * Created by naimish on 15/11/17.
 */

public class MyApplication extends MultiDexApplication {
    private MyDB database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), MyDB.class, "MyDB").build();
    }

    public MyDB getDatabase() {
        return database;
    }

}
