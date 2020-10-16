package com.example.androidtest.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;
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

public class DressChooser extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<DressItem> items;
    private ItemRecyclerAdapter adapter;
    private AppDatabase database;
    StorageReference mStorageRef;
    FirebaseDatabase fdatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbarWithNavigation("Dresses", true);
        initBasket();

        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
        items = new ArrayList<DressItem>();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        adapterInit();
        initData();

        fdatabase = FirebaseDatabase.getInstance();
        myRef = fdatabase.getReference("item");

        FireDatabaseToSQL();

        if (getIntent().getExtras() != null) {
            showNameToast("Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }
    }


    private void adapterInit() {
        adapter = new ItemRecyclerAdapter(items, this, getDatabase());
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

    private void initData() {
        database = getDatabase();
        if (database != null) {
            database.dressItemDao().getAll().observe(this, (List<DressItem> dressItems) -> {
                items.clear();
                items.addAll(dressItems);
                adapter.notifyDataSetChanged();
            });
        }
    }

    private void FireDatabaseToSQL() {

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getDatabase().dressItemDao().deleteAll();
                setItems(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    private void setItems(DataSnapshot dataSnapshot) {

        ArrayList <DressItem> tempList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String id = ds.child("id").getValue(String.class);
            String img_src = ds.child("img_src").getValue(String.class);
            String title = ds.child("title").getValue(String.class);
            String alert = ds.child("alert").getValue(String.class);
            int price = ds.child("price").getValue(Integer.class);
            int oldPrice = ds.child("oldPrice").getValue(Integer.class);
            int stars = ds.child("stars").getValue(Integer.class);
            int reviews = ds.child("reviews").getValue(Integer.class);
            DressItem item = new DressItem(id, img_src, title, alert, price, oldPrice, stars, reviews);

            setUriAndAddtoSQL(item, tempList, dataSnapshot);
        }

    }

    private void setUriAndAddtoSQL(DressItem item, ArrayList <DressItem> tempList, DataSnapshot dataSnapshot) {
        String id = item.getImg_src();
        mStorageRef.child("images/" + id + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("TAG", "Uri link for " + item.getTitle() + " obtained: " + uri.toString());
                item.setUri(uri.toString());
                tempList.add(item);
                checkAndAddToDB(tempList, dataSnapshot);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("TAG", "No image found");
                tempList.add(item);
                checkAndAddToDB(tempList, dataSnapshot);
            }
        });
    }

    private void checkAndAddToDB(ArrayList<DressItem> tempList, DataSnapshot dataSnapshot) {
        if (tempList.size()==dataSnapshot.getChildrenCount()) {
            database.dressItemDao().insert(tempList);
        }
    }
}