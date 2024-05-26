package com.pam.projectpamv2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.components.DateComponent;

import com.pam.projectpamv2.databinding.ActivityKelolaGajiBinding;
import com.pam.projectpamv2.db.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class KelolaGaji extends AppCompatActivity implements ProsesListener, QueryListener{

    private ImageButton ibBack;
    private RecyclerView rvItemPeg;
    private FrameLayout flSearch;
    ActivityKelolaGajiBinding binding;
    private GajiAdapter gajiAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Pegawai> pegawais;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("pegawai");
        pegawais = new ArrayList<>();

        Pegawai a = new Pegawai();
        a.setNama("Doni");
        a.setGaji("10000000");
        a.setStatusGaji(false);
        a.setNoKtp("1234567889");
//        pegawaiInsertUpdateViewModel.insert(a);

//        myRef.child("pegawai").setValue(a);

        flSearch = findViewById(R.id.flSearch);
        Fragment search = new SearchBar();

        binding = ActivityKelolaGajiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        readDataFromDatabase();
//        setUpRecyclerView();

        getSupportFragmentManager().beginTransaction().
                add(R.id.flSearch, search).
                commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
//        readDataFromDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
//    @NonNull
//    private static PegawaiMainViewModel
//    obtainViewModel(AppCompatActivity activity) {
//        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
//
//        return new ViewModelProvider(activity, factory).get(PegawaiMainViewModel.class);
//    }
//    @NonNull
//    private static PegawaiInsertUpdateViewModel
//    obtainViewModelCRUD(AppCompatActivity activity) {
//        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
//        return new ViewModelProvider(activity, factory).get(PegawaiInsertUpdateViewModel.class);
//    }

    @Override
    public void onItemClicked(Pegawai pegawai, String input, int status, int position) {
        String x = input;
//        Intent i = new Intent(this, KelolaGaji.class);
        boolean mStatusGaji = false;
        if (status==1){
            mStatusGaji = true;
        }

      updateData(pegawai, input, mStatusGaji, DateComponent.getCurrentDate());
//        gajiAdapter.notifyItemChanged(position);


//        Toast.makeText(this, "Memproses "+ pegawai.nama, Toast.LENGTH_SHORT).show();
//        startActivity(i);
    }

    public void updateData(Pegawai pegawaiOld, String input, boolean status, String date){
        DatabaseReference notesRef = myRef;
        Query updateQuery = notesRef.orderByChild("nama").equalTo(pegawaiOld.getNama());

        updateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Gunakan setValue untuk memperbarui catatan dengan data yang baru
                    snapshot.getRef().child("gaji").setValue(input);
                    snapshot.getRef().child("date").setValue(date);
                    snapshot.getRef().child("statusGaji").setValue(status)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(KelolaGaji.this, "Gaji updated successfully", Toast.LENGTH_SHORT).show();
                                    readDataFromDatabase();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(KelolaGaji.this, "Failed to update Gaji", Toast.LENGTH_SHORT).show();
                                }

                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KelolaGaji.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getQuery(String query) {
        gajiAdapter.setListPegawai(pegawais);
        gajiAdapter.filter(query);
        gajiAdapter.notifyDataSetChanged();
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
                if (gajiAdapter == null) {
                    setUpRecyclerView();
                } else {
                    gajiAdapter.setListPegawai(pegawais);
                    gajiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KelolaGaji.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView() {
        this.gajiAdapter = new GajiAdapter(this, pegawais, this);
        rvItemPeg = findViewById(R.id.rvItemPeg);
        rvItemPeg.setLayoutManager(new LinearLayoutManager(this));
        rvItemPeg.setAdapter(gajiAdapter);
    }

}




