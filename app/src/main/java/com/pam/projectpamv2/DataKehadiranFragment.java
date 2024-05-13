package ap.mobile.projeksimpeg2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataKehadiranFragment extends Fragment {

    private List<Absensi> dataAbsensi;
    private RecyclerView rvAbsensi;
    private AbsensiAdapter absensiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_kehadiran, container, false);

        this.dataAbsensi = new ArrayList<>();

        Absensi a = new Absensi("Steven Sihite", "07/07/2023", "08.00 WIB", "Jl. Soehat No. 24", R.drawable.ic_photo);
        Absensi b = new Absensi("Arya Yudha", "07/07/2023", "07.50", "Jl. Kerto Raharjo No. 1", R.drawable.ic_photo2);
        Absensi c = new Absensi("M Bintang Nur", "07/07/2023", "07.59 WIB", "Jl. Watu Gong No. 33", R.drawable.ic_foto3);
        Absensi d = new Absensi("Kevin Yosua", "07/07/2023", "08.20", "Jl. Dinoyo No. 3", R.drawable.ic_photo4);
        Absensi e = new Absensi("Jack Ma", "07/07/2023", "07.00", "Jl. Sumbersari No. 1", R.drawable.ic_photo5);

        this.dataAbsensi.add(a);
        this.dataAbsensi.add(b);
        this.dataAbsensi.add(c);
        this.dataAbsensi.add(d);
        this.dataAbsensi.add(e);

        this.rvAbsensi = view.findViewById(R.id.rvAbsensi);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        this.rvAbsensi.setLayoutManager(lm);

        this.absensiAdapter = new AbsensiAdapter(getActivity(), this.dataAbsensi);
        this.rvAbsensi.setAdapter(absensiAdapter);

        return view;
    }
}
