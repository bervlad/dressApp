package com.example.androidtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.fragment.FragmentTemplate;
import com.example.androidtest.listeners.OnSkipClicked;
import com.example.androidtest.utils.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private FragmentTemplate fragmentOne,  fragmentTwo, fragmentThree;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        fragmentOne = FragmentTemplate.newInstance("Fragment one","Hre we go", R.drawable.icon1,false);
        fragmentTwo = FragmentTemplate.newInstance("Fragment two", "there we went", R.drawable.icon2, false);
        fragmentThree = FragmentTemplate.newInstance("Sign In | Sign Up", "there we went", R.drawable.icon3, true);
        fragmentThree.setListener(onSkipClicked);


        adapter.addFragment(fragmentOne, "FrOne");
        adapter.addFragment(fragmentTwo, "FrTwo");
        adapter.addFragment(fragmentThree, "FrThree");

        viewPager.setAdapter(adapter);
        setListeners();

    }

    public void initViews () {
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

    public void setListeners () {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Integer [] indexes = {R.id.divider1, R.id.divider2, R.id.divider3};
                for (int i=0; i<indexes.length; i++) {
                    View bottom = findViewById(indexes[i]);
                    bottom.setBackgroundColor(ContextCompat.getColor (getApplicationContext(), R.color.darker));
                }
                View bottom = findViewById(indexes[position]);
                bottom.setBackgroundColor(ContextCompat.getColor (getApplicationContext(), R.color.colorPrimaryDark));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

OnSkipClicked onSkipClicked = new OnSkipClicked() {
    @Override
    public void nextActivity() {
        Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(explicitIntent);
    }
};
}