package com.example.androidtest.activity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.example.androidtest.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolbarWithNavigation("Details", false);
        final LinearLayoutCompat desc = findViewById(R.id.desc_tab);
        final NestedScrollView scroll = findViewById(R.id.scrollView);
        final AppCompatImageView line =findViewById(R.id.line1);

        setSpinner(R.id.spinner1, new String[]{"Size", "2", "three"});
        setSpinner(R.id.spinner2, new String[]{"Color", "2", "three"});
        setSpinner(R.id.spinner3, new String[]{"1", "2", "3"});

        final AppCompatImageView expand = findViewById(R.id.expand);
        expand.setRotation(180);

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (desc.getVisibility() == VISIBLE) {
                    desc.setVisibility(View.GONE);
                    line.setVisibility(VISIBLE);
                    expand.setRotation(0);
                } else {
                    desc.setVisibility(VISIBLE);
                    line.setVisibility(INVISIBLE);
                    expand.setRotation(180);
                    scroll.post(new Runnable() { @Override public void run() {
                        scroll.fullScroll(View.FOCUS_DOWN); } });
                }
            }
        });
    }

    public void setSpinner(int resource, String[] items) {
        Spinner dropdown = findViewById(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
    }
}