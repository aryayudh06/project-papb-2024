package com.pam.projectpamv2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PegawaiDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pegawai pegawai);
    @Update()
    void update(Pegawai pegawai);
    @Delete()
    void delete(Pegawai pegawai);
    @Query("SELECT * from pegawai ORDER BY id ASC")
    LiveData<List<Pegawai>> getAllPegawai();
    @Query("DELETE FROM pegawai")
    void deleteAllUsers();
}
