package com.example.androidtest.activity.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.androidtest.R;
import com.example.androidtest.app.AndroidTest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class BasePresenterClass {
    public FirebaseAuth mAuth;


    public void initAuth(Context context) {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) mAuth.signOut();;
        ((AndroidTest) getApplication()).setBasket(0);
    }

}
