package com.example.androidtest.activity.base;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.database.AppDatabase;
import com.example.androidtest.model.BasketItem;
import com.example.androidtest.model.DressItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FireBase {

    AppDatabase database;

    StorageReference mStorageRef;
    FirebaseDatabase fdatabase;
    DatabaseReference myRef;


    public void initAuth (HashSet<BasketItem> basketItems) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            basketItems.clear();
        }
    }

    public void FireDatabaseToSQL (AppDatabase database) {
        this.database = database;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        fdatabase = FirebaseDatabase.getInstance();
        myRef = fdatabase.getReference("item");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                database.dressItemDao().deleteAll();
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

        ArrayList<DressItem> tempList = new ArrayList<>();

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

                //database.dressItemDao().insert(item);
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

    public FirebaseUser getUser () {
        return (FirebaseAuth.getInstance()).getCurrentUser();
    }

    public void logPassAuth (String email, String password, BasePresenterClass presenter, AppCompatActivity activity) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                presenter.loginSuccess(user);

                            } else {
                                presenter.loginFailed (task);
                            }
                        }
                    });
        }
    }
