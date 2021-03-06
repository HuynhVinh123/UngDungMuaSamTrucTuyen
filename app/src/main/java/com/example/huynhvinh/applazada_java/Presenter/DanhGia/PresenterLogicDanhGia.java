package com.example.huynhvinh.applazada_java.Presenter.DanhGia;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.huynhvinh.applazada_java.model.DanhGia.DanhGiaModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.view.DanhGia.ViewDanhgia;

import java.util.List;

public class PresenterLogicDanhGia implements  IPresenterDanhGia {

    ViewDanhgia viewDanhgia;
    DanhGiaModel danhGiaModel;

    public PresenterLogicDanhGia(ViewDanhgia viewDanhgia) {
        this.viewDanhgia = viewDanhgia;
        danhGiaModel = new DanhGiaModel();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia,String convertImage1,String convertImage2,String convertImage3,int uyTin) {
        boolean kiemtra = danhGiaModel.ThemDanhGia(danhGia,convertImage1,convertImage2,convertImage3,uyTin);
        if(kiemtra){
            viewDanhgia.DanhGiaThanhCong();
        }
        else
        {
            viewDanhgia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
       // progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = danhGiaModel.LayDanhSachDanhGiaCuaSanPham(masp,limit);

        if(danhGiaList.size()>0)
        {
            progressBar.setVisibility(View.GONE);
            viewDanhgia.HienThiDanhSachDanhGiaTheoSP(danhGiaList);
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaNhanVien(int manv) {
        List<DanhGia> danhGiaList = danhGiaModel.LayDanhSachDanhGiaCuaNhanVien(manv);
        if(danhGiaList.size()>0)
        {
            viewDanhgia.HienThiDanhSachDanhGiaCuaNhanVien(danhGiaList);
        }
    }

    @Override
    public boolean KiemTraThanhVienCoMuaHang(String manv, int masp) {
        boolean kiemtra = danhGiaModel.KiemTraSanPhamDaDuocMua(manv,masp);
        return  kiemtra;
    }
}
