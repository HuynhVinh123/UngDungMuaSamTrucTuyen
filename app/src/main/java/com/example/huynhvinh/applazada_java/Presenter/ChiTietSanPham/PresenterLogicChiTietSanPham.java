package com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham;

import android.content.Context;
import android.util.Log;

import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ChiTietSanPham.ChiTietSanPhamModel;
import com.example.huynhvinh.applazada_java.model.GioHang.GioHangModel;
import com.example.huynhvinh.applazada_java.model.GioHang.SanPhamYeuThichModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ChiTietSanPhamModel chiTietSanPhamModel;
    GioHangModel gioHangModel;
    SanPhamYeuThichModel sanPhamYeuThichModel;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    public  PresenterLogicChiTietSanPham(){

        gioHangModel = new GioHangModel();
        sanPhamYeuThichModel = new SanPhamYeuThichModel();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        chiTietSanPhamModel = new ChiTietSanPhamModel();
        gioHangModel = new GioHangModel();
        sanPhamYeuThichModel = new SanPhamYeuThichModel();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    @Override
    public void LayChiTietSanPham(int masp) {

        SanPham sanPham = chiTietSanPhamModel.LayChiTietSanPham("LaySanPhamVschiTietTheoMaSP","CHITIETSANPHAM",masp);

        // Kiểm tra khuyến mãi cho sản phẩm
        int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(masp);
        if(phantramkm!=0)
        {
            ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
            chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
            sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
        }

        if(sanPham.getMASP()>0){
            String[] linkhinhanh = sanPham.getANHNHO().split(","); // thủ thuật cắt chuỗi bới dấy ","
            viewChiTietSanPham.HienThiSliderSanPham(linkhinhanh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }

    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = chiTietSanPhamModel.LayDanhSachDanhGiaCuaSanPham(masp,limit);

        if(danhGiaList.size()>0)
        {
            viewChiTietSanPham.HienThiDanhGia(danhGiaList);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context,int phantramkhuyenmai) {
        gioHangModel.MoKetNoi(context);


        boolean kiemtra = gioHangModel.ThemGioHang(sanPham,phantramkhuyenmai);
        if(kiemtra)
        {
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else{
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    @Override
    public void ThemSPYeuThich(SanPham sanPham,Context context,int phantramkm) {
        sanPhamYeuThichModel.MoKetNoi(context);
        boolean kiemtra = sanPhamYeuThichModel.ThemSanPhamYeuThich(sanPham,phantramkm);
        if(kiemtra)
        {
            viewChiTietSanPham.ThemSPYeuThichThanhCong();
        }else{
            viewChiTietSanPham.ThemSPYeuThichThatBai();
        }
    }


    public boolean KiemTraThuongHieuLon(int math, String tenth) {
        boolean Kiemtra  = true;    // Nếu kiểm tra là true thì có nghĩa  đó là của thương hiệu lớn và nó có thông số kỹ thuật còn Phụ kiện thì ko có thông số kỹ thuật
        ThuongHieu thuongHieu = chiTietSanPhamModel.kiemTraThuongHieu(math, tenth);
//        if(!thuongHieu.getTENTHUONGHIEU().equals(""))
//        {
//            Kiemtra = true;
//        }else {
//            Kiemtra = false;
//        }
        return  Kiemtra;
    }

    public int DemSoLuongSanPhamCoTrongGioHang(Context context)
    {
        gioHangModel.MoKetNoi(context);
        List<SanPham> sanPhamList = gioHangModel.LayDanhSachSanPhamTrongGioHang();
        int dem = sanPhamList.size();
        return  dem;
    }
}
