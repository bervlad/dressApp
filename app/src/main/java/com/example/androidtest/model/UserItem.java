package com.example.androidtest.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UserItem {
    String email;
    ArrayList<String> items;

    public UserItem(String email, ArrayList<String> items) {
        this.email = email;
        this.items = items;
    }

    public String getUser() {
        return email;
    }

    public ArrayList<String> getItems() {
        return items;
    }


}
