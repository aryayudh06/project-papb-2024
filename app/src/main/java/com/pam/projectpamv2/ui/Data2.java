package com.pam.projectpamv2.ui;

import com.example.recyclecetakdata.R;
import com.example.recyclecetakdata.model.User;

import java.util.ArrayList;

public class Data2 {

    private static Integer [] uid = {
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
    };
    private static String [] personname = {
      "Bintang",
      "Dava",
      "Titan",
      "Gandi",
      "Kamal",
            "daud",
            "ipeh",
            "farrel",
            "juno",
            "samsul",
            "nur",
    };
    private static String [] detail = {
          "Bintang telah absen pada tanggal 15 april 2024",
            "Dava telah absen pada tanggal 15 april 2024",
            "Titan telah absen pada tanggal 15 april 2024",
            "Gandi telah absen pada tanggal 15 april 2024",
            "Kamal telah absen pada tanggal 15 april 2024",
            "daud telah absen pada tanggal 15 april 2024",
            "ipeh telah absen pada tanggal 15 april 2024",
            "farrel telah absen pada tanggal 15 april 2024",
            "juno telah absen pada tanggal 15 april 2024",
            "samsul telah absen pada tanggal 15 april 2024",
            "nur telah absen pada tanggal 15 april 2024",

    };

    private static int[] personimage = {
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
            R.drawable.bintang,
    };

    static ArrayList<User> getListData(){
        ArrayList<User> list=new ArrayList<>();
        for (int position = 0; position < personname.length; position++){
            User data = new User();
            data.setUid(uid[position]);
            data.setName(personname[position]);
            data.setDetail(detail[position]);
            data.setPhoto(personimage[position]);

            list.add(data);

        }
        return list;
    }
}
