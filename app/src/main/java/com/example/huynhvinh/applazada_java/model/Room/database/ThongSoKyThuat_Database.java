package com.example.huynhvinh.applazada_java.model.Room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.huynhvinh.applazada_java.model.Room.dao.ThongSoKyThuatDao;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;


@Database(entities = {ThongSoKyThuat.class},version = 1)
public abstract class ThongSoKyThuat_Database  extends RoomDatabase {

    public static ThongSoKyThuat_Database instance;

    public  abstract ThongSoKyThuatDao thongSoKyThuatDao();


    public static synchronized ThongSoKyThuat_Database getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),ThongSoKyThuat_Database.class,"thongsokythuat")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }


}
