package com.example.recyclecetakdata.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.recyclecetakdata.database.Dao;
import com.example.recyclecetakdata.database.MyDatabase;
import com.example.recyclecetakdata.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class UserRepository {
    private final Dao mUsersDao; 
    private final ExecutorService executorService; 
    public UserRepository(Application application) { 
        MyDatabase db = MyDatabase.getDatabase(application); 
        mUsersDao = db.userDao(); 
        executorService = Executors.newSingleThreadExecutor(); 
    }

    public LiveData<List<User>> getAllUsers() {
        return mUsersDao.getAllData();
    } 

    public void insert(List<User> user) {
        executorService.execute(() -> mUsersDao.insert(user));
    } 

}
