package com.example.androidtest.database;

import android.util.Log;

import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UserData {
    private HashMap<String, UserItem> userData;

    public UserData() {
        userData = new HashMap<>();
    }

}
