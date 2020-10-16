package com.example.androidtest.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userItemsTable")

public class UserItem {

    @PrimaryKey
    @NonNull
    String email;

    public UserItem(String email) {
        this.email = email;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

}
