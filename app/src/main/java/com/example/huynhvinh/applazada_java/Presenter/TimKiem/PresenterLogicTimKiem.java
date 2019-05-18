package com.example.huynhvinh.applazada_java.Presenter.TimKiem;

import android.util.Log;

import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.TimKiem.TimKiemModel;
import com.example.huynhvinh.applazada_java.view.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem  implements  IPresenterTimKiem {

    ViewTimKiem viewTimKiem;
    TimKiemModel timKiemModel;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        timKiemModel = new TimKiemModel();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    @Override
    public void TimKiemSanPhamTheoTenSanPham(String tensp, int limit) {
        List<SanPham> sanPhamList = timKiemModel.LayDanhSachSanPhamTheoMaLoai(tensp,"DANHSACCHSANPHAM","TimKiemSanPhamTheoTenSanPham",limit);
        if(sanPhamList.size()>0)
        {
            if(sanPhamList.size()>0) {
                for (int i = 0; i < sanPhamList.size(); i++) {
                    int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(sanPhamList.get(i).getMASP());
                    if(phantramkm!=0)
                    {
                        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                        chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                        sanPhamList.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                    }
                }
            }
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }
        else {
            viewTimKiem.TimKiemThatBai();
        }
    }
}