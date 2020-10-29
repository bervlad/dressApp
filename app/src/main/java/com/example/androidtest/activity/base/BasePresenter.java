package com.example.androidtest.activity.base;

import android.content.Context;

import com.example.androidtest.model.BasketItem;

import java.util.HashSet;

public interface BasePresenter {

    void initAuth (Context context, HashSet<BasketItem> items);

}
