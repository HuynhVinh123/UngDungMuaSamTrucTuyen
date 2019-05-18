package com.example.huynhvinh.applazada_java.Presenter.GioHang;

import android.content.Context;

import com.example.huynhvinh.applazada_java.model.GioHang.SanPhamYeuThichModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.SanPhamYeuThich.ViewSanPhamYeuThich;

import java.util.List;

public class PresenterLogicSanPhamYeuThich implements IPresenterYeuThich {

    SanPhamYeuThichModel sanPhamYeuThichModel;
    ViewSanPhamYeuThich viewSanPhamYeuThich;

    public PresenterLogicSanPhamYeuThich(ViewSanPhamYeuThich viewSanPhamYeuThich)
    {
        sanPhamYeuThichModel = new SanPhamYeuThichModel();
        this.viewSanPhamYeuThich = viewSanPhamYeuThich;
    }

    @Override
    public void LayDanhSachSanPhamYeuThich(Context context) {

        sanPhamYeuThichModel.MoKetNoi(context);
        List<SanPham> sanPhamList =  sanPhamYeuThichModel.LayDanhSachSanPhamYeuThich();
        viewSanPhamYeuThich.HienThiDanhSachSanPhamYeuThich(sanPhamList);

    }

    @Override
    public void XoaSanPhamYeuThichTheoMaSP(int masp,Context context) {
        sanPhamYeuThichModel.MoKetNoi(context);
        boolean kiemtra  =  sanPhamYeuThichModel.XoaSanPhamTheoMaSanPham(masp);
        if(kiemtra)
        {
            viewSanPhamYeuThich.XoaSanPhamThanhcong();
        }
        else{
            viewSanPhamYeuThich.XoaSanPhamThatBai();
        }
    }
}
