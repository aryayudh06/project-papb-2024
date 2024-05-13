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

public class PegawaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Pegawai> pegawaiList;

    public PegawaiAdapter(Context context, List<Pegawai> pegawaiList) {
        this.context = context;
        this.pegawaiList = pegawaiList;
    }

    public class VH extends RecyclerView.ViewHolder{

        private final TextView tvNamaPegawai;
        private final TextView tvGender;
        private final TextView tvJabatan;
        private final TextView tvKTP;
        private final ImageView ivFotoPegawai;
        private final Button btDelete;
        private Pegawai pegawai;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvNamaPegawai   = itemView.findViewById(R.id.tvNamaPegawai);
            this.tvGender = itemView.findViewById(R.id.tvGender);
            this.tvJabatan = itemView.findViewById(R.id.tvJabatan);
            this.tvKTP = itemView.findViewById(R.id.tvKTP);
            this.ivFotoPegawai = itemView.findViewById(R.id.ivFotoPegawai);
            this.btDelete = itemView.findViewById(R.id.btDelete);
        }

        public void bindPegawai(Pegawai k) {
            this.pegawai = k;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context)
                .inflate(R.layout.row_pegawai, parent, false);
        return new PegawaiAdapter.VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pegawai k = this.pegawaiList.get(position);
        PegawaiAdapter.VH vh = (PegawaiAdapter.VH) holder;
        vh.tvNamaPegawai.setText(k.namaPegawai.toString());
        vh.tvGender.setText(k.gender.toString());
        vh.tvJabatan.setText(k.jabatan.toString());
        vh.tvKTP.setText(k.ktp.toString());
        vh.ivFotoPegawai.setImageResource(k.fotoId);
        vh.bindPegawai(k);

        vh.btDelete.setOnClickListener(v -> {
            int deletePosition = holder.getAdapterPosition();
            pegawaiList.remove(deletePosition);
            notifyItemRemoved(deletePosition);
            String message = " Data a.n. "
                    + k.namaPegawai + " telah dihapus";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }
}
