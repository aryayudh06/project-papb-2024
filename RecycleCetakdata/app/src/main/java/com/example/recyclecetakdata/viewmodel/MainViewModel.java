package com.example.recyclecetakdata.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclecetakdata.model.User;
import com.example.recyclecetakdata.repository.UserRepository;

import java.util.List;

// ViewModel adalah komponen penting dalam arsitektur Model-View-ViewModel (MVVM)
// yang membantu memisahkan logika UI dari logika bisnis.
// viewModel di bawah berfungsi untuk handle data di UI
// Urutannya Database > Repository > ViewModeal > UI (Activity)

public class MainViewModel extends AndroidViewModel {
    private UserRepository mUserRepository; // membuat variable repository

    public MainViewModel(Application application) { // constructor
        super(application);
        mUserRepository = new UserRepository(application); // set nilai variable repository
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserRepository.getAllUsers();
    } // fungsi untuk mendapatkan semua data yang nantinya dapat di akses di Activity

    public void insert(List<User> user) {
        mUserRepository.insert(user);
    } // fungsi untuk input semua data yang nantinya dapat di akses di Activity
}
