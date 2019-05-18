package com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaCuaSanPham(int masp,int limit);
    void ThemGioHang(SanPham sanPham, Context context,int phantramkhuyenmai);
    void ThemSPYeuThich(SanPham sanPham,Context context,int phantramkm);
}
