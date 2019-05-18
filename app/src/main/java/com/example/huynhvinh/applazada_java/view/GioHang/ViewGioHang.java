package com.example.huynhvinh.applazada_java.view.GioHang;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewGioHang {
    void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
    void CapNhatTongTienSanPhamTrongGioHang(List<SanPham> sanPhamList);
    void KhongCoSanPham();
}
