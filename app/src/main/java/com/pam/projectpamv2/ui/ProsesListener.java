package com.pam.projectpamv2.ui;

import com.pam.projectpamv2.db.Pegawai;

public interface ProsesListener {
    void onItemClicked(Pegawai model, String input, int status);

}
