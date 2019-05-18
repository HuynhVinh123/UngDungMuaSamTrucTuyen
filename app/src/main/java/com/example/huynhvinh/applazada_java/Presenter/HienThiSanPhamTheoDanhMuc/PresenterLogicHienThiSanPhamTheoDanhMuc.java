package com.example.huynhvinh.applazada_java.Presenter.HienThiSanPhamTheoDanhMuc;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.model.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements  IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    HienThiSanPhamTheoDanhMucModel hienThiSanPhamTheoDanhMucModel;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        hienThiSanPhamTheoDanhMucModel = new HienThiSanPhamTheoDanhMucModel();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    @Override
    public void LayDanhSachSanPhamTheoMaLoai(int masp,boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();


        if(kiemtra)
        {
            sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", 0); // lấy danh sách sản phẩm theo mã thương hiệu
            // kiểm tra sản phầm có khuyến mãi hay không
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
        }else {
             sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", 0);
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
        }

        if(sanPhamList.size()>0)
        {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);
        }
        else
        {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSanPham();
        }
    }

    @Override
    public void LayDanhSachTatCaSanPhamTheoMaLoai(int masp, boolean kiemtra,int CheckChonLoc) {

        List<SanPham> sanPhamList = new ArrayList<>();
        if(kiemtra)
        {
           sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachTatSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachTatCaSanPhamTheoMaThuongHieu"); // lấy danh sách sản phẩm theo mã thương hiệu
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
        }else {
            sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachTatSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachTatCaSanPhamTheoMaLoaiDanhMuc");
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
        }

        if(sanPhamList.size()>0)
        {
//            if(CheckChonLoc == 0)
//            {
//                SapXepSanPhamTheoHangMoiVe(sanPhamList);
//            }
//            else if(CheckChonLoc == 1 )
//            {
//                SapXepSanPhamTheoHangBanChay(sanPhamList);
//            }
//            else if(CheckChonLoc ==2 )
//            {
//                SapXepSanPhamTheoGiaTuThapDenCao(sanPhamList);
//            }
//            else{
//                SapXepSanPhamTheoGiaTuCaoDenThap(sanPhamList);
//            }
        }
        else
        {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSanPham();
        }

    }

    private  void SapXepSanPhamTheoHangMoiVe(List<SanPham> sanPhamList){
        for (int i = 0; i < sanPhamList.size()-1; i++)
        {
            int SanPhamDau = sanPhamList.get(i).getMASP();
            for(int j=i+1;j<sanPhamList.size();j++)
            {
                int SanPhamSau = sanPhamList.get(j).getMASP();
                if(SanPhamDau <=  SanPhamSau)
                {
                    SanPhamDau = sanPhamList.get(j).getMASP();
                    SanPham sanPham = new SanPham();
                    sanPham = sanPhamList.get(i);
                    sanPhamList.set(i,sanPhamList.get(j));
                    sanPhamList.set(j,sanPham);

                }
            }
        }


        viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachTatCaCacSanPhamMoi(sanPhamList);
    }

    private  void SapXepSanPhamTheoHangBanChay(List<SanPham> sanPhamList){
        for (int i = 0; i < sanPhamList.size()-1; i++)
        {
            int SanPhamDau = sanPhamList.get(i).getLUOTMUA();
            for(int j=i+1;j<sanPhamList.size();j++)
            {
                int SanPhamSau = sanPhamList.get(j).getLUOTMUA();
                if(SanPhamDau <=  SanPhamSau)
                {
                    SanPhamDau = sanPhamList.get(j).getLUOTMUA();
                    SanPham sanPham = new SanPham();
                    sanPham = sanPhamList.get(i);
                    sanPhamList.set(i,sanPhamList.get(j));
                    sanPhamList.set(j,sanPham);

                }
            }
        }

        viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachTatCaCacSanPhamMoi(sanPhamList);
    }

    private  void SapXepSanPhamTheoGiaTuThapDenCao(List<SanPham> sanPhamList){
        for (int i = 0; i < sanPhamList.size()-1; i++)
        {
            int SanPhamDau = sanPhamList.get(i).getGIA();
            for(int j=i+1;j<sanPhamList.size();j++)
            {
                int SanPhamSau = sanPhamList.get(j).getGIA();
                if(SanPhamDau >=  SanPhamSau)
                {
                    SanPhamDau = sanPhamList.get(j).getGIA();
                    SanPham sanPham = new SanPham();
                    sanPham = sanPhamList.get(i);
                    sanPhamList.set(i,sanPhamList.get(j));
                    sanPhamList.set(j,sanPham);

                }
            }
        }

        viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachTatCaCacSanPhamMoi(sanPhamList);
    }

    private  void SapXepSanPhamTheoGiaTuCaoDenThap(List<SanPham> sanPhamList){
        for (int i = 0; i < sanPhamList.size()-1; i++)
        {
            int SanPhamDau = sanPhamList.get(i).getGIA();
            for(int j=i+1;j<sanPhamList.size();j++)
            {
                int SanPhamSau = sanPhamList.get(j).getGIA();
                if(SanPhamDau <=  SanPhamSau)
                {
                    SanPhamDau = sanPhamList.get(j).getGIA();
                    SanPham sanPham = new SanPham();
                    sanPham = sanPhamList.get(i);
                    sanPhamList.set(i,sanPhamList.get(j));
                    sanPhamList.set(j,sanPham);

                }
            }
        }


        viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachTatCaCacSanPhamMoi(sanPhamList);

    }

    public  List<SanPham> LayDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean kiemtra, int limit, ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if(kiemtra)
        {
            sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", limit);

        }else {
            sanPhamList  = hienThiSanPhamTheoDanhMucModel.LayDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", limit);
        }

        if(sanPhamList.size()>0)
        {
            progressBar.setVisibility(View.GONE);
        }
        return  sanPhamList;
    }

}