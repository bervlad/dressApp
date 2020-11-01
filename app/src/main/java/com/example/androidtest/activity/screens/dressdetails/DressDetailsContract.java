package com.example.androidtest.activity.screens.dressdetails;

import com.example.androidtest.activity.base.BasePresenter;
import com.example.androidtest.activity.base.BaseView;
import com.example.androidtest.model.DressItem;

public interface DressDetailsContract {

    interface View extends BaseView<DressDetailsContract.Presenter> {
        void showToast(String toast);

        String getSpinnerSize();

        String getSpinnerColor();

        String getQuant();

        DressItem getDressItem();
    }

    interface Presenter extends BasePresenter<View> {
        void purchaseClicked();
    }
}

