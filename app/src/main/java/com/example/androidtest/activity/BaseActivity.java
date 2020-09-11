package com.example.androidtest.activity;

import android.view.View;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.R;

public abstract class BaseActivity extends AppCompatActivity  {

    private Toolbar toolbar;

    public void initToolbarWithNavigation(String title, Boolean menu) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        if (menu) {
            toolbar.inflateMenu(R.menu.main);
        }

        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
