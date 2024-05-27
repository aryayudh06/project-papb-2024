package com.pam.projectpamv2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.db.Absensi;

import java.util.ArrayList;
import java.util.List;

public class DataAbsensi extends AppCompatActivity {

    private Button btBack;
    private AbsensiAdapter absensiAdapter;
    private RecyclerView recyclerView;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_absensi);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("absensi");
        recyclerView = findViewById(R.id.rvAbsensi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        absensiAdapter = new AbsensiAdapter();
        recyclerView.setAdapter(absensiAdapter);

        this.dbRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Absensi> absensiList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Absensi absensi = dataSnapshot.getValue(Absensi.class);
                            absensiList.add(absensi);
                        }
                        absensiAdapter.setAbsensiList(absensiList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                }
        );
    }
}