package com.example.huynhvinh.applazada_java.model.Room.object;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "thongsokythuat")
public class ThongSoKyThuat {
    @PrimaryKey(autoGenerate = true)
    int mathongso;
    String tenchitiet,giatri;

    public int getMathongso() {
        return mathongso;
    }

    public void setMathongso(int mathongso) {
        this.mathongso = mathongso;
    }

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
