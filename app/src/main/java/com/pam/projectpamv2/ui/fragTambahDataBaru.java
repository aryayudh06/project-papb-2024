package com.pam.projectpamv2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.db.Pegawai;

public class fragTambahDataBaru extends Fragment {

    private EditText editTextNama, editTextJK, editTextJabatan, editTextKTP;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_tambah_data_baru, container, false);

        editTextNama = view.findViewById(R.id.editTextText9);
        editTextJK = view.findViewById(R.id.editTextText10);
        editTextJabatan = view.findViewById(R.id.editTextText11);
        editTextKTP = view.findViewById(R.id.editTextText12);

        // Inisialisasi Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("pegawai");

        Button buttonSimpan = view.findViewById(R.id.button3);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahData();
            }
        });

        return view;
    }

    private void tambahData() {
        String nama = editTextNama.getText().toString().trim();
        String jk = editTextJK.getText().toString().trim();
        String jabatan = editTextJabatan.getText().toString().trim();
        String ktp = editTextKTP.getText().toString().trim();

        if (!nama.isEmpty() && !jk.isEmpty() && !jabatan.isEmpty() && !ktp.isEmpty()) {
            String id = databaseReference.push().getKey();
            Pegawai pegawai = new Pegawai(nama, "", false, 0, ktp, "", jabatan, jk);
            databaseReference.child(id).setValue(pegawai);

            Intent resultIntent = new Intent();
            requireActivity().setResult(requireActivity().RESULT_OK, resultIntent);
            requireActivity().finish();

            Intent balikIntent = new Intent(requireActivity(), dataPegawai.class);
            balikIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(balikIntent);
        }
    }
}
