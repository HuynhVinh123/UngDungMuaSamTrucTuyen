package com.example.huynhvinh.applazada_java.Presenter.SanPhamDaMua;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.QuanLyHoaDon.QuanLyHoaDonModel;
import com.example.huynhvinh.applazada_java.view.SanPhamDaMua.ViewSanPhamDaMua;

import java.util.List;

public class PresenterLogicSanPhamDaMua implements IPresenterSanPhamDaMua {

    ViewSanPhamDaMua viewSanPhamDaMua;
    QuanLyHoaDonModel quanLyHoaDonModel;

    public PresenterLogicSanPhamDaMua(ViewSanPhamDaMua viewSanPhamDaMua) {
        this.viewSanPhamDaMua = viewSanPhamDaMua;
        quanLyHoaDonModel = new QuanLyHoaDonModel();
    }


    @Override
    public void LayDanhSachSanPhamDaMua(String trangthai,String manguoinhan) {
        List<HoaDon> hoaDonList = quanLyHoaDonModel.LayDanhSachHoaDonTheoTrangThai(trangthai,manguoinhan);
        if(hoaDonList.size()>0)
        {
            for(int i=0; i< hoaDonList.size();i++)
            {
                List<ChiTietHoaDon> chiTietHoaDonList = quanLyHoaDonModel.LayDanhSachSPChiTietHoaDon(hoaDonList.get(i).getMaHD());
                if (chiTietHoaDonList.size()>0)
                {
                    hoaDonList.get(i).setChiTietHoaDonList(chiTietHoaDonList);
                }
            }
            viewSanPhamDaMua.HienThiDanhSachSanPhamDaMua(hoaDonList);
        }else {
            //viewDonHang.LayDonHangThatBai();
        }
    }
}
