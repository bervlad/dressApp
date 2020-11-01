package com.example.androidtest.activity.screens.introduction;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.activity.base.BasePresenterClass;

public class IntroductionPresenter extends BasePresenterClass implements IntroductionContract.Presenter {

    public IntroductionPresenter(AppCompatActivity activity) {
        super(activity);
    }


    @Override
    public void takeView(IntroductionContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
