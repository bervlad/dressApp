package com.example.androidtest.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UserInfoDetails {
    String email;
    ArrayList<DressItem> items;

    public UserInfoDetails(String email, ArrayList<DressItem> items) {
        this.email = email;
        this.items = items;
    }

    public String getUser() {
        return email;
    }

    public ArrayList<DressItem> getItems() {
        return items;
    }


}
