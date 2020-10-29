package com.example.androidtest.activity.screens.dresschooser;

import androidx.lifecycle.LiveData;

import com.example.androidtest.activity.base.BasePresenter;
import com.example.androidtest.activity.base.BaseView;
import com.example.androidtest.activity.screens.introduction.IntroductionContract;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.DressItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface DressChooserContract {

    interface View extends BaseView<DressChooserContract.Presenter> {
        void observeItems(LiveData<List<DressItem>> itemsLiveData);
        void setHeart (Boolean pressed);
        void notifyChange ();
    }

    interface Presenter extends BasePresenter {
        void takeView(DressChooserContract.View view);
        void FireDatabaseToSQL ();
        void heartClicked(String id);
        void showHeart(String id);
        Boolean isLoggedIn ();
    }
}
