package com.example.androidtest.activity.screens.logreg;

import android.content.Intent;
import android.util.Log;

import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.activity.screens.dresschooser.DressChooser;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.model.UserItem;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public abstract class LogRegActivity extends BaseActivity {

    void goToListSignedUser(FirebaseUser user) {
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

}
