package com.pam.projectpamv2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclecetakdata.R;
import com.example.recyclecetakdata.model.User;

import java.util.ArrayList;

public class ListDataAdapter extends  RecyclerView.Adapter<ListDataAdapter.ListViewHolder> {
    ArrayList<User> listdata;
    public ListDataAdapter(ArrayList<User> list) {
        this.listdata = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        User data =listdata.get(position);
        holder.imgPhoto.setImageDrawable(holder.itemView.getContext().getDrawable(data.getPhoto()));
        holder.tv_item_nama.setText(data.getName());
        holder.tv_item_keterangan.setText(data.getDetail());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tv_item_nama;
        TextView tv_item_keterangan;
        public ListViewHolder(View itemview) {
            super(itemview);
            imgPhoto = itemview.findViewById(R.id.img_item_person);
            tv_item_nama = itemview.findViewById(R.id.tv_item_nama);
            tv_item_keterangan = itemview.findViewById(R.id.tv_item_keterangan);
        }
    }
}
