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
import com.pam.projectpamv2.components.ViewModelFactory;
import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.db.PegawaiDAO;
import com.pam.projectpamv2.repository.PegawaiRepository;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    private Button btnDataPegawai;
    private Button btnLapBulanan;
    private PegawaiInsertUpdateViewModel pegawaiInsertUpdateViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        this.btnDataPegawai = this.findViewById(R.id.btnDataPegawai);

        this.btnLapBulanan = this.findViewById(R.id.btnLapBulanan);

        this.btnLapBulanan.setOnClickListener(this);

        this.btnDataPegawai.setOnClickListener(this);


        PegawaiRepository p = new PegawaiRepository(this.getApplication());
//
        Pegawai a = new Pegawai("Adel", "7000000", false, 23, "12323233", DateComponent.getCurrentDate());
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
        }
    }

    @NonNull
    private static PegawaiInsertUpdateViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(PegawaiInsertUpdateViewModel.class);
    }
}