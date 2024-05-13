package com.example.recyclecetakdata.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclecetakdata.R;
import com.example.recyclecetakdata.model.User;
import com.example.recyclecetakdata.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private final ArrayList<User> list = new ArrayList<>();
    private  ListDataAdapter listDataAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {// pada fragment, UI logic biasanya diletakkan di fungsi OnViewCreated
        super.onViewCreated(view, savedInstanceState);
        // semua fungsi dibawah dipindahkan dari MainActivity dengan sedikit adjustment
        MainViewModel mainViewModel = obtainViewModel(); // membuat variable viewModel
        mainViewModel.insert(data2.getListData()); // insert semua data, comment baris ini ketika sudah dijalankan sekali karena data sudah tersimpan ke database. Selanjutnya hanya perlu read data.

        RecyclerView rvdata = view.findViewById(R.id.rv_data);
        rvdata.setHasFixedSize(true);
        rvdata.setLayoutManager(new LinearLayoutManager(requireContext())); // untuk menjalankan fungsi ini, dibutuhkan konteks yang valid sehingga requiredContext() digunakan
        listDataAdapter = new ListDataAdapter(list);
        rvdata.setAdapter(listDataAdapter); // set adapter recyclerview

        // sebelumnya kode dibawah berjalan di dalam activity dimana fungsi observe membutuhkan argumen lifecycle owner
        // oleh karena itu yang sebelumnya input argumen "this" (yang merujuk pada MainActivity) diubah menjadi requireActivity() (karena skrng kode ini bukan di MainActivity lagi melainkan fragment)
        mainViewModel.getAllUsers().observe(requireActivity(), new Observer<List<User>>() { // mengambil semua data dari database
            @Override
            public void onChanged(List<User> user) {
                showData(user);
            }
        });
    }

    //fungsi OnCreateView ini dipanggil saat fragment pertama kali dibuat atau saat tampilannya perlu di-generate untuk ditampilkan ke pengguna.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // fungsi ini dipindahkan dari MainActivity
    private void  showData(List<User> list){ // menampilkan data
        listDataAdapter.listdata.addAll(list); // add data ke variable list yang ada di adapter
        listDataAdapter.notifyDataSetChanged(); // refresh adapter untuk menampilkan data baru
    }

    // fungsi ini dipindahkan dari MainActivity
    private MainViewModel obtainViewModel() { // fungsi untuk membuat instance viewModel
        return new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }
}