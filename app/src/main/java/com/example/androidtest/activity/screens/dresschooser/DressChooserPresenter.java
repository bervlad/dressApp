package com.example.androidtest.activity.screens.dresschooser;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.DressItem;

import java.util.HashSet;
import java.util.List;

public class DressChooserPresenter extends BasePresenterClass implements DressChooserContract.Presenter {

    AppDatabase database;

    public DressChooserPresenter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void takeView(DressChooserContract.View view) {
            database = super.getDatabase();
            if (database != null) {
                LiveData<List<DressItem>> liveItemData = database.dressItemDao().getAll();
                view.observeItems(liveItemData);
            }
        }

    @Override
    public void FireDatabaseToSQL() {
        getApp().FireDatabaseToSQL();
    }

}
