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

    public boolean hasUser (String email) {
        return userData.containsKey(email);
    }

    public void addUser (String email) {
            Log.d("TAG", "New user added: "+ userData.get(email));
            userData.put(email, new UserItem(email, new ArrayList<String>() ));
    }


    public ArrayList<String> getItems (String email) {
        if (userData!=null && userData.get(email)!=null) {
            return Objects.requireNonNull(userData.get(email)).getItems();} else return null;
    }
}
