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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mainViewModel = obtainViewModel(); 
        mainViewModel.insert(data2.getListData()); 

        RecyclerView rvdata = view.findViewById(R.id.rv_data);
        rvdata.setHasFixedSize(true);
        rvdata.setLayoutManager(new LinearLayoutManager(requireContext())); 
        listDataAdapter = new ListDataAdapter(list);
        rvdata.setAdapter(listDataAdapter); 

        
        mainViewModel.getAllUsers().observe(requireActivity(), new Observer<List<User>>() { 
            @Override
            public void onChanged(List<User> user) {
                showData(user);
            }
        });
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    
    private void  showData(List<User> list){ 
        listDataAdapter.listdata.addAll(list);
        listDataAdapter.notifyDataSetChanged(); 
    }

    
    private MainViewModel obtainViewModel() {
        return new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }
}
