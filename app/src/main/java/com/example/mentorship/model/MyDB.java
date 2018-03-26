package com.example.mentorship.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by naimish on 15/11/17.
 */

@Database(entities = {Image.class}, version = 1)
public abstract class MyDB extends RoomDatabase {
    public abstract ImageDao imageDao();
}
