package com.pam.projectpamv2.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.recyclecetakdata.R;

public class CetakData extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak_data);

        FragmentManager fragmentManager = getSupportFragmentManager(); // jawaban no 3
        HomeFragment homeFragment = new HomeFragment(); // membuat instance HomeFragment
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) { // cek jika instance yang dibuat sudah benar
            fragmentManager.beginTransaction() // memulai proses menampilkan fragment (istilahnya memulai transaksi)
                    .add(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName()) // add container ke fragment menggunakan id pada FrameLayout
                    .commit(); // untuk menyelesaikan transaksi yang dimulai sebelumnya
        }
    }
}
/*
1. Bagaimana Activity tersebut diimplementasikan menjadi Fragment
- untuk mengimplementasikan fragment pada activity, mula mula buat Blank Fragment baru.
- Pindahkan semua kode xml pada activity_main.xml ke fragment_home.xml
- Buat sebuah Fragment Container pada activity_main.xml. Pada kode ini, digunakan FrameLayout
- Pindahkan UI logic pada MainActivity ke HomeFrament
_ Pada MainActivity, buat instance HomeFragment dan tampilkan pada Activity dengan beginTransaction()
2. Perubahan apa yang dilakukan dari kode program Activity sebelumnya dan jelaskan mengapa perubahan tersebut perlu dilakukan.
- Pada kode xml nya, hanya terdapat 1 view yaitu FrameLayout. View ini berfungsi sebagai container yang akan menampilkan fragment.
- Pada file .java, tidak ada UI logic mengambil data dari database melainkan terdapat kode untuk membuat fragment dan menampilkannya.
3. Bagian mana dari kode program di dalam Activity yang menjadi container Fragment tersebut dimuat dapat ditampilkan dan dijalankan dengan baik.
Pada .xml file yaitu view FrameLayout
4. Bagian mana dari kode program di dalam Activity yang bertanggung jawab untuk mengelola Fragment mana yang ditampilkan atau diubah.
Bagian dari kode program di dalam Activity yang bertanggung jawab untuk mengelola Fragment mana yang ditampilkan atau diubah adalah FragmentManager.
FragmentManager digunakan untuk menambah, menghapus, atau mengganti Fragment yang ditampilkan dalam container Fragment.

NOTE: Tampilan akhir aplikasi memang sama dengan yg sebelumnya tetapi recyclerview tersebut berjalan di dalam fragment
dan fragment tersebut berjalan di dalam MainActivity.
Berhubung container fragmentnya di atur match parent, maka tidak terlihat perbedaannya setelah implementasi fragment.

*/



