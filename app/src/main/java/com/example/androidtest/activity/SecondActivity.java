package com.example.androidtest.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.database.UserData;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.utils.ItemRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecondActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<DressItem> items;
    private ItemRecyclerAdapter adapter;
    private FirebaseUser mUser;
    private UserData userData;
    private AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbarWithNavigation("Dresses", true);
        initBasket();

        mUser = (FirebaseAuth.getInstance()).getCurrentUser();
        userData = ((AndroidTest)getApplication()).getUsersWithInfo();
        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);

        items = ((AndroidTest) getApplication()).initNewArItems();

        adapterInit();
        initData();

        if (database.dressItemDao().checkIfEmpty().size() == 0) {
            ((AndroidTest) getApplication()).initItems();
            items.addAll (((AndroidTest)getApplication()).getItems());
            database.dressItemDao().insert(items);
            Log.d("TAG", "Database initialized");
        }

//        if (items.size()==0) {
//            items.addAll(database.dressItemDao().checkIfEmpty());
//            Log.d("TAG", "Items initialized " + items.size());
//        }


        if (getIntent().getExtras() != null) {
            showNameToast( "Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }
    }

    private void adapterInit() {
        adapter = new ItemRecyclerAdapter(items, this, userData);
        OnDressItemClickListener listener = new OnDressItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent explicitIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                explicitIntent.putExtra(Constants.EXTRA_ITEM, items.get(position));
                startActivity(explicitIntent);
            }

            @Override
            public UserData onHeartClick() {
                return getUserData();
            }

        };

        adapter.setListener(listener);
        // Can be changed to any layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        database =getDatabase();
        if (database!=null) {
            database.dressItemDao().getAll().observe(this, (List<DressItem> dressItems) -> {
                        items.clear();
                        items.addAll(dressItems);
                        adapter.notifyDataSetChanged();
                    });
        }
    }
}