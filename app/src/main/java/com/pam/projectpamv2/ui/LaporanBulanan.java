package com.pam.projectpamv2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.databinding.ActivityLaporanBulananBinding;
import com.pam.projectpamv2.db.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class LaporanBulanan extends AppCompatActivity implements View.OnClickListener, QueryListener, ProsesListener{


    List<Pegawai> pegawais;

    private ActivityLaporanBulananBinding binding;
    private int totalGaji = 0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    LaporanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaporanBulananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("pegawai");
        pegawais = new ArrayList<>();

//        adapter = new LaporanAdapter();
//
//        pegawaiList.getAllPegawai().observe(this, item -> {
//            if (item != null) {
//                Log.d("proses", item.get(1).nama);
//                adapter.setListPegawai(item);
//                pegawais = item;
//            }
//        });

//        binding.rvLaporanBulanan.setLayoutManager(new LinearLayoutManager(this));
//        binding.rvLaporanBulanan.setAdapter(adapter);


        readDataFromDatabase();
        Fragment search = new SearchBar();

        getSupportFragmentManager().beginTransaction().
                add(R.id.flSearch2, search).
                commit();



//        this.svLap.setOnQueryTextListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCetak) {
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

    public void readDataFromDatabase() {
        DatabaseReference pegawaiRef = myRef;  // pastikan membaca dari node "pegawai"
        pegawaiRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pegawais.clear(); // Clear the list before adding new data
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Pegawai pegawai = noteSnapshot.getValue(Pegawai.class);
                    if (pegawai != null) {
                        pegawais.add(pegawai);
                        Log.d("mNotes", pegawai.toString());
                    } else {
                        Log.w("mNotes", "Pegawai data is null");
                    }
                }
                // Set up RecyclerView adapter here after data is fully loaded
                if (adapter == null) {
                    setUpRecyclerView();
                } else {
                    adapter.setListPegawai(pegawais);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LaporanBulanan.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView() {
        this.adapter = new LaporanAdapter(this, pegawais,this);
        binding.rvLaporanBulanan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvLaporanBulanan.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Pegawai model, String input, int status) {

    }

    @Override
    public void onTotalChanged(int total) {
        Log.d("total gaji", String.valueOf(total));
        binding.tvTotal1.setText(String.format("Rp %d,00", total));
    }
}
