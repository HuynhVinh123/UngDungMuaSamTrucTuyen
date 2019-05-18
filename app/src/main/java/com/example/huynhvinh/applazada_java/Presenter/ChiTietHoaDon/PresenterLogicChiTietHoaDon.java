package com.example.huynhvinh.applazada_java.Presenter.ChiTietHoaDon;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.QuanLyHoaDon.QuanLyHoaDonModel;
import com.example.huynhvinh.applazada_java.view.ChiTietHoaDon.ViewChiTietHoaDon;

import java.util.List;

public class PresenterLogicChiTietHoaDon implements IPresenterChiTietHoaDon {

    ViewChiTietHoaDon viewChiTietHoaDon;
    QuanLyHoaDonModel quanLyHoaDonModel;

    public PresenterLogicChiTietHoaDon(ViewChiTietHoaDon viewChiTietHoaDon) {
        this.viewChiTietHoaDon = viewChiTietHoaDon;
        quanLyHoaDonModel = new QuanLyHoaDonModel();
    }

    @Override
    public void LayDanhSachSanPhamCuaHoaDon(String manguoinhan) {

        List<HoaDon> hoaDonList = quanLyHoaDonModel.LayDanhSachHoaDon(manguoinhan);


        if (hoaDonList.size()>0)
        {
            for(int i=0; i< hoaDonList.size();i++)
            {
                List<ChiTietHoaDon> chiTietHoaDonList = quanLyHoaDonModel.LayDanhSachSPChiTietHoaDon(hoaDonList.get(i).getMaHD());
                if (chiTietHoaDonList.size()>0)
                {
                    // Kiểm tra khuyến mãi
                    for(int j=0; j<chiTietHoaDonList.size();j++) {
                        // Kiểm tra hóa đơn có mua trong thời hạn khuyến mãi hay không
                        int phantramkm = quanLyHoaDonModel.KiemTraHoaDonCoTrongThoiGianKM(hoaDonList.get(i).getNgayMua(),chiTietHoaDonList.get(j).getMaSP());
                        if(phantramkm!=0)
                        {
                            ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                            chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                            chiTietHoaDonList.get(j).setChiTietKhuyenMai(chiTietKhuyenMai);
                        }
                    }

                    hoaDonList.get(i).setChiTietHoaDonList(chiTietHoaDonList);

                }
            }
            viewChiTietHoaDon.HienThiDanhSachSanPham(hoaDonList);
        }
    }

    @Override
    public void HuyDonHang(int mahd) {
        boolean kiemtra = quanLyHoaDonModel.HuyDonHang(mahd);
        if(kiemtra)
        {
            viewChiTietHoaDon.HuyHoaDonThanhCong();
        }else {
            viewChiTietHoaDon.HuyHoaDonThatBai();
        }
    }
}