package com.example.huynhvinh.applazada_java.Presenter.DangNhap_DangKy;

import android.util.Log;

import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangKyModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.model.QuenMatKhau.QuenMatKhauModel;
import com.example.huynhvinh.applazada_java.view.DangNhap.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {

    ViewDangKy viewDangKy;
    DangKyModel dangKyModel;
    QuenMatKhauModel quenMatKhauModel;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        dangKyModel = new DangKyModel();
        quenMatKhauModel = new QuenMatKhauModel();
    }

    public PresenterLogicDangKy() {
        dangKyModel = new DangKyModel();
        quenMatKhauModel = new QuenMatKhauModel();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        boolean kiemtra = dangKyModel.DangKyThanhVien(nhanVien);
        if(kiemtra)
        {
            viewDangKy.DangKyThanhcong();
        }
        else {
            viewDangKy.DangKyThatBai();
        }
    }

    @Override
    public boolean KiemTraNVTonTai(String id) {
        boolean kiemtra =  dangKyModel.KiemTraThanhVienDaTonTai(id);
        return  kiemtra;
    }


    public boolean DangKyByGoogkeAndFacebook(NhanVien nhanVien){
        boolean kiemtradangky = false;
        boolean kiemtra = dangKyModel.DangKyThanhVien(nhanVien);
        if(kiemtra)
        {
            kiemtradangky = true;
        }else{
            kiemtradangky =false;

        }

        return  kiemtradangky;

    }

    public boolean KiemTraEMail(String email) {
        boolean kiemtra = false;
        int manv = quenMatKhauModel.KiemTraEmail(email);
        if(manv!=0)
        {
            kiemtra = true;
        }

        return  kiemtra;
    }

}
