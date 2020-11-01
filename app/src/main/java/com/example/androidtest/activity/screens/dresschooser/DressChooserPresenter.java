package com.example.androidtest.activity.screens.dresschooser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.model.UserDressItem;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public class DressChooserPresenter extends BasePresenterClass implements DressChooserContract.Presenter {

    private DressChooserContract.View view;

    public DressChooserPresenter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void takeView(DressChooserContract.View view) {
        this.view = view;
        AppDatabase database = super.getDatabase();
        if (database != null) {
            LiveData<List<DressItem>> liveItemData = database.dressItemDao().getAll();
            liveItemData.observe(super.getActivity(), view::observeItems);
        }
    }

    @Override
    public void dropView() {
        this.view=null;
    }

    @Override
    public void FireDatabaseToSQL() {
        getApp().FireDatabaseToSQL();
    }

    @Override
    public Boolean isLoggedIn() {
        return getUser() != null;
    }

    @Override
    public void heartClicked(String itemId) {
        FirebaseUser mUser = getUser();
        AppDatabase database = getDatabase();
        if (database.userItemDao().getLikesForUser(mUser.getEmail()).contains(itemId)) {
            database.userDressItemDao().deleteLikeFromUser(mUser.getEmail(), itemId);
        } else {
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

}
