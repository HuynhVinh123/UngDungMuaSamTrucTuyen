package com.example.huynhvinh.applazada_java.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia,String convertImage1,String convertImage2,String convertImage3,int uyTin);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
    void LayDanhSachDanhGiaCuaNhanVien(int manv);
    boolean KiemTraThanhVienCoMuaHang(String manv, int masp);
}
