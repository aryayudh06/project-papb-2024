package com.example.recyclecetakdata.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.recyclecetakdata.database.Dao;
import com.example.recyclecetakdata.database.MyDatabase;
import com.example.recyclecetakdata.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// fungsi repository untuk akses database
// logika bisnis seperti mengambil data dari database tidak diletakkan di UI
public class UserRepository {
    private final Dao mUsersDao; // membuat variabel dao
    private final ExecutorService executorService; // membuat variabel executorService untuk handle operasi background yang berhubungan dengan database
    public UserRepository(Application application) { // constructor
        MyDatabase db = MyDatabase.getDatabase(application); // membuat instance database
        mUsersDao = db.userDao(); // set variabel dao dengan memanggil dao dari database
        executorService = Executors.newSingleThreadExecutor(); // set executorService
    }

    public LiveData<List<User>> getAllUsers() {
        return mUsersDao.getAllData();
    } // fungsi untuk mengambil semua data dalam bentuk list

    public void insert(List<User> user) {
        executorService.execute(() -> mUsersDao.insert(user));
    } // fungsi untuk insert semua data Array ke room database

}
