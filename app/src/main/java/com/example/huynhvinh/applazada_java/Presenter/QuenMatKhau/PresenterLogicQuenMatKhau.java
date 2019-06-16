package com.example.huynhvinh.applazada_java.Presenter.QuenMatKhau;

import android.util.Log;

import com.example.huynhvinh.applazada_java.model.QuenMatKhau.QuenMatKhauModel;
import com.example.huynhvinh.applazada_java.view.QuenMatKhau.ViewQuenMatKhau;

public class PresenterLogicQuenMatKhau implements  IPresenterQuenMatKhau {

    QuenMatKhauModel quenMatKhauModel;
    ViewQuenMatKhau viewQuenMatKhau;

    public PresenterLogicQuenMatKhau(ViewQuenMatKhau viewQuenMatKhau) {
        this.viewQuenMatKhau = viewQuenMatKhau;
        quenMatKhauModel = new QuenMatKhauModel();
    }

    public PresenterLogicQuenMatKhau() {
        quenMatKhauModel = new QuenMatKhauModel();
    }

    @Override
    public void KiemTraEMail(String email) {
        int manv = quenMatKhauModel.KiemTraEmail(email);
        if(manv!=0) {
            viewQuenMatKhau.EmailHopLe(manv);
        }else {
            viewQuenMatKhau.EmailKhongHopLe();
        }
    }

    public  boolean CapNhatMatKhau(String matkhau,int manv){
        boolean kiemtra =  quenMatKhauModel.CapNhatMatKhau(matkhau,manv);
        return  kiemtra;
    }
}
