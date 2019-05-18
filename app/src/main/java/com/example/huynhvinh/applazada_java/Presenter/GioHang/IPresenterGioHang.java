package com.example.huynhvinh.applazada_java.Presenter.GioHang;

import android.content.Context;

import com.example.huynhvinh.applazada_java.model.ObjectClass.CodeKhuyenMai;

public interface IPresenterGioHang {

    void LayDanhSachSanPhamTrongGioHang(Context context);
    void CapNhatTongTienGioHang(Context context);
    CodeKhuyenMai KiemTraMaCodeKhuyenMai(String macode);
}
