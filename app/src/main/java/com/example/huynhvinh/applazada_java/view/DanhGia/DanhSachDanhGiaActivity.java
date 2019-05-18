package com.example.huynhvinh.applazada_java.view.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ProgressBar;

import com.example.huynhvinh.applazada_java.Adapter.DanhGiaAdapter;
import com.example.huynhvinh.applazada_java.Adapter.NhanVienDanhGiaAdapter;
import com.example.huynhvinh.applazada_java.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ILoadMore;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoadMoreScroll;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements  ViewDanhgia, ILoadMore {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    DangNhapModel dangNhapModel;
    List<DanhGia> TatCaDanhGiaList;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsach_danhgia);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbarDanhSachDanhGia);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh sách đánh giá");

        TatCaDanhGiaList = new ArrayList<>();

        masp = getIntent().getIntExtra("masp",0);
        presenterLogicDanhGia  = new PresenterLogicDanhGia(this);

        if(masp==0)
        {
            // Show ra danh sách đánh giá của nhân viên
            //1. Lấy mã nhân viên
            dangNhapModel = new DangNhapModel();
            String idnv = dangNhapModel.LayCacheMaNVDangNhap(this);
            presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();
            NhanVien nhanVien = presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(idnv);
            int manv = nhanVien.getMaNV();
            presenterLogicDanhGia.LayDanhSachDanhGiaCuaNhanVien(manv);

        }
        else {
            // show ra danh sách đánh gia của sản phẩm
            presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList) {

        TatCaDanhGiaList.addAll(danhGiaList);

        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter(this,TatCaDanhGiaList,0,R.layout.custom_layout_recycler_danhgia_chitiet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(danhGiaAdapter);
        danhGiaAdapter.notifyDataSetChanged();

    }

    @Override
    public void HienThiDanhSachDanhGiaCuaNhanVien(List<DanhGia> danhGiaList) {
        NhanVienDanhGiaAdapter nhanVienDanhGiaAdapter = new NhanVienDanhGiaAdapter(this,danhGiaList,R.layout.custom_layout_danhgiacuanhanvien);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(nhanVienDanhGiaAdapter);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        nhanVienDanhGiaAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
