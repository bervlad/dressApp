package com.example.androidtest.model;

import androidx.room.Entity;

@Entity(tableName = "basketTable")

public class Basket {


    int num;

    public Basket(int num) {
        this.num=num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
