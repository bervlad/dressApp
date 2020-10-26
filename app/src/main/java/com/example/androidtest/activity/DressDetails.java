package com.example.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.R;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.model.DressItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class DressDetails extends BaseActivity {
    private DressItem dressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolbarWithNavigation("Details", false);
        initBasket();

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();


        final LinearLayoutCompat desc = findViewById(R.id.desc_tab);
        final NestedScrollView scroll = findViewById(R.id.scrollView);
        final AppCompatImageView line = findViewById(R.id.line1);
        AppCompatButton button = findViewById(R.id.addButton);
        final AppCompatImageView circle = findViewById(R.id.basket_circle);


        //getting model from parcel
        if (getIntent().getExtras() != null) {
            dressItem = getIntent().getParcelableExtra(Constants.EXTRA_ITEM);
        }

        //init views
        ImageView avatar = findViewById(R.id.avatar);
        TextView title = findViewById(R.id.title_card);
        TextView price = findViewById(R.id.price);
        TextView crossed_price = findViewById(R.id.crossed_price);
        ArrayList<ImageView> stars = new ArrayList<ImageView>();
        for (int i = 0; i < 5; i++) {
            stars.add((ImageView) findViewById(getResources().
                    getIdentifier("star_" + Integer.toString(i + 1), "id", getPackageName())));
        }
        TextView reviews = findViewById(R.id.reviews);
        TextView alert = findViewById(R.id.alert);


        //Setting data
        Glide.with(avatar).load(dressItem.getUri()).placeholder(R.drawable.ic_account_search_outline).into(avatar);

        for (int i = 0; i < dressItem.getStars(); i++) {
            stars.get(i).setColorFilter(ContextCompat.getColor(this, R.color.star_filled));
        }

        title.setText(dressItem.getTitle());
        price.setText(dressItem.getPriceText());
        crossed_price.setText(dressItem.getOldPriceText());
        reviews.setText(dressItem.getReviewsText());
        alert.setText(dressItem.getAlert());

        //setting default values
        final Spinner spinnerSize = setSpinner(R.id.spinner1, new String[]{"Size", "2", "three"});
        final Spinner spinnerColor = setSpinner(R.id.spinner2, new String[]{"Color", "2", "three"});
        final Spinner quant = setSpinner(R.id.spinner3, new String[]{"1", "2", "3"});


        //adding functionality to button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser == null) {
                    showNameToast("Please login");
                    return;
                }
                if (spinnerSize.getSelectedItem().toString().equals("Size")) {
                    showNameToast("Please specify size");
                    return;
                }
                if (spinnerColor.getSelectedItem().toString().equals("Color")) {
                    showNameToast("Please specify color");
                    return;
                }
                int quantity = Integer.parseInt(quant.getSelectedItem().toString());
                int num = (((AndroidTest) getApplication()).getBasket()) + quantity;
                ((AndroidTest) getApplication()).setBasket(num);
                Intent explicitIntent = new Intent(DressDetails.this, DressChooser.class);
                startActivity(explicitIntent);
            }
        });

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
                    scroll.post(new Runnable() {
                        @Override
                        public void run() {
                            scroll.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        });
    }

    public Spinner setSpinner(int resource, String[] items) {
        Spinner dropdown = findViewById(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        return dropdown;
    }
}