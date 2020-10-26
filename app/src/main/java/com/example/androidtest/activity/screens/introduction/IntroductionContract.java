package com.example.androidtest.activity.screens.introduction;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.androidtest.activity.base.BasePresenter;
import com.example.androidtest.activity.base.BaseView;

import java.util.List;

public interface IntroductionContract {


    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter<View> {
        void initAuth (Context context);
    }

}
