package com.example.huynhvinh.applazada_java.view.QuanLyHoaDon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.QuanLyDonHangAdapter;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon.PresenterLogicQuanLyHoaDon;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

import java.util.List;

public class QuanLyHoaDonActivity extends AppCompatActivity implements ViewQuanLyHoaDon {

    Toolbar toolbar;
    PresenterLogicQuanLyHoaDon logicQuanLyHoaDon;
    RecyclerView recyclerQuanLyDonhang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlyhoadon);

        recyclerQuanLyDonhang = (RecyclerView) findViewById(R.id.recyclerQuanLyHoaDon);
        toolbar = (Toolbar) findViewById(R.id.toolbarQuanLyHoaDon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đơn hàng của tôi");

        DangNhapModel dangNhapModel = new DangNhapModel();

        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);

        logicQuanLyHoaDon = new PresenterLogicQuanLyHoaDon(this);
        logicQuanLyHoaDon.LayDanhSachHoaDon(manv);
    }

    @Override
    public void HienThiDanhSachHoaDon(List<HoaDon> hoaDonList) {
        for(int i=0;i<hoaDonList.size();i++)
        {
            QuanLyDonHangAdapter quanLyDonHangAdapter = new QuanLyDonHangAdapter(this,hoaDonList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerQuanLyDonhang.setLayoutManager(layoutManager);
            recyclerQuanLyDonhang.setAdapter(quanLyDonHangAdapter);
            quanLyDonHangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}