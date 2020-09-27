package com.example.androidtest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.fragment.FragmentTemplate;
import com.example.androidtest.listeners.OnLastFragment;
import com.example.androidtest.utils.ViewPagerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends BaseActivity {

    private FragmentTemplate fragmentOne, fragmentTwo, fragmentThree;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mSignInClient;
    private static final String TAGG = "SignInActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mSignInClient = GoogleSignIn.getClient(this, gso);

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);

        initViews();

        fragmentOne = FragmentTemplate.newInstance("Fragment one", "Hre we go", R.drawable.icon1, false);
        fragmentTwo = FragmentTemplate.newInstance("Fragment two", "there we went", R.drawable.icon2, false);
        fragmentThree = FragmentTemplate.newInstance("there we went", R.drawable.icon3, true);
        fragmentThree.setListener(onLastFragment);
        adapter.addFragment(fragmentOne, "FrOne");
        adapter.addFragment(fragmentTwo, "FrTwo");
        adapter.addFragment(fragmentThree, "FrThree");

        viewPager.setAdapter(adapter);
        setListeners();

        if (mAuth.getCurrentUser()!=null) signOut();

//        mAuth.signOut();
//        showNameToast(mAuth.getCurrentUser().getDisplayName());

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void initViews() {
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
    }


    public void setListeners() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Integer[] indexes = {R.id.divider1, R.id.divider2, R.id.divider3};
                for (int i = 0; i < indexes.length; i++) {
                    View bottom = findViewById(indexes[i]);
                    bottom.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.darker));
                }
                View bottom = findViewById(indexes[position]);
                bottom.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    OnLastFragment onLastFragment = new OnLastFragment() {
        @Override
        public void nextActivity() {
            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(explicitIntent);
        }

        @Override
        public void login() {
            signIn();
        }
    };

    private void signIn() {
        Intent signInIntent = mSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAGG, "firebaseAuthWithGoogle: " + account.getId());
                Log.d(TAGG, "Hello: " + account.getDisplayName());
                showNameToast("Hello " + account.getDisplayName());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAGG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAGG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            onLastFragment.nextActivity();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAGG, "signInWithCredential:failure", task.getException());
//                            Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }
}

