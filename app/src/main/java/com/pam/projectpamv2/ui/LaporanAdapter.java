package com.pam.projectpamv2.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.projectpamv2.components.PegawaiDiffCallback;
import com.pam.projectpamv2.databinding.ActivityLaporanBulananBinding;
import com.pam.projectpamv2.databinding.RowKelolaGajiBinding;
import com.pam.projectpamv2.databinding.RowLaporanBulananBinding;
import com.pam.projectpamv2.db.Pegawai;


import java.util.ArrayList;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    Context context;
    List<Pegawai> laporan = new ArrayList<>();
    ProsesListener listener;

    public LaporanAdapter(Context context, List<Pegawai> laporan, ProsesListener prosesListener){
        this.context = context;
        this.listener = prosesListener;
        setListPegawai(laporan);
    }

    void setListPegawai(List<Pegawai> listPegawai) {
        final PegawaiDiffCallback diffCallback = new PegawaiDiffCallback(this.laporan, listPegawai);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.laporan.clear();
//        this.gajian.addAll(listPegawai);
        for (Pegawai pegawai : listPegawai) {
            if (pegawai.isStatusGaji()) {
                Log.d("info", pegawai.nama);
                this.laporan.add(pegawai);
            }
        }
        diffResult.dispatchUpdatesTo(this);
        if (laporan!= null) {
            getTotal();
        }
    }


    public void getTotal(){
        int x = 0;
        int y = 0;
        for (Pegawai p : laporan){
            y = Integer.parseInt(p.getGaji());
            x += y;
        }

        listener.onTotalChanged(x);
    }

    @NonNull
    @Override
    public LaporanAdapter.LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowLaporanBulananBinding binding = RowLaporanBulananBinding.inflate(inflater, parent, false);
        return new LaporanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {
        holder.bind(laporan.get(position), position);
    }

    public void filter(String query) {
        List<Pegawai> filteredList = new ArrayList<>();
        for (Pegawai pegawai : laporan) {
            if (pegawai.nama.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(pegawai);
            }
        }
        setListPegawai(filteredList);
    }

    @Override
    public int getItemCount() {
        return this.laporan.size();
    }

    public static class LaporanViewHolder extends RecyclerView.ViewHolder {
        final RowLaporanBulananBinding binding;


        public LaporanViewHolder(RowLaporanBulananBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pegawai pegawai, int position) {
            binding.tvNomor.setText(String.valueOf(position + 1));
            binding.tvNamaProses.setText(pegawai.nama);
            binding.tvTanggal.setText(pegawai.getDate());
            binding.tvGajiProses.setText(pegawai.gaji);
        }
    }
}

