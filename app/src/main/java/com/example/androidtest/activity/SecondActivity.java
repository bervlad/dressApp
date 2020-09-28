package com.example.androidtest.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.utils.ItemRecyclerAdapter;

import java.util.ArrayList;

public class SecondActivity extends BaseActivity {
    RecyclerView recyclerView;
    ArrayList<DressItem> items;
    ItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbarWithNavigation("Dresses", true);
        initBasket();

        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
        items = new ArrayList<DressItem>();

        items.add(new DressItem(R.drawable.img_1, "Hello",null, 100, 150, 3, 2, true));
        items.add(new DressItem(R.drawable.img_2, "Hello2", null, 140, 170, 4, 3, false));
        items.add(new DressItem(R.drawable.img_2, "Hello3", "Important", 10, -1, 5, 20, true));

        adapter = new ItemRecyclerAdapter(items, this);
        OnDressItemClickListener listener = new OnDressItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent explicitIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                explicitIntent.putExtra(Constants.EXTRA_ITEM, items.get(position));
                startActivity(explicitIntent);
            }
        };

        adapter.setListener(listener);
        // Can be changed to any layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        if (getIntent().getExtras() != null) {
            showNameToast( "Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }

    }
}