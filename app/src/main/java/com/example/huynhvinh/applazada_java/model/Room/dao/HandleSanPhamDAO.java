package com.example.huynhvinh.applazada_java.model.Room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;

import java.util.List;

@Dao
public interface HandleSanPhamDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void themSanPham(SanPham_Room sanPham_room);

    @Query("DELETE FROM sanpham_table")
    void deleteAllNotes();

    @Query("SELECT * FROM sanpham_table")
    LiveData<List<SanPham_Room>> getAllProducts();

}
