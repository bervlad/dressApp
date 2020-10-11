package com.example.androidtest.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userDressItemsTable", primaryKeys = {"email","dressId"})

public class UserDressItem {

    @NonNull
    @ColumnInfo(name="email")
    private String email;

    @NonNull
    @ColumnInfo(name="dressId")
    private String dressId;

    public UserDressItem(String email, String dressId) {
        this.email = email;
        this.dressId = dressId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDressId() {
        return dressId;
    }

    public void setDressId(String dressId) {
        this.dressId = dressId;
    }

}
