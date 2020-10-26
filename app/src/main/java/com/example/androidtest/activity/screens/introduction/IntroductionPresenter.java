package com.example.androidtest.activity.screens.introduction;

import android.content.Context;

import com.example.androidtest.R;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class IntroductionPresenter extends BasePresenterClass implements IntroductionContract.Presenter {


    private IntroductionContract.View view;


    @Override
    public void takeView(IntroductionContract.View view) {
        this.view=view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
