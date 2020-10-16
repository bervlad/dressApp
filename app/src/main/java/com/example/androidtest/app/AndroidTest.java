package com.example.androidtest.app;

import android.app.Application;

import androidx.room.Room;

import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.DressItem;

import java.util.ArrayList;

public class AndroidTest extends Application {

    private int basket;
    private ArrayList<DressItem> items;
    private AppDatabase appDatabase;

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        basket = 0;

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "dataUsersDress")
                .allowMainThreadQueries()
                .build();
    }
}
