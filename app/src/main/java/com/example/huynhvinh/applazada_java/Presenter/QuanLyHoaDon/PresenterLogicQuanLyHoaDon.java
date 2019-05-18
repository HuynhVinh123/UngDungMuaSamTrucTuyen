package com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon;

import android.util.Log;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.QuanLyHoaDon.QuanLyHoaDonModel;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.ViewQuanLyHoaDon;
import com.facebook.login.LoginManager;

import java.util.List;

public class PresenterLogicQuanLyHoaDon implements  IPresenterQuanLyHoaDon {

    ViewQuanLyHoaDon viewQuanLyHoaDon;
    QuanLyHoaDonModel quanLyHoaDonModel;

    public PresenterLogicQuanLyHoaDon(ViewQuanLyHoaDon viewQuanLyHoaDon) {
        this.viewQuanLyHoaDon = viewQuanLyHoaDon;
        quanLyHoaDonModel = new QuanLyHoaDonModel();
    }

    public PresenterLogicQuanLyHoaDon() {
        quanLyHoaDonModel = new QuanLyHoaDonModel();
    }

    @Override
    public void LayDanhSachHoaDon(String manguoinhan) {
        List<HoaDon> hoaDonList = quanLyHoaDonModel.LayDanhSachHoaDon(manguoinhan);
        if (hoaDonList.size()>0)
        {
            for(int i=0; i< hoaDonList.size();i++)
            {
                List<ChiTietHoaDon> chiTietHoaDonList = quanLyHoaDonModel.LayDanhSachSPChiTietHoaDon(hoaDonList.get(i).getMaHD());
                if (chiTietHoaDonList.size()>0)
                {
                    hoaDonList.get(i).setChiTietHoaDonList(chiTietHoaDonList);
                }
            }
            viewQuanLyHoaDon.HienThiDanhSachHoaDon(hoaDonList);
        }
    }

    public List<HoaDon> DanhSachHoaDon(String manguoinhan){
        List<HoaDon> hoaDonList = quanLyHoaDonModel.LayDanhSachHoaDon(manguoinhan);
        if (hoaDonList.size()>0)
        {
            for(int i=0; i< hoaDonList.size();i++)
            {
                List<ChiTietHoaDon> chiTietHoaDonList = quanLyHoaDonModel.LayDanhSachSPChiTietHoaDon(hoaDonList.get(i).getMaHD());
                if (chiTietHoaDonList.size()>0)
                {
                    hoaDonList.get(i).setChiTietHoaDonList(chiTietHoaDonList);
                }
            }
        }
        return  hoaDonList;
    }
}