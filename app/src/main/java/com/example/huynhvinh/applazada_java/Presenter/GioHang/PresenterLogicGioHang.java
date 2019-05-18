package com.example.huynhvinh.applazada_java.Presenter.GioHang;

import android.content.Context;
import android.view.View;

import com.example.huynhvinh.applazada_java.model.GioHang.GioHangModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.CodeKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {

    GioHangModel gioHangModel;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        gioHangModel = new GioHangModel();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {

        gioHangModel.MoKetNoi(context);
        List<SanPham> sanPhamList = gioHangModel.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size()>0)
        {
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }

    }

    @Override
    public void CapNhatTongTienGioHang(Context context) {
        gioHangModel.MoKetNoi(context);
        List<SanPham> sanPhamList = gioHangModel.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size()>0)
        {
            viewGioHang.CapNhatTongTienSanPhamTrongGioHang(sanPhamList);
        }
        else{
            viewGioHang.KhongCoSanPham();
        }
    }

    @Override
    public CodeKhuyenMai KiemTraMaCodeKhuyenMai(String macode) {

        CodeKhuyenMai codeKhuyenMai = gioHangModel.KiemTraMaCodeKhuyenMai(macode);

        return codeKhuyenMai;
    }


}
