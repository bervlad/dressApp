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
    FireBase fireBase;

    public HashSet<BasketItem> getBasketItems() {
        return basketItems;
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    public void initAuth () {
        fireBase.initAuth(basketItems);
    }

    public void FireDatabaseToSQL () {
        fireBase.FireDatabaseToSQL(appDatabase);
    }

    public FirebaseUser getUser () {
        return fireBase.getUser();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        basketItems = new HashSet<>();
        fireBase = new FireBase();

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "dataUsersDress")
                .allowMainThreadQueries()
                .build();
    }
}
