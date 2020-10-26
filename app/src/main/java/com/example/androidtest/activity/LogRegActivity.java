package com.example.androidtest.activity;

import android.content.Intent;
import android.util.Log;

import com.example.androidtest.activity.base.BaseActivity;
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

        AppDatabase database = getDatabase();
        if (database.userItemDao().checkEmail(user.getEmail()).size() == 0) {
            UserItem newUser = new UserItem(Objects.requireNonNull(user.getEmail()));
            database.userItemDao().insert(newUser);
            Log.d("TAG", "User added to SQL database");

        } else {
            Log.d("TAG", "User " + database.userItemDao().checkEmail(user.getEmail()).get(0).getEmail() + " exists");
        }

        startActivity(explicitIntent);
    }

    public void goToList() {
        Intent explicitIntent = new Intent(this, DressChooser.class);
        startActivity(explicitIntent);
    }

}
