package com.example.androidtest.activity.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.activity.screens.dresschooser.DressChooserContract;
import com.example.androidtest.activity.screens.logreg.AuthContract;
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.R;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.listeners.Constants;
import com.google.firebase.auth.FirebaseAuth;

public abstract class BaseActivity extends AppCompatActivity  {

    public FirebaseAuth mAuth;

    public BasePresenterClass getPresenter() {
        return presenter;
    }

    public BasePresenterClass presenter;

    public void initToolbarWithNavigation(String title, Boolean menu) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        if (menu) {
            toolbar.inflateMenu(R.menu.main);
            if (presenter.getUser() != null) {
                toolbar.getMenu().getItem(2).setVisible(true);
            }
        }

        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_item_logout) {
                    presenter.initAuth();
                    Intent clearIntent = getIntent();
                    clearIntent.removeExtra(Constants.LOG);
                    setIntent(clearIntent);
                    recreate();
                }
                return true;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void initBasket() {
        //basket init
        TextView basketText = findViewById(R.id.basket_text);
        AppCompatImageView circle = findViewById(R.id.basket_circle);
        int num = presenter.getBasketSize();
        if (num > 0) {
            basketText.setVisibility(View.VISIBLE);
            circle.setVisibility(View.VISIBLE);
            basketText.setText(Integer.toString(num));
        } else {
            basketText.setVisibility(View.INVISIBLE);
            circle.setVisibility(View.INVISIBLE);
        }
    }

    public void showNameToast(String name) {
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    public void showToast(String toast) {
        showNameToast(toast);
    }
}
