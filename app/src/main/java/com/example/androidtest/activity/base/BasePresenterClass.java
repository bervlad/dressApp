package com.example.androidtest.activity.base;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashSet;

public class BasePresenterClass implements BasePresenter {

    private AppCompatActivity activity;

    public BasePresenterClass( AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }


    public AndroidTest getApp () {
        return (AndroidTest) activity.getApplication();
    }

    @Override
    public void initAuth() {
        getApp().initAuth();
    }

    @Override
    public AppDatabase getDatabase() {
        return (getApp().getDatabase());
    }

    public int getBasketSize () {
        return getApp().getBasketItems().size();
    }
}
