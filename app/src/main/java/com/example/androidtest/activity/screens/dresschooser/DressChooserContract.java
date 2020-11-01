package com.example.androidtest.activity.screens.dresschooser;

import com.example.androidtest.activity.base.BasePresenter;
import com.example.androidtest.activity.base.BaseView;
import com.example.androidtest.model.DressItem;

import java.util.List;

public interface DressChooserContract {

    interface View extends BaseView<DressChooserContract.Presenter> {
        void observeItems(List<DressItem> dressItems);

        void setHeart(Boolean pressed);

        void notifyChange();
    }

    interface Presenter extends BasePresenter <View> {

        void FireDatabaseToSQL();

        void heartClicked(String id);

        void showHeart(String id);

        Boolean isLoggedIn();
    }
}
