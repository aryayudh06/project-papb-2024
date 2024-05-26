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
import com.pam.projectpamv2.databinding.RowKelolaGajiBinding;
import com.pam.projectpamv2.db.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class GajiAdapter extends RecyclerView.Adapter<GajiAdapter.GajiViewHolder> {

    Context context;
    List<Pegawai> gajian = new ArrayList<>();
    ProsesListener listener;

    public GajiAdapter(Context context, List<Pegawai> gajian, ProsesListener listener) {
        this.context = context;
        setListPegawai(gajian);
        this.listener = listener;
    }
    void setListPegawai(List<Pegawai> listPegawai) {
        final PegawaiDiffCallback diffCallback = new PegawaiDiffCallback(this.gajian, listPegawai);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.gajian.clear();
        for (Pegawai pegawai : listPegawai) {
            if (!pegawai.isStatusGaji()) {
//                Log.d("info", pegawai.isStatusGaji());
                this.gajian.add(pegawai);
                Log.d("info", pegawai.toString());
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
                    listener.onItemClicked(gajian.get(holder.getBindingAdapterPosition()), holder.binding.etInput.getText().toString(), 1, holder.getBindingAdapterPosition());
                }
            }
        });
    }
    public void filter(String query) {
        List<Pegawai> filteredList = new ArrayList<>();
        for (Pegawai pegawai : gajian) {
            if (pegawai.nama.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(pegawai);
            }
        }
        setListPegawai(filteredList);
    }



    @Override
    public int getItemCount() {
        return this.gajian.size();
    }

    public static class GajiViewHolder extends RecyclerView.ViewHolder {
        final RowKelolaGajiBinding binding;

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
