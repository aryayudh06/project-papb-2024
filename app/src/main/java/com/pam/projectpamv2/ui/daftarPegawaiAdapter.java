package com.pam.projectpamv2.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.db.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class daftarPegawaiAdapter extends RecyclerView.Adapter<daftarPegawaiAdapter.PegawaiViewHolder> {

    private List<Pegawai> pegawaiList;
    private Context context;
    private DatabaseReference dbRef;

    public daftarPegawaiAdapter(Context context) {
        this.context = context;
        this.pegawaiList = new ArrayList<>();
        this.dbRef = FirebaseDatabase.getInstance().getReference("pegawai");
        fetchPegawaiData();
    }

    private void fetchPegawaiData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Pegawai> newList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pegawai pegawai = dataSnapshot.getValue(Pegawai.class);
                    newList.add(pegawai);
                }
                setPegawai(newList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Database Error", error.toException());
            }
        });
    }

    public void setPegawai(List<Pegawai> pegawaiList) {
        this.pegawaiList = pegawaiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PegawaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_table, parent, false);
        return new PegawaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PegawaiViewHolder holder, int position) {
        Pegawai pegawai = pegawaiList.get(position);
        holder.textViewNama.setText(pegawai.getNama());
        holder.textViewJK.setText(pegawai.getJenisKelamin());
        holder.textViewJabatan.setText(pegawai.getJabatan());
        holder.textViewKTP.setText(pegawai.getNoKtp());
    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }

    public class PegawaiViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNama, textViewJK, textViewJabatan, textViewKTP;

        public PegawaiViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewJK = itemView.findViewById(R.id.textViewJK);
            textViewJabatan = itemView.findViewById(R.id.textViewJabatan);
            textViewKTP = itemView.findViewById(R.id.textViewKTP);
        }

        public void bind(Pegawai pegawai) {
            textViewNama.setText(pegawai.getNama());
            textViewJK.setText(pegawai.getJenisKelamin());
            textViewJabatan.setText(pegawai.getJabatan());
            textViewKTP.setText(pegawai.getNoKtp());
        }
    }
}
