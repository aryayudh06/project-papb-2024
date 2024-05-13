package com.example.recyclecetakdata.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recyclecetakdata.model.User;

@Database(entities = {User.class}, version = 2, exportSchema = false) // annotation database dengan entyty User
public abstract class MyDatabase extends RoomDatabase { // abstrak class database room

    public abstract Dao userDao(); // variabel dao

    private static volatile MyDatabase INSTANCE; // variabel instance database

    public static MyDatabase getDatabase(final Context context) { // fungsi untuk mendapatkan instance database
        if (INSTANCE == null) { // jika instance belum ada
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), // maka buat instance database
                                    MyDatabase.class, "my_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE; // mengembalikan data instance database
    }
}
