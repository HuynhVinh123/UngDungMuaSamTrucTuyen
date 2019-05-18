package com.example.huynhvinh.applazada_java.view.ThanhToan;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
