package com.pam.projectpamv2.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class daftarPegawaiAdapter extends RecyclerView.Adapter<daftarPegawaiAdapter.PegawaiViewHolder> {

    private List<PegawaiEntity> pegawaiList;
    private Context context;

    public daftarPegawaiAdapter() {
//        this.context = context;
        this.pegawaiList = new ArrayList<>();
    }

    public void setPegawai(List<PegawaiEntity> pegawaiList) {
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
        PegawaiEntity pegawai = pegawaiList.get(position);
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

        public void bind(PegawaiEntity pegawai) {
            textViewNama.setText(pegawai.getNama());
            textViewJK.setText(pegawai.getJenisKelamin());
            textViewJabatan.setText(pegawai.getJabatan());
            textViewKTP.setText(pegawai.getNoKtp());
        }
    }
}




