package com.pam.projectpamv2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pam.projectpamv2.R;
import com.pam.projectpamv2.components.DateComponent;
import com.pam.projectpamv2.components.ViewModelFactory;
import com.pam.projectpamv2.databinding.ActivityKelolaGajiBinding;
import com.pam.projectpamv2.db.Pegawai;

public class KelolaGaji extends AppCompatActivity implements ProsesListener{

    private ImageButton ibBack;
    private RecyclerView rvItemPeg;
    ActivityKelolaGajiBinding binding;
    private GajiAdapter gajiAdapter;
    PegawaiInsertUpdateViewModel pegawaiInsertUpdateViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKelolaGajiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gajiAdapter = new GajiAdapter(this, this);

        PegawaiMainViewModel itemMainViewModel = obtainViewModel(KelolaGaji.this);
        pegawaiInsertUpdateViewModel = obtainViewModelCRUD(KelolaGaji.this);

        itemMainViewModel.getAllPegawai().observe(this, item -> {
            if (item != null) {
                Log.d("test", item.get(1).nama);
                gajiAdapter.setListPegawai(item);
            }
        });

//        Pegawai a = new Pegawai();
//        a.setNama("Doni");
//        a.setGaji("10000000");
//        a.setStatusGaji(false);
//        a.setNoKtp("1234567889");
//        pegawaiInsertUpdateViewModel.insert(a);


        binding.rvItemPeg.setLayoutManager(new LinearLayoutManager(this));
//        binding.rvItemPeg.setHasFixedSize(true);
        binding.rvItemPeg.setAdapter(gajiAdapter);
        binding.ibBack.setOnClickListener(view -> {
            if (view.getId() == R.id.ibBack) {
                Intent intent = new Intent(KelolaGaji.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    @NonNull
    private static PegawaiMainViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return new ViewModelProvider(activity, factory).get(PegawaiMainViewModel.class);
    }
    @NonNull
    private static PegawaiInsertUpdateViewModel
    obtainViewModelCRUD(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(PegawaiInsertUpdateViewModel.class);
    }

    @Override
    public void onItemClicked(Pegawai pegawai, String input, int status) {
        String x = input;
        Intent i = new Intent(this, Homepage.class);

        pegawai.setStatusGaji(true);
        pegawai.setGaji(input);
        pegawai.setDate(DateComponent.getCurrentDate());
        pegawaiInsertUpdateViewModel.update(pegawai);
        i.putExtra("gaji", x);

        Toast.makeText(this, "Memproses "+ pegawai.nama, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}




