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
import com.example.androidtest.app.AndroidTest;
import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.database.UserData;
import com.example.androidtest.listeners.Constants;
import com.example.androidtest.listeners.OnDressItemClickListener;
import com.example.androidtest.model.DressItem;
import com.example.androidtest.utils.ItemRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecondActivity extends BaseActivity {
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
        items = ((AndroidTest) getApplication()).initNewArItems();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        adapterInit();
        initData();

        //FireBase realtime database
//        // Write a message to the database
        fdatabase = FirebaseDatabase.getInstance();
        myRef = fdatabase.getReference("item");

//        if (database.dressItemDao().checkIfEmpty().size() == 0) {
//            ((AndroidTest) getApplication()).initItems();
//            items.addAll(((AndroidTest) getApplication()).getItems());
//            Log.d("TAG", "Array size " + items.size());
//            database.dressItemDao().insert(items);
//            Log.d("TAG", "Database initialized");
           FireDatabaseToSQL();
//        }

        if (getIntent().getExtras() != null) {
            showNameToast("Welcome " + getIntent().getStringExtra(Constants.LOG) + " !");
        }
    }

    private void FireDatabaseToSQL() {



        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

//                ((AndroidTest) getApplication()).deleteDatabase("dataUsersDress");
//                items.clear();

                getDatabase().dressItemDao().deleteAll();
                items.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String id = ds.child("id").getValue(String.class);
                    String img_src = ds.child("img_src").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String alert = ds.child("alert").getValue(String.class);
                    int price =ds.child("price").getValue(Integer.class);;
                    int oldPrice= ds.child("oldPrice").getValue(Integer.class);
                    int stars= ds.child("stars").getValue(Integer.class);;
                    int reviews= ds.child("reviews").getValue(Integer.class);
                    String uriLink = ds.child("uri").getValue(String.class);

                    DressItem item = new DressItem(id, img_src, title, alert, price, oldPrice, stars, reviews);
                    item.setUri(uriLink);
                    items.add (item);
                }
                database.dressItemDao().insert(items);
                Log.d("TAG", "Database initialized");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    private void adapterInit() {
        adapter = new ItemRecyclerAdapter(items, this, getDatabase());
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
//
//                myRef.removeValue();
//                for (DressItem item : items) {
//                    setItemToFdatabase (item);
//                }

            });
        }
    }

    private void setItemToFdatabase(DressItem item) {
        String id = item.getImg_src();
        mStorageRef.child("images/" + id + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("TAG", "Uri link for " + item.getTitle() + " obtained: " + uri.toString());
                item.setUri(uri.toString());
                DatabaseReference newRef = myRef.child(item.getId());
                newRef.setValue(item);
                Log.d("TAG", "KEY: " + newRef.getKey());
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("TAG", "No image found");
            }
        });
    }
}