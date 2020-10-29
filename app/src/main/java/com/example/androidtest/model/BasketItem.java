package com.example.androidtest.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.concurrent.atomic.AtomicLong;

public class BasketItem {

    private final String email;

    private final String dressId;

    private final String size;

    private final String color;

    private int num;

    static final AtomicLong NEXT_ID = new AtomicLong(0);

    @PrimaryKey
    final long id;

    public long getId() {
        return id;
    }

    public BasketItem(@NonNull String email, @NonNull String dressId, String size, String color, int num) {
        this.id = NEXT_ID.getAndIncrement();
        this.email = email;
        this.dressId = dressId;
        this.size = size;
        this.color = color;
        this.num = num;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getDressId() {
        return dressId;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

}
