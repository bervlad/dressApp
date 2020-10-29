package com.example.androidtest.activity.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;

import java.util.HashSet;

public interface BasePresenter {

    void initAuth ();

    AppDatabase getDatabase();
}
