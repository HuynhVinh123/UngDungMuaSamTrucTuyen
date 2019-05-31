package com.example.huynhvinh.applazada_java.view.SanPhamDaBan;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewSanPhamDaBan {
    void HienThiDanhSachSanPham(List<SanPham> sanPhamList);
    void XoaSanPhamThanhCong();
    void XoaSanPhamThatBai();
}
