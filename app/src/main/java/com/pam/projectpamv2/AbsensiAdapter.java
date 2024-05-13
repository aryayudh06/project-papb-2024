package ap.mobile.projeksimpeg2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AbsensiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Absensi> absensiList;

    public AbsensiAdapter(Context context, List<Absensi> absensiList) {
        this.context = context;
        this.absensiList = absensiList;
    }

    public class VH extends RecyclerView.ViewHolder{

        private final TextView tvNamaAbsensi;
        private final TextView tvTanggalAbsensi;
        private final TextView tvJamAbsensi;
        private final TextView tvLokasiAbsensi;
        private final ImageView ivFoto;
        private final Button btShow;
        private Absensi absensi;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvNamaAbsensi    = itemView.findViewById(R.id.tvNamaAbsensi);
            this.tvTanggalAbsensi = itemView.findViewById(R.id.tvTanggalAbsensi);
            this.tvJamAbsensi = itemView.findViewById(R.id.tvJamAbsensi);
            this.tvLokasiAbsensi = itemView.findViewById(R.id.tvLokasiAbsensi);
            this.ivFoto = itemView.findViewById(R.id.ivFoto);
            this.btShow = itemView.findViewById(R.id.btShow);
        }

        public void bindAbsensi(Absensi k) {
            this.absensi = k;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context)
                .inflate(R.layout.row_data_kehadiran, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Absensi k = this.absensiList.get(position);
        VH vh = (VH) holder;
        vh.tvNamaAbsensi.setText(k.nama.toString());
        vh.tvTanggalAbsensi.setText(k.tanggal.toString());
        vh.tvJamAbsensi.setText(k.jam.toString());
        vh.tvLokasiAbsensi.setText(k.lokasi.toString());
        vh.ivFoto.setImageResource(k.fotoResourceId);
        vh.bindAbsensi(k);

        vh.btShow.setOnClickListener(v -> {
            String message = k.nama + " telah absen pada tanggal "
                    + k.tanggal + " pukul " + k.jam;
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {

        return absensiList.size();
    }
}
