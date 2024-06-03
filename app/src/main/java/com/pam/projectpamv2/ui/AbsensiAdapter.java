package com.pam.projectpamv2.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.db.Absensi;
import java.util.ArrayList;
import java.util.List;

public class AbsensiAdapter extends RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder> {
    private List<Absensi> absensiList;
    private final Context context;

    public AbsensiAdapter(Context context, List<Absensi> absensiList){
        this.absensiList = absensiList;
        this.context = context;
    }

    @NonNull
    @Override
    public AbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data_absensi, parent, false);
        return new AbsensiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiViewHolder holder, int position) {
        Absensi absensi = absensiList.get(position);
        holder.tvNama.setText(absensi.getNama());
        holder.tvAlamat.setText(absensi.getAlamat());
        holder.tvJamAbsensi.setText(absensi.getJamAbsensi());
        holder.tvTanggal.setText(absensi.getTanggal());
    }

    @Override
    public int getItemCount() {
        return absensiList.size();
    }

    public void setAbsensiList(List<Absensi> absensiList) {
        this.absensiList = absensiList;
        notifyDataSetChanged();
    }

    public static class AbsensiViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat, tvJamAbsensi, tvTanggal;

        public AbsensiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvLokasiAbsensi);
            tvJamAbsensi = itemView.findViewById(R.id.tvJamAbsensi);
            tvTanggal = itemView.findViewById(R.id.tvTanggalAbsensi);
        }
    }
}
