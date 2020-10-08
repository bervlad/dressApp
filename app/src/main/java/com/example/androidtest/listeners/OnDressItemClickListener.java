package com.example.androidtest.listeners;

import android.view.View;

import com.example.androidtest.database.UserData;

public interface OnDressItemClickListener {
    public void onItemClick(View v, int position);
    public UserData onHeartClick ();
}

