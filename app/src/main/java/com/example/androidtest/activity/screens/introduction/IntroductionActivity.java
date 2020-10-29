package com.example.androidtest.activity.screens.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.androidtest.R;
import com.example.androidtest.activity.LoginActivity;
import com.example.androidtest.activity.RegisterActivity;
import com.example.androidtest.activity.screens.dresschooser.DressChooser;
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.fragment.FragmentTemplate;
import com.example.androidtest.listeners.OnLastFragment;
import com.example.androidtest.utils.ViewPagerAdapter;

public class IntroductionActivity extends AppCompatActivity implements IntroductionContract.View{

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private IntroductionContract.Presenter presenter;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAGG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setPresenter(new IntroductionPresenter(this));
        presenter.initAuth();

        setContentView(R.layout.activity_main);

        initViews();

        FragmentTemplate fragmentOne = FragmentTemplate.newInstance("Fragment one", "Hre we go", R.drawable.icon1, false);
        FragmentTemplate fragmentTwo = FragmentTemplate.newInstance("Fragment two", "there we went", R.drawable.icon2, false);
        FragmentTemplate fragmentThree = FragmentTemplate.newInstance("there we went", R.drawable.icon3, true);
        fragmentThree.setListener(onLastFragment);
        adapter.addFragment(fragmentOne);
        adapter.addFragment(fragmentTwo);
        adapter.addFragment(fragmentThree);

        viewPager.setAdapter(adapter);
        setListeners();
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
                for (Integer index : indexes) {
                    View bottom = findViewById(index);
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
            Intent explicitIntent = new Intent(IntroductionActivity.this, DressChooser.class);
            startActivity(explicitIntent);
        }

        @Override
        public void login() {
            Intent explicitIntent = new Intent(IntroductionActivity.this, LoginActivity.class);
            startActivity(explicitIntent);
        }

        @Override
        public void register() {
            Intent explicitIntent = new Intent(IntroductionActivity.this, RegisterActivity.class);
            startActivity(explicitIntent);
        }

    };


    @Override
    public void setPresenter(IntroductionContract.Presenter presenter) {
        this.presenter=presenter;
    }

}
