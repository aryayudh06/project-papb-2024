package com.pam.projectpamv2.db;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Absensi {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama")
    public String nama = "";

    @ColumnInfo(name = "alamat")
    public String alamat = "";

    @ColumnInfo(name = "jamAbsensi")
    public String jamAbsensi = "";

    @ColumnInfo(name = "tanggal")
    public String tanggal = "";

    public Absensi(){

    }
    
    public Absensi(String nama, String alamat, String jamAbsensi, String tanggal) {
        this.nama = nama;
        this.alamat = alamat;
        this.jamAbsensi = jamAbsensi;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat(){
        return this.alamat;
    }

    public void setAlamat(){
        this.alamat = alamat;
    }

    public String getJamAbsensi(){
        return this.jamAbsensi;
    }

    public void setJamAbsensi(){
        this.jamAbsensi = jamAbsensi;
    }

    public String getTanggal(){
        return this.tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
