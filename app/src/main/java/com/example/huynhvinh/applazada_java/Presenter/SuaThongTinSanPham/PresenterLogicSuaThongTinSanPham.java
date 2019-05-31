package com.example.huynhvinh.applazada_java.Presenter.SuaThongTinSanPham;

import com.example.huynhvinh.applazada_java.model.ChiTietSanPham.ChiTietSanPhamModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.SuaThongTinSanPham.SuaThongTinSanPhamModel;
import com.example.huynhvinh.applazada_java.view.SuaThongTinSanPham.ViewSuaThongTinSanPham;

public class PresenterLogicSuaThongTinSanPham  implements  IPresenterSuaThongTinSanPham{

    ViewSuaThongTinSanPham viewSuaThongTinSanPham;
    ChiTietSanPhamModel chiTietSanPhamModel;
    SuaThongTinSanPhamModel suaThongTinSanPhamModel;

    public PresenterLogicSuaThongTinSanPham(ViewSuaThongTinSanPham viewSuaThongTinSanPham) {
        this.viewSuaThongTinSanPham = viewSuaThongTinSanPham;
        chiTietSanPhamModel = new ChiTietSanPhamModel();
        suaThongTinSanPhamModel = new SuaThongTinSanPhamModel();
    }

    @Override
    public void SuaThongTinSanPham(SanPham sanPham) {
        boolean kiemtra =  suaThongTinSanPhamModel.CapNhatThongTinSanPham(sanPham);
        if (kiemtra)
        {
            viewSuaThongTinSanPham.CapNhatSanPhamThanhCong();
        }else {
            viewSuaThongTinSanPham.CapNhatSanPhamThatBai();
        }
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = chiTietSanPhamModel.LayChiTietSanPham("LaySanPhamVschiTietTheoMaSP","CHITIETSANPHAM",masp);
        if(sanPham!=null)
        {
            viewSuaThongTinSanPham.HienThiSanPham(sanPham);
        }
    }
}
