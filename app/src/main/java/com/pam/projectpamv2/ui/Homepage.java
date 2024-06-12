package com.pam.projectpamv2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pam.projectpamv2.R;
import com.pam.projectpamv2.components.DateComponent;
import com.pam.projectpamv2.db.Pegawai;


public class Homepage extends AppCompatActivity implements View.OnClickListener {

    private Button btnDataPegawai;
    private Button btnLapBulanan;
    private Button btnDataAbsensi;

    private Button btnPegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        this.btnDataPegawai = this.findViewById(R.id.btnDataPegawai);

        this.btnLapBulanan = this.findViewById(R.id.btnLapBulanan);

        this.btnDataAbsensi = this.findViewById(R.id.btnDataAbsensi);

        this.btnPegawai = this.findViewById(R.id.btnPegawai);

        this.btnDataAbsensi.setOnClickListener(this);

        this.btnLapBulanan.setOnClickListener(this);

        this.btnDataPegawai.setOnClickListener(this);

        this.btnPegawai.setOnClickListener(this);
//
//        Pegawai a = new Pegawai("Adel", "7000000", false, 23, "12323233", DateComponent.getCurrentDate());
//        p.insert(a);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnDataPegawai){
            Intent i = new Intent(this, KelolaGaji.class);
            startActivity(i);
        } else if (v.getId()==R.id.btnLapBulanan) {
            Intent i = new Intent(this, LaporanBulanan.class);
            startActivity(i);
        } else if (v.getId()==R.id.btnDataAbsensi) {
            Intent i = new Intent(this, DataAbsensi.class);
            startActivity(i);
        } else if (v.getId() == R.id.btnPegawai){
            Intent i = new Intent(this, dataPegawai.class);
            startActivity(i);
        }
    }

}