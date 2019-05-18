package com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon.IPresenterQuanLyHoaDon;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon.PresenterLogicQuanLyHoaDon;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.view.DanhGia.DanhSachDanhGiaActivity;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.DonHangActivity;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.QuanLyHoaDonActivity;
import com.example.huynhvinh.applazada_java.view.SanPhamDaMua.SanPhamDaMuaActivity;
import com.example.huynhvinh.applazada_java.view.SanPhamYeuThich.SanPhamYeuThichActivity;
import com.example.huynhvinh.applazada_java.view.ThemSanPham.ThemSanPhamActivity;

import java.util.List;


public class QuanLyTaiKhoanActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbarQLTK;
    TextView txtTenDN,txtDangNhapBang,txtSoLuongDonHang,txtSoLuongSoLuongSanPham;
    DangNhapModel dangNhapModel;
    PresenterLogicQuanLyHoaDon presenterQuanLyHoaDon;
    LinearLayout linearChiTietNhanVien,lnDonHangChoKiemDuyet,lnDonHangThanhCong,lnDonHangDaHuy,lnDonHangDangVanChuyen,lnSanPhamDaMua,lnSanPhamYeuThich,lnNhanXetCuaToi;
    TextView txtDonHangChoKiemDuyet;
    FButton fButtonBanHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlytaikhoan);

        fButtonBanHang = (FButton) findViewById(R.id.f_twitter_button);
        lnNhanXetCuaToi = (LinearLayout) findViewById(R.id.lnNhanXetCuaToi);
        lnSanPhamYeuThich = (LinearLayout) findViewById(R.id.lnSanPhamYeuThich);
        lnSanPhamDaMua = (LinearLayout) findViewById(R.id.lnSanPhamDaMua);
        lnDonHangDangVanChuyen = (LinearLayout) findViewById(R.id.lnDonHangDangVanChuyen);
        lnDonHangDaHuy = (LinearLayout) findViewById(R.id.lnDonHangDaHuy);
        lnDonHangThanhCong = (LinearLayout) findViewById(R.id.lnDonHangThanhCong);
        lnDonHangChoKiemDuyet = (LinearLayout) findViewById(R.id.lnDonHangChoKiemDuyet);
        txtDonHangChoKiemDuyet = (TextView) findViewById(R.id.txtDonHangChoKiemDuyet);
        linearChiTietNhanVien = (LinearLayout) findViewById(R.id.linearThongTinChiTiet);
        txtSoLuongSoLuongSanPham = (TextView) findViewById(R.id.txtSoLuongSanPhamQLTK);
        txtSoLuongDonHang = (TextView) findViewById(R.id.txtSoLuongDonHang);
        txtDangNhapBang = (TextView) findViewById(R.id.txtDangNhapBang);
        txtTenDN = (TextView) findViewById(R.id.txtTenDNQLTK);
        toolbarQLTK = (Toolbar) findViewById(R.id.toolbarQuanLyTaiKhoan);
        setSupportActionBar(toolbarQLTK);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        initView();



    }


    private void initView() {

        dangNhapModel = new DangNhapModel();
        String tennv = dangNhapModel.LayCachedDangNhap(this);
        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);
        int SoLuongSanPham =0;
        txtTenDN.setText(tennv);
        String check = dangNhapModel.LayCacheKiemTraDangNhap(this);

        if(check.trim().equals("0"))
        {
            txtDangNhapBang.setText("Tài khoản Google");
        }else if(check.trim().equals("1")){
            txtDangNhapBang.setText("Tài khoản Facebook");
        }else if(check.trim().equals("3")){
            txtDangNhapBang.setText("Tài khoản ứng dụng");
        }

        presenterQuanLyHoaDon = new PresenterLogicQuanLyHoaDon();
        List<HoaDon> hoaDonList =   presenterQuanLyHoaDon.DanhSachHoaDon(manv);
        for (int i=0;i<hoaDonList.size();i++)
        {
            SoLuongSanPham += hoaDonList.get(i).getChiTietHoaDonList().size();
        }

        txtSoLuongSoLuongSanPham.setText(SoLuongSanPham+"");
        txtSoLuongDonHang.setText(hoaDonList.size()+"");

        linearChiTietNhanVien.setOnClickListener(this);
        lnDonHangChoKiemDuyet.setOnClickListener(this);
        lnDonHangThanhCong.setOnClickListener(this);
        lnDonHangDaHuy.setOnClickListener(this);
        lnDonHangDangVanChuyen.setOnClickListener(this);
        lnSanPhamDaMua.setOnClickListener(this);
        lnSanPhamYeuThich.setOnClickListener(this);
        lnNhanXetCuaToi.setOnClickListener(this);
        fButtonBanHang.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.linearThongTinChiTiet:
                Intent iThongTinTK = new Intent(this, ThongTinTaiKhoanActivity.class);
                startActivity(iThongTinTK);
                break;
            case R.id.lnDonHangChoKiemDuyet:
                Intent iQuanLyDonHang = new Intent(this, DonHangActivity.class);
                iQuanLyDonHang.putExtra("trangthai","chokiemduyet");
                startActivity(iQuanLyDonHang);
                break;
            case R.id.lnDonHangDaHuy:
                Intent iQuanLyDonHangDaHuy = new Intent(this, DonHangActivity.class);
                iQuanLyDonHangDaHuy.putExtra("trangthai","dahuy");
                startActivity(iQuanLyDonHangDaHuy);
                break;
            case R.id.lnDonHangDangVanChuyen:
                Intent iQuanLyDonHangDangVanChuyen = new Intent(this, DonHangActivity.class);
                iQuanLyDonHangDangVanChuyen.putExtra("trangthai","dangvanchuyen");
                startActivity(iQuanLyDonHangDangVanChuyen);
                break;
            case R.id.lnDonHangThanhCong:
                Intent iQuanLyDonHangThanhCong = new Intent(this, DonHangActivity.class);
                iQuanLyDonHangThanhCong.putExtra("trangthai","thanhcong");
                startActivity(iQuanLyDonHangThanhCong);
                break;
            case R.id.lnSanPhamDaMua:
                Intent iSanPhamDaMua = new Intent(this, SanPhamDaMuaActivity.class);
                startActivity(iSanPhamDaMua);
                break;
            case R.id.lnSanPhamYeuThich:
                Intent iSanPhamYeuThich = new Intent(this, SanPhamYeuThichActivity.class);
                startActivity(iSanPhamYeuThich);
                break;
            case R.id.lnNhanXetCuaToi:
                Intent iDanhSachDanhGia = new Intent(this, DanhSachDanhGiaActivity.class);
                startActivity(iDanhSachDanhGia);
                break;
            case R.id.f_twitter_button:
                Intent iThemSanPham = new Intent(this, ThemSanPhamActivity.class);
                iThemSanPham.putExtra("kiemtra",0);
                startActivity(iThemSanPham);
                break;
        }
    }
}