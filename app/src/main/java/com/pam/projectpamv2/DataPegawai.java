package ap.mobile.projeksimpeg2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DataPegawai extends AppCompatActivity implements View.OnClickListener {

    private Button btBack2;
    private List<Pegawai> dataPegawai;
    private RecyclerView rvPegawai;
    private PegawaiAdapter pegawaiAdapter;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pegawai);

        this.dataPegawai = new ArrayList<Pegawai>();
        this.rvPegawai = this.findViewById(R.id.rvPegawai);
        this.pegawaiAdapter = new PegawaiAdapter(DataPegawai.this, this.dataPegawai);

        Pegawai a = new Pegawai("Steven Hot Asi Sihite", "Laki-Laki", "CEO", "12345678", R.drawable.ic_photo);
        Pegawai b = new Pegawai("Arya Yudha", "Laki-Laki", "HRD", "90123456", R.drawable.ic_photo2);
        Pegawai c = new Pegawai("Jack Ma", "Laki-Laki", "Senior Manager", "44135790", R.drawable.ic_photo5);
        Pegawai d = new Pegawai("M Bintang Nur", "Laki-Laki", "Karyawan", "87650932", R.drawable.ic_photo4);
        Pegawai e = new Pegawai("Kevin Yoshua", "Laki-Laki", "Karyawan", "53197024", R.drawable.ic_foto3);

        this.dataPegawai.add(a);this.dataPegawai.add(b);
        this.dataPegawai.add(c);this.dataPegawai.add(d);
        this.dataPegawai.add(e);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(DataPegawai.this, LinearLayoutManager.VERTICAL,false);

        this.rvPegawai.setLayoutManager(lm);
        this.rvPegawai.setAdapter(pegawaiAdapter);

        this.btBack2 = this.findViewById(R.id.btBack2);
        this.btBack2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btBack2){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
