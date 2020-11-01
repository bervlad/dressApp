package com.example.androidtest.activity.screens.logreg;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.KeyboardUtils;
import com.example.androidtest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;


public class RegisterActivity extends AuthActivity implements AuthContract.View {

    private static final String TAG = "RegisterActivity";
    AuthContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setPresenter(new AuthPresenter(this));
        presenter = (AuthContract.Presenter) getPresenter();
        presenter.takeView(this);

        mAuth = FirebaseAuth.getInstance();

        initToolbarWithNavigation("Registration", false);

        final AppCompatEditText email, password, user;
        AppCompatButton regButton;

        email = findViewById(R.id.editText_user);
        password = findViewById(R.id.editText_password);
        user = findViewById(R.id.editText_username);
        regButton = findViewById(R.id.button_reg);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.regClicked(user, email, password);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}