package com.example.huynhvinh.applazada_java.model.ObjectClass;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

public class ThongSo {

    String tenchitiet,giatri;


    public String getTenchitiet() {
        return tenchitiet;
    }

    public void setTenchitiet(String tenchitiet) {
        this.tenchitiet = tenchitiet;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }
}
