package com.pam.projectpamv2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.R;

import java.util.ArrayList;
import java.util.List;

public class dataPegawai extends AppCompatActivity {

    private static final int REQUEST_ADD_DATA = 1;
    private daftarPegawaiAdapter pegawaiAdapter;
    private DatabaseReference databaseReference;
    private List<Pegawai> dataPegawaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pegawai);

        RecyclerView recyclerView = findViewById(R.id.tablerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        pegawaiAdapter = new daftarPegawaiAdapter(this);
        recyclerView.setAdapter(pegawaiAdapter);

        // Inisialisasi Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("pegawai");

        // Load data dari Firebase Realtime Database
        loadDataFromFirebase();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, new fragTambahDataBaru()).commit();
            }
        });
    }

    private void loadDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataPegawaiList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pegawai pegawai = snapshot.getValue(Pegawai.class);
                    dataPegawaiList.add(pegawai);
                }
                pegawaiAdapter.setPegawai(dataPegawaiList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_DATA && resultCode == RESULT_OK) {
            // Refresh RecyclerView or reload data from database
            loadDataFromFirebase(); // or any other method to update RecyclerView
        }
    }
}
