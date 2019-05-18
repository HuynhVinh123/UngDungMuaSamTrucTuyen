package com.example.huynhvinh.applazada_java.Presenter.KhuyenMai;

import android.util.Log;

import com.example.huynhvinh.applazada_java.model.KhuyenMai.KhuyenMaiModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.KhuyenMai;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements  IPresenterKhuyenMai {
    ViewKhuyenMai viewKhuyenMai;
    KhuyenMaiModel khuyenMaiModel;
    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        khuyenMaiModel = new KhuyenMaiModel();
    }

    public PresenterLogicKhuyenMai() {
        khuyenMaiModel = new KhuyenMaiModel();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = khuyenMaiModel.LayDanhSachSanPhamKhuyenMai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");
        if(khuyenMaiList.size()>0)
        {
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }

    public int KiemTraKhuyenMai(int masp){
        int phamtramkhuyenmai = khuyenMaiModel.KiemTraKhuyenMai(masp);
        return  phamtramkhuyenmai;
    }

}
