package com.example.huynhvinh.applazada_java.view.ThemSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.ExpandLoaiSanPhamAdapter;
import com.example.huynhvinh.applazada_java.Presenter.ThemSanPham.EventClickExpan;
import com.example.huynhvinh.applazada_java.Presenter.ThemSanPham.PrensenterLogicThemSanPham;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;

import java.util.List;

public class DanhSachLoaiSanPhamActivity extends AppCompatActivity implements ViewDanhSachLoaiSanPham {

    PrensenterLogicThemSanPham prensenterLogicThemSanPham;
    ExpandableListView expandableListView;
    Toolbar toolbar;
    int checkThemSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachloaisanpham);

        expandableListView = (ExpandableListView) findViewById(R.id.expanLoaiSanPham);
        toolbar = (Toolbar) findViewById(R.id.toolbarLoaiSanPham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Danh sách loại sản phẩm");

        checkThemSanPham = getIntent().getIntExtra("checkThemSanPham",-1);

        prensenterLogicThemSanPham = new PrensenterLogicThemSanPham(this);
        prensenterLogicThemSanPham.LayDanhSachLoaiSanPham();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void HienThiDanhSachLoaiSanPham(List<LoaiSanPham> loaiSanPhamList) {
        ExpandLoaiSanPhamAdapter expandLoaiSanPhamAdapter = new ExpandLoaiSanPhamAdapter(this,loaiSanPhamList,
        new EventClickExpan() {
            @Override
            public void onClick() {
                onBackPressed();
            }
        },checkThemSanPham);
        expandableListView.setAdapter(expandLoaiSanPhamAdapter);
        expandLoaiSanPhamAdapter.notifyDataSetChanged();
    }
}