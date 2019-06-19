package com.example.huynhvinh.applazada_java.Presenter.ThanhToan;

import android.content.Context;

import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

public interface IPresenterThanhToan {
    void ThemHoaDon(HoaDon hoaDon,int manv, int uytin);
    void LayDanhSachSanPhamTrongGioHang(Context context);
}
