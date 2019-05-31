package com.example.huynhvinh.applazada_java.Presenter.ThanhToan;

import android.content.Context;

import com.example.huynhvinh.applazada_java.model.GioHang.GioHangModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ThanhToan.ThanhToanModel;
import com.example.huynhvinh.applazada_java.view.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ThanhToanModel thanhToanModel;
    GioHangModel gioHangModel;
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan,Context context) {
        this.viewThanhToan = viewThanhToan;
        thanhToanModel = new ThanhToanModel();
        gioHangModel = new GioHangModel();
        gioHangModel.MoKetNoi(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = thanhToanModel.ThemHoaDon(hoaDon);
        if(kiemtra)
        {
            thanhToanModel.CapNhatSoLuongSanPham(hoaDon);
            viewThanhToan.DatHangThanhCong();
            int dem = sanPhamList.size();
            for(int i=0;i<dem;i++)
            {
                gioHangModel.XoaSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }
        }else{
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        sanPhamList = gioHangModel.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}