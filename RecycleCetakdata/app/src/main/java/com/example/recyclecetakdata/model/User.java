package com.example.recyclecetakdata.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private Integer uid; // perlu variable yang unique untuk jadi primary key
    private String name;
    private String detail;
    private Integer photo;

    public Integer getUid() {return uid;}

    public void setUid(Integer uid) {this.uid = uid;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }
}
