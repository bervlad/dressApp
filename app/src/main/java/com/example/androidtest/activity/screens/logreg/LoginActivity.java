package com.example.androidtest.activity.screens.logreg;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.KeyboardUtils;
import com.example.androidtest.R;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends LogRegActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        this.setPresenter(new LoginPresenter(this));
        presenter.takeView(this);

        initToolbarWithNavigation("Login page", false);

        final AppCompatEditText email, password;
        AppCompatButton logButton;

        email = findViewById(R.id.editText_user);
        password = findViewById(R.id.editText_password);
        logButton = findViewById(R.id.button_login);

        //mAuth = FirebaseAuth.getInstance();

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logClicked(email, password);
            }
        });

    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        super.presenter = (BasePresenterClass) presenter;
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hide(LoginActivity.this);
    }

    @Override
    public void showToast(String toast) {
        showNameToast(toast);
    }

    @Override
    public void goToListSignedUser(FirebaseUser user) {
        super.goToListSignedUser(user);
    }

}