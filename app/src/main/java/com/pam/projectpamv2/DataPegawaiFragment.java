package ap.mobile.projeksimpeg2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DataPegawaiFragment extends Fragment {
    private Button btBack2;
    private List<Pegawai> dataPegawai;
    private RecyclerView rvPegawai;
    private PegawaiAdapter pegawaiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_pegawai, container, false);

        this.dataPegawai = new ArrayList<>();
        this.rvPegawai = view.findViewById(R.id.rvPegawai);
        this.pegawaiAdapter = new PegawaiAdapter(getActivity(), this.dataPegawai);

        Pegawai a = new Pegawai("Steven Hot Asi Sihite", "Laki-Laki", "CEO", "12345678", R.drawable.ic_photo);
        Pegawai b = new Pegawai("Arya Yudha", "Laki-Laki", "HRD", "90123456", R.drawable.ic_photo2);
        Pegawai c = new Pegawai("Jack Ma", "Laki-Laki", "Senior Manager", "44135790", R.drawable.ic_photo5);
        Pegawai d = new Pegawai("M Bintang Nur", "Laki-Laki", "Karyawan", "87650932", R.drawable.ic_photo4);
        Pegawai e = new Pegawai("Kevin Yoshua", "Laki-Laki", "Karyawan", "53197024", R.drawable.ic_foto3);

        this.dataPegawai.add(a);this.dataPegawai.add(b);
        this.dataPegawai.add(c);this.dataPegawai.add(d);
        this.dataPegawai.add(e);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);

        this.rvPegawai.setLayoutManager(lm);
        this.rvPegawai.setAdapter(pegawaiAdapter);

        return view;
    }
}