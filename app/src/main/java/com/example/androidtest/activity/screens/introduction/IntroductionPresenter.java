package com.example.androidtest.activity.screens.introduction;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.model.BasketItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashSet;


public class IntroductionPresenter extends BasePresenterClass implements IntroductionContract.Presenter {

    private IntroductionContract.View view;


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
