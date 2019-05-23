package com.example.huynhvinh.applazada_java.model.Room.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;
import com.example.huynhvinh.applazada_java.model.Room.repository.SanPhamRepository;

import java.util.List;

public class SanPhamViewModel extends AndroidViewModel {

    private SanPhamRepository sanPhamRepository;
    private LiveData<List<SanPham_Room>> listLiveData;

    public SanPhamViewModel(@NonNull Application application) {
        super(application);
        sanPhamRepository = new SanPhamRepository(application);
        listLiveData = sanPhamRepository.layDanhSachSanPham();
    }

    public void themSanPham(SanPham_Room sanPham_room){
        sanPhamRepository.themSanPham(sanPham_room);
    }

    public LiveData<List<SanPham_Room>> layDanhSachSanPham(){
            return listLiveData;
    }

}
