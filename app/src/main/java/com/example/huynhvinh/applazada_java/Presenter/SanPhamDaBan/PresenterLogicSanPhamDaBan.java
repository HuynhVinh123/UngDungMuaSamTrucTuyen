package com.example.huynhvinh.applazada_java.Presenter.SanPhamDaBan;

import android.util.Log;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.SanPhamDaBan.SanPhamDaBanModel;
import com.example.huynhvinh.applazada_java.view.SanPhamDaBan.ViewSanPhamDaBan;

import java.util.List;

public class PresenterLogicSanPhamDaBan implements IPresenterSanPhamDaBan {

    ViewSanPhamDaBan viewSanPhamDaBan;
    SanPhamDaBanModel sanPhamDaBanModel;

    public PresenterLogicSanPhamDaBan(ViewSanPhamDaBan viewSanPhamDaBan) {
        this.viewSanPhamDaBan = viewSanPhamDaBan;
        sanPhamDaBanModel = new SanPhamDaBanModel();
    }

    @Override
    public void LayDanhSachSanPham(int manv) {
        List<SanPham> sanPhamList = sanPhamDaBanModel.LayDanhSachTatSanPhamTheoMaLoai(manv);
        if(sanPhamList.size()>0)
        {
            viewSanPhamDaBan.HienThiDanhSachSanPham(sanPhamList);
        }
    }

    @Override
    public void XoaSanPham(int masp) {
        boolean kiemtra = sanPhamDaBanModel.XoaSanPham(masp);
        if (kiemtra)
        {
            viewSanPhamDaBan.XoaSanPhamThanhCong();
        }
        else {
            viewSanPhamDaBan.XoaSanPhamThatBai();
        }
    }
}
