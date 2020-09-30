package com.example.androidtest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.KeyboardUtils;
import com.example.androidtest.R;
import com.example.androidtest.listeners.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends LogRegActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        initToolbarWithNavigation("Login page", false);

        final AppCompatEditText email, password;
        AppCompatButton logButton;

        email = findViewById(R.id.editText_user);
        password = findViewById(R.id.editText_password);
        logButton = findViewById(R.id.button_login);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtils.hide(LoginActivity.this);
                if (mAuth.getCurrentUser()!=null) {showNameToast("Already signed in"); goToList(); return;}
                if (email.getText()==null || email.getText().toString().trim().isEmpty()){showNameToast("Please enter email"); return;}
                if (password.getText()==null || password.getText().toString().trim().isEmpty()){showNameToast("Please enter password"); return;}
                logPassAuth(email.getText().toString(), password.getText().toString(), mAuth);
            }
        });

    }

    private void logPassAuth(String email, String password, final FirebaseAuth mAuth) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            goToListSignedUser(user);

                        } else {
                            showNameToast("Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
                        }
                    }
                });
    }
}