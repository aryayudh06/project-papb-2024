package com.pam.projectpamv2.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Pegawai.class}, version  = 1)
public abstract class PegawaiDB extends RoomDatabase {

    public abstract PegawaiDAO pegawaiDAO();

    private static volatile PegawaiDB INSTANCE;

    public static PegawaiDB getDbInstance(Context context) {

        if (INSTANCE == null){
            synchronized (PegawaiDB.class){
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), PegawaiDB.class, "DB_P")
                                .build();
            }
        }
        return INSTANCE;
    }

}
