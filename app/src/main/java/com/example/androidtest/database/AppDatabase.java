package com.example.androidtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.DressItemDao;

@Database(entities = {DressItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DressItemDao dressItemDao();
}

