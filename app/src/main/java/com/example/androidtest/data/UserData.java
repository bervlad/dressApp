package com.example.androidtest.data;

import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserInfoDetails;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class UserData {
    private HashMap<FirebaseUser, UserInfoDetails> userData;

    public UserInfoDetails getUserInfoDetails (FirebaseUser user) {
        return userData.get(user);
    }

    public void addUser (FirebaseUser user) {
        if (userData.get(user)!=null) {
            userData.put(user, new UserInfoDetails (user, new ArrayList<DressItem>() ));
        }
    }

    public void addUserWithInfo (FirebaseUser user, UserInfoDetails details) {
            userData.put(user, details );
    }

    public void addItemsToUser (FirebaseUser user, ArrayList<DressItem> items) {
        Objects.requireNonNull(userData.get(user)).setItems(items);
    }

    public ArrayList<DressItem> getItems (FirebaseUser user) {
        return Objects.requireNonNull(userData.get(user)).getItems();
    }
}
