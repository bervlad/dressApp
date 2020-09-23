package com.example.androidtest.app;

import android.app.Application;

public class AndroidTest extends Application {

    private int basket;

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
