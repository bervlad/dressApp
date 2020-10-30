package com.example.androidtest.activity.screens.logreg;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.androidtest.activity.base.BasePresenterClass;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginPresenter extends BasePresenterClass implements LoginContract.Presenter{

    private LoginContract.View view;

    public LoginPresenter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void logClicked(AppCompatEditText email, AppCompatEditText password) {
        view.hideKeyboard();
        FirebaseUser user = getApp().getUser();

        if (user!= null) {
            view.showToast("Already signed in");
            view.goToList();
            return;
        }
        if (email.getText() == null || email.getText().toString().trim().isEmpty()) {
            view.showToast("Please enter email");
            return;
        }
        if (password.getText() == null || password.getText().toString().trim().isEmpty()) {
            view.showToast("Please enter password");
            return;
        }
        getApp().logPassAuth(email.getText().toString(), password.getText().toString(), this, getActivity());
    }


    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        view.goToListSignedUser(user);
    }

    @Override
    public void loginFailed(Task<AuthResult> task) {
        view.showToast("Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
    }
}
