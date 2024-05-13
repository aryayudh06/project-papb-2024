package com.example.recyclecetakdata.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclecetakdata.model.User;
import com.example.recyclecetakdata.repository.UserRepository;

import java.util.List;



public class MainViewModel extends AndroidViewModel {
    private UserRepository mUserRepository; 

    public MainViewModel(Application application) { 
        super(application);
        mUserRepository = new UserRepository(application); 
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserRepository.getAllUsers();
    } 

    public void insert(List<User> user) {
        mUserRepository.insert(user);
    } 
}
