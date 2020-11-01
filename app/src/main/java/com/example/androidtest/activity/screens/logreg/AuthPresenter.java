package com.example.androidtest.activity.screens.logreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class AuthPresenter extends BasePresenterClass implements AuthContract.Presenter{

    private AuthContract.View view;

    public AuthPresenter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void logClicked(AppCompatEditText email, AppCompatEditText password) {
        view.hideKeyboard();
        FirebaseUser user = getApp().getUser();

        if (isUserLoggedIn(user)) return;
        if (isFieldFilledIn(email, "Please enter email")) return;
        if (isFieldFilledIn(password, "Please enter password")) return;

        getApp().logPassAuth(Objects.requireNonNull(email.getText()).toString(),
                Objects.requireNonNull(password.getText()).toString(),
                (BaseActivity) getActivity());
    }

    @Override
    public void regClicked(AppCompatEditText textUser, AppCompatEditText email, AppCompatEditText password) {
        view.hideKeyboard();

        FirebaseUser user = getApp().getUser();

        if (isUserLoggedIn(user)) return;
        if (isFieldFilledIn(email, "Please enter email")) return;
        if (isFieldFilledIn(password, "Please enter password")) return;

        getApp().regPassAuth(
                Objects.requireNonNull(textUser.getText()).toString(),
                Objects.requireNonNull(email.getText()).toString(),
                Objects.requireNonNull(password.getText()).toString(),
                (BaseActivity) getActivity()
        );
    }

    private boolean isFieldFilledIn(AppCompatEditText field, String s) {
        if (field.getText() == null || field.getText().toString().trim().isEmpty()) {
            view.showToast(s);
            return true;
        }
        return false;
    }

    private boolean isUserLoggedIn(FirebaseUser user) {
        if (user!= null) {
            view.showToast("Already signed in");
            view.goToList();
            return true;
        }
        return false;
    }

    @Override
    public void takeView(AuthContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view=null;
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
