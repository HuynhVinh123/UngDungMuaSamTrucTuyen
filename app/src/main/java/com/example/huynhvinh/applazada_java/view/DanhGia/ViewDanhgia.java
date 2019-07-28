package com.example.huynhvinh.applazada_java.view.DanhGia;

import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhgia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList);
    void HienThiDanhSachDanhGiaCuaNhanVien(List<DanhGia> danhGiaList);
}