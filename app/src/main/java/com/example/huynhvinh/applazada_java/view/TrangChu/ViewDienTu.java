package com.example.huynhvinh.applazada_java.view.TrangChu;

import com.example.huynhvinh.applazada_java.model.ObjectClass.DienTu;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiSachDienTu(List<DienTu> dienTus) ;
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhamList);
}
