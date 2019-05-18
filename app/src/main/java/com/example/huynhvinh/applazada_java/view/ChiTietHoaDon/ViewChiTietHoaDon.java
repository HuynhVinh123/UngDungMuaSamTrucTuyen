package com.example.huynhvinh.applazada_java.view.ChiTietHoaDon;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

import java.util.List;

public interface ViewChiTietHoaDon {
    void HienThiDanhSachSanPham(List<HoaDon> hoaDonList);
    void HuyHoaDonThanhCong();
    void HuyHoaDonThatBai();
}
