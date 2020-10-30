package com.example.androidtest.activity.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.activity.screens.dresschooser.DressChooserContract;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashSet;

public interface BasePresenter <T> {

    void initAuth ();

    AppDatabase getDatabase();

    void takeView(T view);

}
