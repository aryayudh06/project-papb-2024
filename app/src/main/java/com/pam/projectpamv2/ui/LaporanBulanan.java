package com.pam.projectpamv2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.pam.projectpamv2.R;
import com.pam.projectpamv2.components.ViewModelFactory;
import com.pam.projectpamv2.databinding.ActivityKelolaGajiBinding;
import com.pam.projectpamv2.databinding.ActivityLaporanBulananBinding;
import com.pam.projectpamv2.db.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class LaporanBulanan extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener, SearchView.OnQueryTextListener {

    private ImageButton ibBack;
    private SearchView svLap;
    private TextView tvGaji;
    private Button btnCetak;
    private RecyclerView rvLaporan;

    public static final String EXTRA_ITEM = "extra_item";
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;
    private boolean isEdit = false;
    private Pegawai pegawai;
    private ActivityLaporanBulananBinding binding;
    private PegawaiMainViewModel pegawaiMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaporanBulananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pegawaiMainViewModel = obtainViewModel(LaporanBulanan.this);
        pegawai = getIntent().getParcelableExtra(EXTRA_ITEM);
        PegawaiMainViewModel pegawaiList = obtainViewModel(this);


        LaporanAdapter adapter = new LaporanAdapter();

        pegawaiList.getAllPegawai().observe(this, item -> {
            if (item != null) {
                Log.d("proses", item.get(1).nama);
                adapter.setListPegawai(item);
            }
        });

        binding.rvLaporanBulanan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvLaporanBulanan.setAdapter(adapter);

        binding.ibBack.setOnClickListener(this);

//        this.svLap.setOnQueryTextListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ibBack) {
            Intent i = new Intent(this, Homepage.class);
            startActivity(i);
        } else if (v.getId() == R.id.btnCetak) {
            Intent i = new Intent(this, LaporanBulanan.class);
            startActivity(i);
        }
    }

    public boolean onQueryTextSubmit(String query) {
        // performSearch(query);
        Intent i = new Intent(this, LaporanBulanan.class);
        startActivity(i);
        finish();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @NonNull
    private static PegawaiMainViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(PegawaiMainViewModel.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
