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
public interface UserDressItemDao {

    @Query("SELECT * FROM userDressItemsTable")
    LiveData<List<UserDressItem>> getAll();

    @Query("SELECT dressId FROM userDressItemsTable WHERE email = :id")
    LiveData<String> getDressesByUser(String id);

    @Query("SELECT dressId FROM userDressItemsTable WHERE email = :email")
    List<String> getDressesByUserEmail(String email);

    @Insert
    void insert(UserDressItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<UserDressItem> items);

    @Update
    void update(UserDressItem item);

    @Delete
    void delete(UserDressItem item);

    @Query("DELETE FROM userDressItemsTable")
    void deleteAll();

    @Query ("DELETE FROM userDressItemsTable WHERE email= :email AND dressId= :dressId")
    void deleteLikeFromUser (String email, String dressId);

    @Query("SELECT * FROM userDressItemsTable")
    List<UserDressItem> checkIfEmpty();
}
