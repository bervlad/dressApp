package com.example.androidtest.app;

import android.app.Application;

import com.example.androidtest.data.UserData;
import com.google.firebase.auth.FirebaseAuth;

public class AndroidTest extends Application {

    private int basket;
    private UserData usersWithInfo;


    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public UserData getUsersWithInfo() {
        return usersWithInfo;
    }

    public void setUsersWithInfo(UserData usersWithInfo) {
        this.usersWithInfo = usersWithInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        basket=0;
    }
}
