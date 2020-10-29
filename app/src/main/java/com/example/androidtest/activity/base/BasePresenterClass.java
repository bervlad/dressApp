package com.example.androidtest.activity.base;

import android.content.Context;

import com.example.androidtest.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashSet;

public class BasePresenterClass implements BasePresenter {

    FirebaseAuth mAuth;

    @Override
    public void initAuth(Context context, HashSet<BasketItem> items) {
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            items.clear();
        }
    }
}
