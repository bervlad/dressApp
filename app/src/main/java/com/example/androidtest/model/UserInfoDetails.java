package com.example.androidtest.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UserInfoDetails {
    FirebaseUser user;
    ArrayList<DressItem> items;

    public UserInfoDetails(FirebaseUser user, ArrayList<DressItem> items) {
        this.user = user;
        this.items = items;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public ArrayList<DressItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DressItem> items) {
        this.items = items;
    }

    public DressItem getItemById (int id) {
        for (DressItem item : items) {
            if (item.getId()==id)  return item;
        }
    return null;
    }


}
