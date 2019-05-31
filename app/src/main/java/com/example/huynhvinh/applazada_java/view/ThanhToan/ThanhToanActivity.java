package com.example.huynhvinh.applazada_java.view.ThanhToan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener,ViewThanhToan {

    Toolbar toolbarThanhToan;
    EditText edTenNguoiNhan,edDiaChi,edSoDT,edEmail;
    ImageButton imNhanTienKhiGiaoHang,imChuyenKhoan;
    TextView txtNhanTienKhiGiaoHang,txtChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoaThuan,cb_giaohang;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
    int giatien;
    String manguoinhan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_thanhtoan);

        cb_giaohang = (CheckBox) findViewById(R.id.cb_giaohang);
        edTenNguoiNhan = (EditText) findViewById(R.id.edTenNguoiNhan);
        edDiaChi = (EditText) findViewById(R.id.edDiaChi);
        edSoDT = (EditText) findViewById(R.id.edSoDT);
        imNhanTienKhiGiaoHang = (ImageButton) findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = (ImageButton) findViewById(R.id.imChuyenKhoan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        cbThoaThuan = (CheckBox) findViewById(R.id.cbThoaThuan);
        txtNhanTienKhiGiaoHang = (TextView) findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = (TextView) findViewById(R.id.txtChuyenKhoan);
        edEmail = (EditText) findViewById(R.id.edEmail);

        toolbarThanhToan = (Toolbar) findViewById(R.id.toolbarThanhToan);
        setSupportActionBar(toolbarThanhToan);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thanh toán giỏ hàng");

        giatien = getIntent().getIntExtra("sotien",0);

        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);

        initView();

        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiGiaoHang.setOnClickListener(this);


    }

    private void initView() {
        DangNhapModel dangNhapModel = new DangNhapModel();
        String tennguoinhan = dangNhapModel.LayCachedDangNhap(this);
        String sodt = dangNhapModel.LayCacheSoDTDangNhap(this);
        String diachi = dangNhapModel.LayCacheDiaChiDangNhap(this);
        manguoinhan = dangNhapModel.LayCacheMaNVDangNhap(this);

        edTenNguoiNhan.setText(tennguoinhan);
        Log.d("kiemtra",diachi);
        if(!diachi.equals("null"))
        {
            edDiaChi.setText(diachi);
        }

        Boolean kiemtra = Patterns.EMAIL_ADDRESS.matcher(sodt).matches();
        if(kiemtra)
        {
            edEmail.setText(sodt);
        }else {

            edSoDT.setText(sodt);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.btnThanhToan:

                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String email = edEmail.getText().toString();
                Boolean kiemtra = Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if(tennguoinhan.trim().length()>0 && sodt.trim().length()>0 && diachi.trim().length()>0 && email.trim().length()>0 && kiemtra)
                {
                    if(cbThoaThuan.isChecked()){

                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setSoDT(sodt);
                        hoaDon.setDiaChi(diachi);
                        hoaDon.setEmail(email);
                        hoaDon.setSoTien(giatien);
                        hoaDon.setMaNguoiNhan(manguoinhan);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDonList);
                        presenterLogicThanhToan.ThemHoaDon(hoaDon);
                    }else {
                        Toasty.warning(this,"Bạn chưa nhấn chọn vào ô thỏa thuận !", Toast.LENGTH_SHORT,true).show();
                    }
                }else{
                    Toasty.warning(this, "Bạn chưa nhập đầy đủ các thông tin !", Toast.LENGTH_SHORT,true).show();
                }
                break;

        }
    }

    @Override
    public void DatHangThanhCong() {
        Toasty.success(this, "Đặt hàng thành công !", Toast.LENGTH_SHORT,true).show();
        Intent iTrangChu = new Intent(this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toasty.error(this, "Đặt hàng thất bại !", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        for(int i=0; i<sanPhamList.size();i++)
        {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDonList.add(chiTietHoaDon);
        }
    }
}