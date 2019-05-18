package com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.QuanLyHoaDon.QuanLyHoaDonModel;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.ViewDonHang;

import java.util.List;

public class PresenterLogicQuanLyTrangThaiHoaDon implements IPresenterQuanLyTrangThaiHoaDon {

    ViewDonHang viewDonHang;
    QuanLyHoaDonModel quanLyHoaDonModel;

    public PresenterLogicQuanLyTrangThaiHoaDon(ViewDonHang viewDonHang) {
        this.viewDonHang  = viewDonHang;
        quanLyHoaDonModel = new QuanLyHoaDonModel();
    }

    @Override
    public void LayDanhSachHoaDonTheoTrangThai(String trangthai,String manguoinhan) {
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
            viewDonHang.HienThiDanhSachDonHang(hoaDonList);
        }else {
            //viewDonHang.LayDonHangThatBai();
        }
    }



}