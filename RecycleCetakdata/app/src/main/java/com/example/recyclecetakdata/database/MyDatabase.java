package com.example.recyclecetakdata.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recyclecetakdata.model.User;

@Database(entities = {User.class}, version = 2, exportSchema = false) 
public abstract class MyDatabase extends RoomDatabase { 

    public abstract Dao userDao(); 

    private static volatile MyDatabase INSTANCE; 

    public static MyDatabase getDatabase(final Context context) { 
        if (INSTANCE == null) { 
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), 
                                    MyDatabase.class, "my_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE; 
    }
}
