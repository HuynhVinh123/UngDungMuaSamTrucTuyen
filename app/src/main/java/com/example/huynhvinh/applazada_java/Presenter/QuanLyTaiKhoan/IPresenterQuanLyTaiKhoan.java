package com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan;

import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;

public interface IPresenterQuanLyTaiKhoan {
    void LayThongTinNhanVien(String manv);
    void LayThongTinNhanVienBangIdNV(String id);
    void CapNhatThongTinNhanVienBangMaNV(NhanVien nhanVien);
    void CapNhatThongTinNhanVienBangIdNV(NhanVien nhanVien);
}
