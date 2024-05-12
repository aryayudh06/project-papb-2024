package com.pam.projectpamv2.ui;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.repository.PegawaiRepository;

import java.util.List;

public class PegawaiMainViewModel extends ViewModel {
    private final PegawaiRepository mItemRepository;

    public PegawaiMainViewModel(Application application) {
        mItemRepository = new PegawaiRepository(application);
    }
    LiveData<List<Pegawai>> getAllPegawai() {
        return mItemRepository.getAllPegawai();
    }
}

