package com.example.androidtest.activity.screens.logreg;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;

import com.example.androidtest.KeyboardUtils;
import com.example.androidtest.R;
import com.example.androidtest.activity.base.BasePresenterClass;

public class LoginActivity extends AuthActivity implements AuthContract.View {

    private static final String TAG = "LoginActivity";
    AuthContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        this.setPresenter(new AuthPresenter(this));
        presenter.takeView(this);

        initToolbarWithNavigation("Login page", false);

        final AppCompatEditText email, password;
        AppCompatButton logButton;

        email = findViewById(R.id.editText_user);
        password = findViewById(R.id.editText_password);
        logButton = findViewById(R.id.button_login);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logClicked(email, password);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

}