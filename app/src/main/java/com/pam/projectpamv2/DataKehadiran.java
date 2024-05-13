package ap.mobile.projeksimpeg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DataKehadiran extends AppCompatActivity implements View.OnClickListener {

    private Button btBack;
    private List<Absensi> dataAbsensi;
    private RecyclerView rvAbsensi;
    private AbsensiAdapter absensiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kehadiran);

        this.dataAbsensi = new ArrayList<Absensi>();

        Absensi a = new Absensi("Steven Sihite", "07/07/2023", "08.00 WIB", "Jl. Soehat No. 24", R.drawable.ic_photo);
        Absensi b = new Absensi("Arya Yudha", "07/07/2023", "07.50", "Jl. Kerto Raharjo No. 1", R.drawable.ic_photo2);
        Absensi c = new Absensi("M Bintang Nur", "07/07/2023", "07.59 WIB", "Jl. Watu Gong No. 33", R.drawable.ic_foto3);
        Absensi d = new Absensi("Kevin Yosua", "07/07/2023", "08.20", "Jl. Dinoyo No. 3", R.drawable.ic_photo4);
        Absensi e = new Absensi("Jack Ma", "07/07/2023", "07.00", "Jl. Sumbersari No. 1", R.drawable.ic_photo5);

        this.dataAbsensi.add(a); this.dataAbsensi.add(b);
        this.dataAbsensi.add(c); this.dataAbsensi.add(d);
        this.dataAbsensi.add(e);
        this.rvAbsensi = this.findViewById(R.id.rvAbsensi);

        this.absensiAdapter = new AbsensiAdapter(DataKehadiran.this, this.dataAbsensi);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(DataKehadiran.this, LinearLayoutManager.VERTICAL,false);

        this.rvAbsensi.setLayoutManager(lm);
        this.rvAbsensi.setAdapter(absensiAdapter);

        this.btBack = this.findViewById(R.id.btBack);
        this.btBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        if(view.getId() == R.id.btBack){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
