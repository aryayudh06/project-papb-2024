package ap.mobile.projeksimpeg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btDataKehadiran;
    private Button btDataPegawai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btDataKehadiran = this.findViewById(R.id.btDataKehadiran);
        this.btDataKehadiran.setOnClickListener(this);

        this.btDataPegawai = this.findViewById(R.id.btDataPegawai);
        this.btDataPegawai.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DataKehadiranFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DataPegawaiFragment())
                .commit();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btDataKehadiran){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DataKehadiranFragment())
                    .commit();
        } else if (view.getId() == R.id.btDataPegawai) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DataPegawaiFragment())
                    .commit();
        }
    }

}