package com.pam.projectpamv2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.db.PegawaiDAO;
import com.pam.projectpamv2.db.PegawaiDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PegawaiRepository {
    private final PegawaiDAO mPegawaiDao;

    private final ExecutorService executorService;

    public PegawaiRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        PegawaiDB db = PegawaiDB.getDbInstance(application);
        mPegawaiDao = db.pegawaiDAO();
    }

    public LiveData<List<Pegawai>> getAllPegawai() {
        return mPegawaiDao.getAllPegawai();
    }

    public void insert(final Pegawai pegawai) {
        executorService.execute(() -> mPegawaiDao.insert(pegawai));
    }

    public void delete(final  Pegawai pegawai) {
        executorService.execute(() -> mPegawaiDao.delete(pegawai));
    }

    public void update(final  Pegawai pegawai) {
        executorService.execute(() -> mPegawaiDao.update(pegawai));
    }

}
