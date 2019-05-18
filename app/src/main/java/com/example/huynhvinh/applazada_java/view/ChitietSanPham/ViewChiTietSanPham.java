package com.example.huynhvinh.applazada_java.view.ChitietSanPham;

import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSliderSanPham(String[] linkHinhSanPham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();
    void ThemSPYeuThichThanhCong();
    void ThemSPYeuThichThatBai();
}
