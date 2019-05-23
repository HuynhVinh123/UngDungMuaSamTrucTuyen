package com.example.huynhvinh.applazada_java.model.Room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.huynhvinh.applazada_java.model.Room.dao.HandleSanPhamDAO;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;

@Database(entities = {SanPham_Room.class},version = 3)
public abstract class SanPham_DatabaseRoom  extends RoomDatabase {

    private static SanPham_DatabaseRoom instance;

    public abstract HandleSanPhamDAO handleSanPhamDAO();

    public static synchronized SanPham_DatabaseRoom getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),SanPham_DatabaseRoom.class,"sanpham_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }

}
