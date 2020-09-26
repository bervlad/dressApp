package com.example.androidtest.app;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

public class AndroidTest extends Application {

    private int basket;
    private FirebaseAuth mAuth;

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        basket=0;
    }
}
