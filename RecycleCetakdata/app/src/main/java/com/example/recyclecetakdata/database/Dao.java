package com.example.recyclecetakdata.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.recyclecetakdata.model.User;

import java.util.List;

@androidx.room.Dao
public interface Dao { 

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<User> data); 

    @Query("SELECT * from User ORDER BY name ASC")
    LiveData<List<User>> getAllData(); 
}
