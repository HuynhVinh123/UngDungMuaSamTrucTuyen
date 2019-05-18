package com.example.huynhvinh.applazada_java.Presenter.DangNhap_DangKy;

import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;

public interface IPresenterDangKy {
    void ThucHienDangKy(NhanVien nhanVien);
    boolean KiemTraNVTonTai(String id);
}
