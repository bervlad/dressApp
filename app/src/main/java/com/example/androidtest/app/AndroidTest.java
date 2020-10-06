package com.example.androidtest.app;

import android.app.Application;

import com.example.androidtest.R;
import com.example.androidtest.data.UserData;
import com.example.androidtest.model.DressItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AndroidTest extends Application {

    private int basket;
    private UserData usersWithInfo;
    private ArrayList<DressItem> items;


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

    public ArrayList<DressItem> getItems () {
        return items;
    }

    public void initItems () {
        items = new ArrayList<DressItem>();

        items.add(new DressItem(1, R.drawable.img_1, "Hello",null, 100, 150, 3, 2));
        items.add(new DressItem(2, R.drawable.img_2, "Hello2", null, 140, 170, 4, 3));
        items.add(new DressItem(3, R.drawable.img_2, "Hello3", "Important", 10, -1, 5, 20));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        basket=0;
        usersWithInfo = new UserData();
    }
}
