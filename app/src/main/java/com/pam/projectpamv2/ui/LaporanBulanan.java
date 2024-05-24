package com.pam.projectpamv2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.projectpamv2.R;
import com.pam.projectpamv2.databinding.ActivityLaporanBulananBinding;
import com.pam.projectpamv2.db.Pegawai;

import java.util.List;

public class LaporanBulanan extends AppCompatActivity implements View.OnClickListener, QueryListener {

    private ImageButton ibBack;
    private SearchView svLap;
    private TextView tvGaji;
    private Button btnCetak;
    private RecyclerView rvLaporan;

    public static final String EXTRA_ITEM = "extra_item";
    List<Pegawai> pegawais;
    private boolean isEdit = false;
    private Pegawai pegawai;
    private ActivityLaporanBulananBinding binding;

    LaporanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaporanBulananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        pegawai = getIntent().getParcelableExtra(EXTRA_ITEM);



        adapter = new LaporanAdapter();
//
//        pegawaiList.getAllPegawai().observe(this, item -> {
//            if (item != null) {
//                Log.d("proses", item.get(1).nama);
//                adapter.setListPegawai(item);
//                pegawais = item;
//            }
//        });

        binding.rvLaporanBulanan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvLaporanBulanan.setAdapter(adapter);
        Fragment search = new SearchBar();

        getSupportFragmentManager().beginTransaction().
                add(R.id.flSearch2, search).
                commit();


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
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void getQuery(String query) {
        adapter.setListPegawai(pegawais);
        adapter.filter(query);
        adapter.notifyDataSetChanged();
    }
}
