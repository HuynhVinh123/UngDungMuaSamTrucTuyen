package com.example.huynhvinh.applazada_java.view.ChiTietHoaDon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.ChiTietHoaDonAdapter;
import com.example.huynhvinh.applazada_java.Presenter.ChiTietHoaDon.PresenterLogicChiTietHoaDon;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ViewChiTietSanPham;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.DonHangActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ChiTietHoaDonActivity extends AppCompatActivity implements ViewChiTietHoaDon, View.OnClickListener {

    Toolbar toolbarCTHD;
    PresenterLogicChiTietHoaDon presenterLogicChiTietHoaDon;
    DangNhapModel dangNhapModel;
    int mahd;
    TextView txtMaDonHang,txtNgayDatHoaDonChiTiet,txtTrangThai,txtTenNguoiNhan,txtSoDT,txtDiaChi,txtHinhThucGiaoHang,txtTongTienCTHD;
    RecyclerView recyclerView;
    LinearLayout lnHuyDonHang;
    Button btnHuyDonHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiethoadon);

        btnHuyDonHang = (Button) findViewById(R.id.btnHuyDonHang);
        lnHuyDonHang = (LinearLayout) findViewById(R.id.lnHuyDonHang);
        txtTongTienCTHD = (TextView) findViewById(R.id.txtTongTienCTHD);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerChiTietHoaDon);
        txtHinhThucGiaoHang = (TextView) findViewById(R.id.txtHinhThucGiaoHang);
        txtDiaChi = (TextView) findViewById(R.id.txtDiaChiNguoiNhanCTHD);
        txtSoDT = (TextView) findViewById(R.id.txtSoDTCTHD);
        txtTenNguoiNhan = (TextView) findViewById(R.id.txtTenNguoiNhanCTHD);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThaiHoaDonChiTiet);
        txtNgayDatHoaDonChiTiet = (TextView) findViewById(R.id.txtMaHoaDonChiTiet);
        txtMaDonHang = (TextView) findViewById(R.id.txtMaHoaDonChiTiet);
        toolbarCTHD = (Toolbar) findViewById(R.id.toolbarChiTietHD);
        setSupportActionBar(toolbarCTHD);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Chi tiết đơn hàng");

        dangNhapModel = new DangNhapModel();

        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);
        mahd = getIntent().getIntExtra("mahd",-1);

        presenterLogicChiTietHoaDon = new PresenterLogicChiTietHoaDon(this);
        presenterLogicChiTietHoaDon.LayDanhSachSanPhamCuaHoaDon(manv);

        btnHuyDonHang.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void HienThiDanhSachSanPham(List<HoaDon> hoaDonList) {


        HoaDon hoaDon = new HoaDon();
        for(int i=0; i<hoaDonList.size();i++)
        {
            if(mahd==hoaDonList.get(i).getMaHD())   // Lấy thông tin chi tiết của 1 hóa đơn
            {
                hoaDon = hoaDonList.get(i);
            }
        }

        int TongTien =0;

        for(int i=0; i<hoaDon.getChiTietHoaDonList().size();i++)
        {
            ChiTietHoaDon chiTietHoaDon = hoaDon.getChiTietHoaDonList().get(i);
            ChiTietKhuyenMai chiTietKhuyenMai = chiTietHoaDon.getChiTietKhuyenMai();
            if(chiTietKhuyenMai!=null)
            {
                int GiaSP = chiTietHoaDon.getGia()*chiTietKhuyenMai.getPHANTRAMKM()/100;
                TongTien += (GiaSP*hoaDon.getChiTietHoaDonList().get(i).getSoLuong());
            }else {
                int GiaSP = chiTietHoaDon.getGia();
                TongTien += (GiaSP*hoaDon.getChiTietHoaDonList().get(i).getSoLuong());
            }

        }
        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        String gia = formatter.format(TongTien);
        txtTongTienCTHD.setText(gia + " VNĐ");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ChiTietHoaDonAdapter adapter = new ChiTietHoaDonAdapter(this,hoaDon.getChiTietHoaDonList());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        txtMaDonHang.setText(hoaDon.getMaHD()+"");
        txtSoDT.setText(hoaDon.getSoDT());
        if(hoaDon.getTrangThai().trim().equals("chokiemduyet")) {
            lnHuyDonHang.setVisibility(View.VISIBLE);
            txtTrangThai.setText("Chờ kiểm duyệt");
        }else if(hoaDon.getTrangThai().trim().equals("dahuy"))
        {
            txtTrangThai.setText("Đã hủy");
        }else if(hoaDon.getTrangThai().trim().equals("thanhcong"))
        {
            txtTrangThai.setText("Giao hàng thành công");
        }else if(hoaDon.getTrangThai().trim().equals("dangvanchuyen"))
        {
            txtTrangThai.setText("Đang vận chuyển");
        }
        txtTenNguoiNhan.setText(hoaDon.getTenNguoiNhan());
        txtDiaChi.setText(hoaDon.getDiaChi());
        txtHinhThucGiaoHang.setText("Giao hàng tiêu chuẩn(Dự kiến giao hàng ngày "+ hoaDon.getNgayMua() +" )");

        NestedScrollView nestedScrollViewChiTiet = (NestedScrollView) findViewById(R.id.nestScrollviewCTHD);
        nestedScrollViewChiTiet.fullScroll(View.FOCUS_UP);
        nestedScrollViewChiTiet.smoothScrollTo(0,0);

    }

    @Override
    public void HuyHoaDonThanhCong() {
        Intent iDonHang = new Intent(this, DonHangActivity.class);
        iDonHang.putExtra("trangthai","chokiemduyet");
        startActivity(iDonHang);
        Toasty.success(this, "Hủy hóa đơn thành công!", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void HuyHoaDonThatBai() {
        Toasty.error(this, "Hủy hóa đơn thất bại!", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void onClick(View v) {
        presenterLogicChiTietHoaDon.HuyDonHang(mahd);
    }
}