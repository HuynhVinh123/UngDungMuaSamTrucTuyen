package com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan;

import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.model.QuanLyTaiKhoan.QuanLyTaiKhoanModel;
import com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan.ViewThongTinTaiKhoan;

public class PresenterLogicQuanLyTaiKhoan implements IPresenterQuanLyTaiKhoan {

    ViewThongTinTaiKhoan viewThongTinTaiKhoan;
    QuanLyTaiKhoanModel quanLyTaiKhoanModel;

    public PresenterLogicQuanLyTaiKhoan(ViewThongTinTaiKhoan viewThongTinTaiKhoan) {
        this.viewThongTinTaiKhoan = viewThongTinTaiKhoan;
        quanLyTaiKhoanModel = new QuanLyTaiKhoanModel();
    }

    public PresenterLogicQuanLyTaiKhoan() {
        quanLyTaiKhoanModel = new QuanLyTaiKhoanModel();
    }

    @Override
    public void LayThongTinNhanVien(String manv) {

        NhanVien nhanVien = quanLyTaiKhoanModel.LayThongTinNhanVien(manv);
        if (!nhanVien.getTenNV().trim().equals("") || !nhanVien.getTenNV().trim().equals(null))
        {
            viewThongTinTaiKhoan.HienThiThongTinNguoiDung(nhanVien);
        }

    }

    public  NhanVien LayThongTinNhanVienMaNV(String manv){
        NhanVien nhanVien = quanLyTaiKhoanModel.LayThongTinNhanVien(manv);
        return  nhanVien;
    }

    public NhanVien LayThongTinNhanVienID(String id){
        NhanVien nhanVien = quanLyTaiKhoanModel.LayThongTinNhanVienBangId(id);

        return  nhanVien;
    }

    @Override
    public void LayThongTinNhanVienBangIdNV(String id) {
        NhanVien nhanVien = quanLyTaiKhoanModel.LayThongTinNhanVienBangId(id);
        if (!nhanVien.getTenNV().trim().equals("") || !nhanVien.getTenNV().trim().equals(null))
        {
            viewThongTinTaiKhoan.HienThiThongTinNguoiDung(nhanVien);
        }
    }

    @Override
    public void CapNhatThongTinNhanVienBangMaNV(NhanVien nhanVien) {
        boolean kiemtra = quanLyTaiKhoanModel.CapNhatThongTinNhanVienBangMaNV(nhanVien);
        if (kiemtra==true)
        {
            viewThongTinTaiKhoan.CapNhatThanhCong();
        }else {
            viewThongTinTaiKhoan.CapNhatThatBai();
        }
    }

    @Override
    public void CapNhatThongTinNhanVienBangIdNV(NhanVien nhanVien) {
        boolean kiemtra = quanLyTaiKhoanModel.CapNhatThongTinNhanVienBangIdNV(nhanVien);
    }
}
