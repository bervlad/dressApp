package com.example.androidtest.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.AndroidTest;
import com.example.androidtest.R;

public abstract class BaseActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    TextView basketText;
    AppCompatImageView circle;


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

    public void initBasket () {
        //basket init
        basketText=findViewById(R.id.basket_text);
        circle = findViewById(R.id.basket_circle);
        int num= ((AndroidTest)getApplication()).getBasket();
        if (num>0) {
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
}
