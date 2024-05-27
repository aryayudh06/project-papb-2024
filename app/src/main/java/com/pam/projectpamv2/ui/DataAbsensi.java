package com.pam.projectpamv2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.projectpamv2.R;

public class DataAbsensi extends AppCompatActivity {

    private Button btBack;

    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_absensi);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("absensi");

    }
}