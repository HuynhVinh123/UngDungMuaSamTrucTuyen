package com.example.huynhvinh.applazada_java.Presenter.ThemSanPham;

import android.view.View;

import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.model.ThemSanPham.ThemSanPhamModel;
import com.example.huynhvinh.applazada_java.model.TrangChu_DienTu.DienTuModel;
import com.example.huynhvinh.applazada_java.view.ThemSanPham.ViewDanhSachLoaiSanPham;

import java.util.List;

public class PrensenterLogicThemSanPham implements  IPresenterThemSanPham{

    ViewDanhSachLoaiSanPham viewDanhSachLoaiSanPham;
    ThemSanPhamModel themSanPhamModel;
    DienTuModel dienTuModel;

    public PrensenterLogicThemSanPham(ViewDanhSachLoaiSanPham viewDanhSachLoaiSanPham) {
        this.viewDanhSachLoaiSanPham = viewDanhSachLoaiSanPham;
        themSanPhamModel = new ThemSanPhamModel();
        dienTuModel = new DienTuModel();
    }

    public PrensenterLogicThemSanPham() {
        themSanPhamModel = new ThemSanPhamModel();
        dienTuModel = new DienTuModel();
    }

    @Override
    public void LayDanhSachLoaiSanPham() {

        List<LoaiSanPham> loaiSanPhamList = themSanPhamModel.LayDanhSachLoaiSanPham();
        if(loaiSanPhamList.size() >0)
        {
            viewDanhSachLoaiSanPham.HienThiDanhSachLoaiSanPham(loaiSanPhamList);
        }

    }


    public List<ThuongHieu> LayDanhSachThuongHieu() {
        List<ThuongHieu> thuongHieuList =  dienTuModel.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        return  thuongHieuList;
    }

    public boolean ThemSanPham(String convertImage1, String convertImage2, String convertImage3, SanPham sanPham){
        boolean kiemtra = themSanPhamModel.ThemSanPham(convertImage1,convertImage2,convertImage3,sanPham);
        return kiemtra;
    }



}
