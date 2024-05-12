package com.pam.projectpamv2.components;

import androidx.recyclerview.widget.DiffUtil;

import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.db.Pegawai;

import java.util.List;

public class PegawaiDiffCallback extends DiffUtil.Callback {
    private final List<Pegawai> mOldPegawaiList;
    private final List<Pegawai> mNewPegawaiList;
    public PegawaiDiffCallback(List<Pegawai> oldPegawaiList, List<Pegawai> newPegawaiList) {
        this.mOldPegawaiList = oldPegawaiList;
        this.mNewPegawaiList = newPegawaiList;
    }
    @Override
    public int getOldListSize() {
        return mOldPegawaiList.size();
    }
    @Override
    public int getNewListSize() {
        return mNewPegawaiList.size();
    }
    @Override

    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldPegawaiList.get(oldItemPosition).id == mNewPegawaiList.get(newItemPosition).id;
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Pegawai oldEmployee = mOldPegawaiList.get(oldItemPosition);
        final Pegawai newEmployee = mNewPegawaiList.get(newItemPosition);
        return oldEmployee.nama.equals(newEmployee.nama) && oldEmployee.statusGaji == (newEmployee.statusGaji);
    }
}
