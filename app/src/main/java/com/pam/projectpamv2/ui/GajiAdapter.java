package com.pam.projectpamv2.ui;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.projectpamv2.components.PegawaiDiffCallback;
import com.pam.projectpamv2.R;
import com.pam.projectpamv2.components.ViewModelFactory;
import com.pam.projectpamv2.databinding.RowKelolaGajiBinding;
import com.pam.projectpamv2.db.Pegawai;
import com.pam.projectpamv2.repository.PegawaiRepository;

import java.util.ArrayList;
import java.util.List;

public class GajiAdapter extends RecyclerView.Adapter<GajiAdapter.GajiViewHolder> {

    Context context;
    List<Pegawai> gajian = new ArrayList<>();
    ProsesListener listener;

    public GajiAdapter(Context context, ProsesListener listener) {
        this.context = context;
        this.listener = listener;
    }
    void setListPegawai(List<Pegawai> listPegawai) {
        final PegawaiDiffCallback diffCallback = new PegawaiDiffCallback(this.gajian, listPegawai);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.gajian.clear();
//        this.gajian.addAll(listPegawai);
        for (Pegawai pegawai : listPegawai) {
            if (!pegawai.getStatusGaji()) {
                Log.d("info", pegawai.nama);
                this.gajian.add(pegawai);
            }
        }
        diffResult.dispatchUpdatesTo(this);

    }

    @NonNull
    @Override
    public GajiAdapter.GajiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowKelolaGajiBinding binding = RowKelolaGajiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GajiViewHolder(this.listener, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GajiViewHolder holder, int position) {
        holder.bind(gajian.get(position), position);
        holder.binding.btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.binding.etInput.getText().toString().equals("")) {
                    listener.onItemClicked(gajian.get(position), holder.binding.etInput.getText().toString(), 1);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.gajian.size();
    }

    public static class GajiViewHolder extends RecyclerView.ViewHolder {
        final RowKelolaGajiBinding binding;
        PegawaiRepository mRepository;

        public GajiViewHolder(ProsesListener listener, RowKelolaGajiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pegawai pegawai, int position) {
            binding.tvIndeks.setText(String.valueOf(position+1));
            binding.tvKtp.setText(pegawai.noKtp);
            binding.tvNama.setText(pegawai.nama);

        }

    }
}
