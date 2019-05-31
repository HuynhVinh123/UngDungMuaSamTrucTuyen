package com.example.huynhvinh.applazada_java.model.Room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;

import java.util.List;

@Dao
public interface ThongSoKyThuatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  ThemThongSo(ThongSoKyThuat thongSoKyThuat);

    @Query("SELECT * FROM thongsokythuat")
    LiveData<List<ThongSoKyThuat>> layDanhSachThongSoKyThuat();

    @Query("DELETE FROM thongsokythuat")
    void xoaDanhSachThongSo();

}
