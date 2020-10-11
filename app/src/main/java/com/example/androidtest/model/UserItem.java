package com.example.androidtest.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.androidtest.database.TypesConverter;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Entity(tableName = "userItemsTable")

@TypeConverters({TypesConverter.class})

public class UserItem {

    @PrimaryKey
    @NonNull
    String email;

    @ColumnInfo (name="itemsLiked")
    ArrayList<String> items;

    public UserItem(String email, ArrayList<String> items) {
        this.email = email;
        this.items = items;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setItems(ArrayList<String> itemsLiked) {
        this.items = itemsLiked;
    }
}
