package com.example.androidtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.DressItemDao;
import com.example.androidtest.model.UserDressItem;
import com.example.androidtest.model.UserDressItemDao;
import com.example.androidtest.model.UserItem;
import com.example.androidtest.model.UserItemDao;

@Database(entities = {DressItem.class, UserItem.class, UserDressItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DressItemDao dressItemDao();
    public abstract UserItemDao userItemDao();
    public abstract UserDressItemDao userDressItemDao();
}

