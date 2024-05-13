package com.example.recyclecetakdata.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.recyclecetakdata.model.User;

import java.util.List;

@androidx.room.Dao
public interface Dao { //membuat interface DAO

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<User> data); // fungsi untuk insert data Array ke Room

    @Query("SELECT * from User ORDER BY name ASC")
    LiveData<List<User>> getAllData(); // fungsi untuk mengambil semua data dalam List
}