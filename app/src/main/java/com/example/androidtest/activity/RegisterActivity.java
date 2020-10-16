package com.example.androidtest.activity;

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


public class RegisterActivity extends LogRegActivity {

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
                KeyboardUtils.hide(RegisterActivity.this);
                if (mAuth.getCurrentUser() != null) {
                    showNameToast("Already signed in");
                    goToList();
                    return;
                }
                if (user.getText() == null || user.getText().toString().trim().isEmpty()) {
                    showNameToast("Please enter your name");
                    return;
                }
                if (email.getText() == null || email.getText().toString().trim().isEmpty()) {
                    showNameToast("Please enter email");
                    return;
                }
                if (password.getText() == null || password.getText().toString().trim().isEmpty()) {
                    showNameToast("Please enter password");
                    return;
                }
                regPassAuth(user.getText().toString(), email.getText().toString(), password.getText().toString(), mAuth);
            }
        });
    }

    private void regPassAuth(final String name, String email, String password, final FirebaseAuth mAuth) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            final FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();

                            assert user != null;
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                                goToListSignedUser(user);
                                            }
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            showNameToast("Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
                        }
                    }
                });
    }
}