package com.example.androidtest.app;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.activity.base.FireBase;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.DressItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashSet;

public class AndroidTest extends Application {

    private int basket;
    private HashSet<BasketItem> basketItems;
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
    
    public void logPassAuth (String email, String pass, BasePresenterClass presenter, AppCompatActivity activity) {
        fireBase.logPassAuth (email, pass, presenter, activity);
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
