package com.example.huynhvinh.applazada_java.view.SuaThongTinSanPham;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.SuaThongTinSanPham.PresenterLogicSuaThongTinSanPham;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan.QuanLyTaiKhoanActivity;

import es.dmoral.toasty.Toasty;

public class SuaThongTinSabPhamActivity extends AppCompatActivity implements  ViewSuaThongTinSanPham, View.OnClickListener {

    private PresenterLogicSuaThongTinSanPham presenterLogicSuaThongTinSanPham;
    private EditText edt_TenSanPham,edt_ThongTinSanPham,edt_DatGia,edt_SoLuong;
    private  FButton btn_XacNhan;
    int masp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suathongtinsanpham);

        btn_XacNhan = (FButton) findViewById(R.id.btn_XacNhanSua);
        edt_TenSanPham = (EditText) findViewById(R.id.edtSuaTenSanPham);
        edt_ThongTinSanPham = (EditText) findViewById(R.id.edtSuaMoTaSanPham);
        edt_DatGia = (EditText) findViewById(R.id.edtSuaGiaSanPham);
        edt_SoLuong = (EditText) findViewById(R.id.edtSuaSoLuong);

        masp = getIntent().getIntExtra("masp",0);

        presenterLogicSuaThongTinSanPham = new PresenterLogicSuaThongTinSanPham(this);
        presenterLogicSuaThongTinSanPham.LayChiTietSanPham(masp);

        btn_XacNhan.setOnClickListener(this);

    }

    @Override
    public void HienThiSanPham(SanPham sanPham) {
        edt_TenSanPham.setText(sanPham.getTENSP());
        edt_ThongTinSanPham.setText(sanPham.getTHONGTIN());
        edt_DatGia.setText(sanPham.getGIA()+"");
        edt_SoLuong.setText(sanPham.getSOLUONG() + "");
    }

    @Override
    public void CapNhatSanPhamThanhCong() {
        Toasty .success(this,"Cập nhật sản phẩm thành công",Toasty.LENGTH_SHORT,true).show();
        Intent iQuanLyTaiKhoan = new Intent(this, QuanLyTaiKhoanActivity.class);
        startActivity(iQuanLyTaiKhoan);
    }

    @Override
    public void CapNhatSanPhamThatBai() {
        Toasty.error(this,"Cập nhật sản phẩm thất bại",Toasty.LENGTH_SHORT,true).show();
    }

    @Override
    public void onClick(View v) {

        String tensp = edt_TenSanPham.getText().toString();
        String thongtin = edt_ThongTinSanPham.getText().toString();
        int Gia = Integer.parseInt(edt_DatGia.getText().toString());
        int SoLuong = Integer.parseInt(edt_SoLuong.getText().toString());

        SanPham sanPham = new SanPham();
        sanPham.setTENSP(tensp);
        sanPham.setTHONGTIN(thongtin);
        sanPham.setGIA(Gia);
        sanPham.setSOLUONG(SoLuong);
        sanPham.setMASP(masp);

        presenterLogicSuaThongTinSanPham.SuaThongTinSanPham(sanPham);
    }
}
