package com.example.huynhvinh.applazada_java.model.Room.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.huynhvinh.applazada_java.model.Room.dao.ThongSoKyThuatDao;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;
import com.example.huynhvinh.applazada_java.model.Room.repository.ThongSoKyThuatRespository;

import java.util.List;

public class ThongSoKyThuatViewModel extends AndroidViewModel {

    private ThongSoKyThuatRespository thongSoKyThuatRespository;

    LiveData<List<ThongSoKyThuat>> thongSoKyThuatList;

    public ThongSoKyThuatViewModel(@NonNull Application application) {
        super(application);
        thongSoKyThuatRespository  = new ThongSoKyThuatRespository(application);
        thongSoKyThuatList = thongSoKyThuatRespository.layDanhSachThongSoKyThuat();
    }

    public void xoaThongSo(){
        thongSoKyThuatRespository.xoaDanhSachThongSo();
    }

    public  void themThongSo(ThongSoKyThuat thongSoKyThuat){
        thongSoKyThuatRespository.themThongSo(thongSoKyThuat);
    }

    public LiveData<List<ThongSoKyThuat>> layDanhSachThongSo(){
        return  thongSoKyThuatList;
    }
}
