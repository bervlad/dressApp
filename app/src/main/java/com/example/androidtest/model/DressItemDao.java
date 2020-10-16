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
public interface DressItemDao {

    @Query("SELECT * FROM dressItemsTable ORDER BY id ASC")
    LiveData<List<DressItem>> getAll();

    @Query("SELECT * FROM dressItemsTable WHERE id = :id")
    LiveData<DressItem> getById(String id);

    @Insert
    void insert(DressItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<DressItem> items);

    @Update
    void update(DressItem item);

    @Delete
    void delete(DressItem item);

    @Query("DELETE FROM dressItemsTable")
    void deleteAll();

    @Query("SELECT * FROM dressItemsTable")
    List<DressItem> checkIfEmpty();

}
