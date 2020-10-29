package com.example.androidtest.activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.activity.screens.introduction.IntroductionContract;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.utils.ItemRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class DressChooser extends BaseActivity implements DressChooserContract.View {
    private RecyclerView recyclerView;
    private ArrayList<DressItem> items;
    private ItemRecyclerAdapter adapter;

    DressChooserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbarWithNavigation("Dresses", true);
        initBasket();

        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
        items = new ArrayList<DressItem>();

        adapterInit();

        this.setPresenter(new DressChooserPresenter (this));
        presenter.takeView(this);
        presenter.FireDatabaseToSQL();

        if (getIntent().getExtras() != null) {
            showNameToast("Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }
    }


    private void adapterInit() {
        adapter = new ItemRecyclerAdapter(items, this, getDatabase());
        OnDressItemClickListener listener = new OnDressItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent explicitIntent = new Intent(DressChooser.this, DressDetails.class);
                explicitIntent.putExtra(Constants.EXTRA_ITEM, items.get(position));
                startActivity(explicitIntent);
            }

            @Override
            public void onItemClick(View v, int position) {

            }

        };

        adapter.setListener(listener);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void setPresenter(DressChooserContract.Presenter presenter) {
        this.presenter=presenter;
    }


    @Override
    public void observeItems(LiveData<List<DressItem>> itemsLiveData) {
            itemsLiveData.observe(this, (List<DressItem> dressItems) -> {
            items.clear();
            items.addAll(dressItems);
            adapter.notifyDataSetChanged();
        });
    }
}