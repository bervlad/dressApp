package com.example.androidtest.activity.screens.logreg;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.androidtest.activity.base.BasePresenter;
import com.example.androidtest.activity.base.BaseView;
import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {
        void hideKeyboard ();
        void showToast (String toast);
        void goToList ();
        void goToListSignedUser (FirebaseUser user);
    }

    interface Presenter extends BasePresenter<LoginContract.View> {
        void logClicked (AppCompatEditText login, AppCompatEditText password);
    }

}
