package com.example.androidtest.activity.screens.dresschooser;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidtest.R;
import com.example.androidtest.activity.screens.dressdetails.DressDetails;
import com.example.androidtest.activity.base.BaseActivity;
import com.example.androidtest.activity.base.BasePresenterClass;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.utils.ItemRecyclerAdapter;

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
        this.setPresenter(new DressChooserPresenter(this));
        initToolbarWithNavigation("Dresses", true);
        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
        items = new ArrayList<DressItem>();

        adapterInit();
        presenter.takeView(this);
        initBasket();

        presenter.FireDatabaseToSQL();

        if (getIntent().getExtras() != null) {
            showNameToast("Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }
    }


    private void adapterInit() {
        adapter = new ItemRecyclerAdapter(items, this, presenter);
        OnDressItemClickListener listener = new OnDressItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Intent explicitIntent = new Intent(DressChooser.this, DressDetails.class);
                explicitIntent.putExtra(Constants.EXTRA_ITEM, items.get(position));
                startActivity(explicitIntent);
            }

        };

        adapter.setListener(listener);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void observeItems(List<DressItem> dressItems) {
        items.clear();
        items.addAll(dressItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setHeart(Boolean isPressed) {
        adapter.getmHolder(isPressed);
    }

    @Override
    public void notifyChange() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(DressChooserContract.Presenter presenter) {
        this.presenter = presenter;
        super.presenter = (BasePresenterClass) presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}
