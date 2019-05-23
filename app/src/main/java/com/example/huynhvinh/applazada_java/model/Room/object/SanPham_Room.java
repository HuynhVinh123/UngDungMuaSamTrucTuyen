package com.example.huynhvinh.applazada_java.model.Room.object;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sanpham_table")
public class SanPham_Room {


    @PrimaryKey
    private  int masp;

    private String tensp;

    private  String image;

    private  int gia;

    private  int giakm;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getGiakm() {
        return giakm;
    }

    public void setGiakm(int giakm) {
        this.giakm = giakm;
    }
}