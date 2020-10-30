package com.example.androidtest.activity.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.UserItem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashSet;
import java.util.Objects;

public class BasePresenterClass {

    private AppCompatActivity activity;

    public BasePresenterClass(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public AndroidTest getApp() {
        return (AndroidTest) activity.getApplication();
    }

    public void initAuth() {
        getApp().initAuth();
    }

    public AppDatabase getDatabase() {
        return (getApp().getDatabase());
    }

    public int getBasketSize() {
        return getApp().getBasketItems().size();
    }

    public FirebaseUser getUser() {
        return getApp().getUser();
    }

    public void addUserToDatabase(FirebaseUser user) {
        AppDatabase database = getDatabase();

        if (database.userItemDao().checkEmail(user.getEmail()).size() == 0) {
            UserItem newUser = new UserItem(Objects.requireNonNull(user.getEmail()));
            database.userItemDao().insert(newUser);
            Log.d("TAG", "User added to SQL database");
        } else {
            Log.d("TAG", "User " + database.userItemDao().checkEmail(user.getEmail()).get(0).getEmail() + " exists");
        }
    }

    public void loginSuccess(FirebaseUser user) {

    }

    public void loginFailed(Task<AuthResult> task) {

    }

}
