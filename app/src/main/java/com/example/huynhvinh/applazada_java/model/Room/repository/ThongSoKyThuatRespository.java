package com.example.huynhvinh.applazada_java.model.Room.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.huynhvinh.applazada_java.model.Room.dao.HandleSanPhamDAO;
import com.example.huynhvinh.applazada_java.model.Room.dao.ThongSoKyThuatDao;
import com.example.huynhvinh.applazada_java.model.Room.database.ThongSoKyThuat_Database;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;

import java.util.List;

public class ThongSoKyThuatRespository {

    ThongSoKyThuatDao thongSoKyThuatDao;
    private LiveData<List<ThongSoKyThuat>> ThongSoKyThuats;

    public ThongSoKyThuatRespository(Application application) {
        ThongSoKyThuat_Database thongSoKyThuat_database = ThongSoKyThuat_Database.getInstance(application);
        thongSoKyThuatDao = thongSoKyThuat_database.thongSoKyThuatDao();
        ThongSoKyThuats = thongSoKyThuatDao.layDanhSachThongSoKyThuat();
    }

    public void xoaDanhSachThongSo(){
        new xoaDanhSachThongSoKyThuat(thongSoKyThuatDao).execute();
    }

    public void themThongSo(ThongSoKyThuat thongSoKyThuat){
        new themThongSoKyThuat(thongSoKyThuatDao).execute(thongSoKyThuat);
    }

    public  LiveData<List<ThongSoKyThuat>> layDanhSachThongSoKyThuat(){
        return  ThongSoKyThuats;
    }

    private  static class themThongSoKyThuat extends AsyncTask<ThongSoKyThuat, Void, Void> {

        private ThongSoKyThuatDao thongSoKyThuatDao;

        public themThongSoKyThuat(ThongSoKyThuatDao thongSoKyThuatDao) {
            this.thongSoKyThuatDao = thongSoKyThuatDao;
        }

        @Override
        protected Void doInBackground(ThongSoKyThuat... thongSoKyThuats) {
            thongSoKyThuatDao.ThemThongSo(thongSoKyThuats[0]);
            return null;
        }
    }

    private  static class xoaDanhSachThongSoKyThuat extends AsyncTask<ThongSoKyThuat, Void, Void> {

        private  ThongSoKyThuatDao  thongSoKyThuatDao;


        public xoaDanhSachThongSoKyThuat(ThongSoKyThuatDao thongSoKyThuatDao) {
            this.thongSoKyThuatDao = thongSoKyThuatDao;
        }

        @Override
        protected Void doInBackground(ThongSoKyThuat... thongSoKyThuats) {
            thongSoKyThuatDao.xoaDanhSachThongSo();
            return null;
        }
    }

}
