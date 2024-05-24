package com.pam.projectpamv2.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pegawai {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama")
    public String nama = "";

    @ColumnInfo(name = "gaji")
    public String gaji = "";

    @ColumnInfo(name = "usia")
    public int usia = 0;

    @ColumnInfo(name = "statusGaji")
    public boolean statusGaji;

    @ColumnInfo(name = "noKtp")
    public String noKtp = "";

    @ColumnInfo(name = "date")
    private String date = "";

    @Ignore
    public Pegawai(){

    }
    public Pegawai(String nama, String gaji, boolean statusGaji, int usia, String noKtp, String date) {
        this.nama = nama;
        this.gaji = gaji;
        this.statusGaji = statusGaji;
        this.noKtp = noKtp;
        this.date = date;
        this.usia = usia;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }



    public String getNama() {
        return nama;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getGaji() {
        return gaji;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public void setStatusGaji(boolean statusGaji) {
        this.statusGaji = statusGaji;
    }

    public boolean getStatusGaji() {
        return statusGaji;
    }

}
