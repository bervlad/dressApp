package com.example.androidtest.data;

import android.util.Log;

import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserInfoDetails;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class UserData {
    private HashMap<String, UserInfoDetails> userData;

    public UserData() {
        userData = new HashMap<>();
    }

    public boolean hasUser (String email) {
        return userData.containsKey(email);
    }

    public void addUser (String email) {
            Log.d("TAG", "New user added: "+ userData.get(email));
            userData.put(email, new UserInfoDetails (email, new ArrayList<DressItem>() ));
    }


    public ArrayList<DressItem> getItems (String email) {
        if (userData!=null && userData.get(email)!=null) {
            return Objects.requireNonNull(userData.get(email)).getItems();} else return null;
    }
}
