package com.example.androidtest.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
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

        initToolbarWithNavigation("Dresses");

        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
        items = new ArrayList<DressItem>();

        items.add(new DressItem(R.drawable.img_1, "Hello",null, 100, 150, 3, 2));
        items.add(new DressItem(R.drawable.img_2, "Hello2", null, 140, 170, 4, 3));
        items.add(new DressItem(R.drawable.img_2, "Hello3", "Important", 10, -1, 5, 20));


        adapter = new ItemRecyclerAdapter(items, this);


        // Can be changed to any layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }
}