package com.example.huynhvinh.applazada_java.Presenter.TrangChu_DienTu;

import android.util.Log;

import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.model.NhaCuaVaDoiSong.NhaCuaVaDoiSongModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DienTu;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.model.TrangChu_DienTu.DienTuModel;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {

    ViewDienTu viewDienTu;
    DienTuModel dienTuModel;
    NhaCuaVaDoiSongModel nhaCuaVaDoiSongModel;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    public PresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        dienTuModel = new DienTuModel();
        nhaCuaVaDoiSongModel = new NhaCuaVaDoiSongModel();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    @Override
    public void LayDanhSachDienTu() {

        List<DienTu> dienTuList = new ArrayList<>();

        // Danh  sách sản phẩm
        List<ThuongHieu> thuongHieuList =  dienTuModel.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = dienTuModel.LayDanhSachSanPhamTOP("LayDanhSachTopDienThoaiVaMayTinhBang","DANHSACHTOPSANPHAMVAMAYTINHBANG");
        // Kiểm tra khuyến mãi
        if(sanPhamList.size()>0) {
            for (int i = 0; i < sanPhamList.size(); i++) {

                int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(sanPhamList.get(i).getMASP());
                if(phantramkm != 0)
                {
                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                    sanPhamList.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                }
            }
        }

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setThuonghieu(true);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại & Máy tính bảng");
        dienTuList.add(dienTu);


        // Danh sách phụ kiện
        List<SanPham> phukienList = dienTuModel.LayDanhSachSanPhamTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");
        List<ThuongHieu> topphukienList = dienTuModel.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");

        // Kiểm tra khuyến mãi
        if(phukienList.size()>0) {
            for (int i = 0; i < phukienList.size(); i++) {
                int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(phukienList.get(i).getMASP());
                if(phantramkm!=0)
                {
                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                    phukienList.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                }
            }
        }

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topphukienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setThuonghieu(false);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top phụ kiện");
        dienTuList.add(dienTu1);

        if(thuongHieuList.size()>0)
        {
            viewDienTu.HienThiSachDienTu(dienTuList);
        }



    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = dienTuModel.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHLOGOTHUONGHIEU");
        if(thuongHieuList.size()>0)
        {
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }
        else
        {
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
       // List<SanPham> sanPhamList = dienTuModel.LayDanhSachSanPhamTOP("LayDanhSachSanPhamMoiVe","DANHSACHSANPHAMMOIVE");
        List<SanPham> listSPMoiVe = new ArrayList<>();
        List<SanPham> sanPhamList = nhaCuaVaDoiSongModel.LayDanhSachSanPhamTheoMaLoaiSP("LayDanhSachSanPhamThongQuaLoaiSanPhamNhaCuaVaDoiSong","DANHSACHSANPHAMNHACUAVADOISONG",2);

        if(sanPhamList.size()>0)
        {


            for(int i=0; i<10 ; i++)
            {
                listSPMoiVe.add(sanPhamList.get(i));
            }

            if(listSPMoiVe.size()>0) {
                for (int i = 0; i < listSPMoiVe.size(); i++) {

                    int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(listSPMoiVe.get(i).getMASP());
                    if(phantramkm != 0)
                    {
                        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                        chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                        listSPMoiVe.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                    }
                }
            }

            viewDienTu.HienThiSanPhamMoiVe(listSPMoiVe);
        }
        else
        {

        }
    }
}
