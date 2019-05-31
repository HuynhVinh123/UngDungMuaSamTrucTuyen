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

import com.example.huynhvinh.applazada_java.Adapter.DonHangAdapter;
import com.example.huynhvinh.applazada_java.Adapter.QuanLyDonHangAdapter;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon.PresenterLogicQuanLyHoaDon;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyHoaDon.PresenterLogicQuanLyTrangThaiHoaDon;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class DonHangActivity extends AppCompatActivity implements  ViewDonHang{

    Toolbar toolbar;
    PresenterLogicQuanLyTrangThaiHoaDon presenterLogicQuanLyTrangThaiHoaDon;
    RecyclerView recyclerQuanLyDonhang;
    private  String trangthai;
    DangNhapModel dangNhapModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_quanlyhoadon);

        recyclerQuanLyDonhang = (RecyclerView) findViewById(R.id.recyclerQuanLyHoaDon);
        toolbar = (Toolbar) findViewById(R.id.toolbarQuanLyHoaDon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dangNhapModel = new DangNhapModel();

        String manguoinhan = dangNhapModel.LayCacheMaNVDangNhap(this);
        trangthai = getIntent().getStringExtra("trangthai");

        // set title cho ToolBar
        if(trangthai.equals("chokiemduyet"))
        {
            getSupportActionBar().setTitle("Đơn hàng chờ kiểm duyệt");
        }else if(trangthai.equals("dahuy")){
            getSupportActionBar().setTitle("Đơn hàng đã hủy");
        }else if(trangthai.equals("thanhcong"))
        {
            getSupportActionBar().setTitle("Đơn hàng giao thành công");
        }else  if(trangthai.equals("dangvanchuyen"))
        {
            getSupportActionBar().setTitle("Đơn hàng đang vận chuyển");
        }

        presenterLogicQuanLyTrangThaiHoaDon = new PresenterLogicQuanLyTrangThaiHoaDon(this);
        presenterLogicQuanLyTrangThaiHoaDon.LayDanhSachHoaDonTheoTrangThai(trangthai,manguoinhan);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void HienThiDanhSachDonHang(List<HoaDon> hoaDonList) {
        for(int i=0;i<hoaDonList.size();i++)
        {
            DonHangAdapter donHangAdapter = new DonHangAdapter(this,hoaDonList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerQuanLyDonhang.setLayoutManager(layoutManager);
            recyclerQuanLyDonhang.setAdapter(donHangAdapter);
            donHangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void LayDonHangThatBai() {
        Toasty.error(this, "Lấy đơn hàng thất bại!", Toast.LENGTH_SHORT,true).show();
    }


}
