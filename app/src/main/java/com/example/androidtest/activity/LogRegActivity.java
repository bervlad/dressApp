package com.example.androidtest.activity;

import android.content.Intent;

import com.example.androidtest.listeners.Constants;
import com.google.firebase.auth.FirebaseUser;

public abstract class LogRegActivity extends BaseActivity {

    void goToListSignedUser(FirebaseUser user) {
        Intent explicitIntent = new Intent(this, SecondActivity.class);
        if (user.getDisplayName()!=null) {
            explicitIntent.putExtra(Constants.LOG, user.getDisplayName());
        }
        // adding user to database
        if (!getUserData().hasUser(user.getEmail())) {
            getUserData().addUser(user.getEmail());
        }

        startActivity(explicitIntent);
    }

    public void goToList ()
    {
        Intent explicitIntent = new Intent(this, SecondActivity.class);
        startActivity(explicitIntent);
    }

    private void addUser () {

    }
}
