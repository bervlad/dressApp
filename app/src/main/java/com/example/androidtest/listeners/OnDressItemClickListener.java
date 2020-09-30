package com.example.androidtest.listeners;

import android.app.Application;
import android.view.View;

import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.data.UserData;
import com.example.androidtest.utils.ItemRecyclerAdapter;

public interface OnDressItemClickListener {
    public void onItemClick(View v, int position);
    public UserData onHeartClick ();
}

