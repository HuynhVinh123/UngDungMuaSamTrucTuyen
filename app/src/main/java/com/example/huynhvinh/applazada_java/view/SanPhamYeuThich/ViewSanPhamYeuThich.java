package com.example.huynhvinh.applazada_java.view.SanPhamYeuThich;

import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewSanPhamYeuThich {
    void HienThiDanhSachSanPhamYeuThich(List<SanPham> sanPhamList);
    void XoaSanPhamThanhcong();
    void XoaSanPhamThatBai();
}
