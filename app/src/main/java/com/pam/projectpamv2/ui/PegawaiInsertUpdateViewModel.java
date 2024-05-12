package com.pam.projectpamv2.ui;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.repository.PegawaiRepository;

public class PegawaiInsertUpdateViewModel extends ViewModel {
    private final PegawaiRepository mPegawaiRepository;
    public PegawaiInsertUpdateViewModel(Application application) {
        mPegawaiRepository = new PegawaiRepository(application);
    }

    public void insert(Pegawai pegawai) {
        mPegawaiRepository.insert(pegawai);
    }
    public void update(Pegawai pegawai) {
        mPegawaiRepository.update(pegawai);
    }
    public void delete(Pegawai pegawai) {
        mPegawaiRepository.delete(pegawai);
    }
}
