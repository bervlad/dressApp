package com.example.androidtest.activity.screens.logreg;

import android.content.Intent;

import com.example.androidtest.KeyboardUtils;
import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.activity.screens.dresschooser.DressChooser;
import com.example.androidtest.listeners.Constants;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public abstract class AuthActivity extends BaseActivity {

    public void goToListSignedUser(FirebaseUser user) {
        Intent explicitIntent = new Intent(this, DressChooser.class);
        if (user.getDisplayName() != null) {
            explicitIntent.putExtra(Constants.LOG, user.getDisplayName());
        }

        presenter.addUserToDatabase(user);

        startActivity(explicitIntent);
    }

    public void goToList() {
        Intent explicitIntent = new Intent(this, DressChooser.class);
        startActivity(explicitIntent);
    }

    public void hideKeyboard() {
        KeyboardUtils.hide(this);
    }

    public void showToast(String toast) {
        showNameToast(toast);
    }

    public void setPresenter(AuthContract.Presenter presenter) {
        super.presenter = (BasePresenterClass) presenter;
    }

}
