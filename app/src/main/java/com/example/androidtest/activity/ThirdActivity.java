package com.example.androidtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidtest.R;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolbarWithNavigation("Details", false);

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}