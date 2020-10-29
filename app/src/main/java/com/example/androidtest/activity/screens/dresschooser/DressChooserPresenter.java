package com.example.androidtest.activity.screens.dresschooser;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;

import com.example.androidtest.R;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserDressItem;
import com.google.firebase.auth.FirebaseUser;

import java.security.acl.Owner;
import java.util.HashSet;
import java.util.List;

public class DressChooserPresenter extends BasePresenterClass implements DressChooserContract.Presenter {

    AppDatabase database;
    Owner owner;

    DressChooserContract.View view;



    public DressChooserPresenter(AppCompatActivity activity) {

        super(activity);
    }

    @Override
    public void takeView(DressChooserContract.View view) {
        this.view = view;
            database = super.getDatabase();
            if (database != null) {
                LiveData<List<DressItem>> liveItemData = database.dressItemDao().getAll();
                //liveItemData.observe(owner, );
                view.observeItems(liveItemData);
            }
        }

    @Override
    public void FireDatabaseToSQL() {
        getApp().FireDatabaseToSQL();
    }

    @Override
    public Boolean isLoggedIn () {
        return getUser() != null;
    }

    public FirebaseUser getUser () {
        return getApp().getUser();
    }

    @Override
    public void heartClicked(String itemId) {
        FirebaseUser mUser = getUser();
        AppDatabase database = getDatabase();
        if (database.userItemDao().getLikesForUser(mUser.getEmail()).contains(itemId)) {
//            view.setHeart(itemId, false);
            database.userDressItemDao().deleteLikeFromUser(mUser.getEmail(), itemId);
        } else {
//            view.setHeart(itemId, true);
            database.userDressItemDao().insert(new UserDressItem(mUser.getEmail(), itemId));
        }

        view.notifyChange();
    }

    @Override
    public void showHeart(String itemId) {
        FirebaseUser mUser = getUser();
        AppDatabase database = getDatabase();
        view.setHeart(database.userItemDao().getLikesForUser(mUser.getEmail()).contains(itemId));
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
