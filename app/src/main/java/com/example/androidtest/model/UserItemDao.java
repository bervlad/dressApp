package com.example.androidtest.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserItemDao {
    @Query("SELECT * FROM userItemsTable")
    LiveData<List<UserItem>> getAll();

    @Query("SELECT * FROM userItemsTable WHERE email = :email")
    LiveData<UserItem> getEmail(String email);

    @Insert
    void insert(UserItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<UserItem> items);

    @Update
    void update(UserItem item);

    @Delete
    void delete(UserItem item);

    @Query("DELETE FROM userItemsTable")
    void deleteAll();

    @Query("SELECT * FROM userItemsTable")
    List<UserItem> checkIfEmpty();

    @Query("SELECT * FROM userItemsTable WHERE email = :email")
    List<UserItem> checkEmail(String email);

}
