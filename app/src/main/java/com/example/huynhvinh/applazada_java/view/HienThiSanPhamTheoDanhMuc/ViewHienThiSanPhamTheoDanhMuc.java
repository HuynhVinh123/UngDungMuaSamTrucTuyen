package com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewHienThiSanPhamTheoDanhMuc {
    void HienThiDanhSachSanPham(List<SanPham> sanPhamList);
    void LoiHienThiDanhSachSanPham();
    void HienThiDanhSachTatCaCacSanPhamMoi(List<SanPham> sanPhamList);
}
