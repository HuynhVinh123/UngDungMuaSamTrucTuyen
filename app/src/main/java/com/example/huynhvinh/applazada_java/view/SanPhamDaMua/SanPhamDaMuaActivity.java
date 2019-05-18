package com.example.huynhvinh.applazada_java.view.SanPhamDaMua;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.huynhvinh.applazada_java.Adapter.ChiTietHoaDonAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Presenter.SanPhamDaMua.PresenterLogicSanPhamDaMua;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDaMuaActivity extends AppCompatActivity  implements  ViewSanPhamDaMua{

    Toolbar toolbar;
    PresenterLogicSanPhamDaMua presenterLogicSanPhamDaMua;
    DangNhapModel dangNhapModel;
    RecyclerView recyclerSanPhamDaMua;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamdamua);

        recyclerSanPhamDaMua = (RecyclerView) findViewById(R.id.recyclerSanPhamDaMua);
        toolbar = (Toolbar) findViewById(R.id.toolbarSanPhamDaMua);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sản phẩm đã mua");

        dangNhapModel = new DangNhapModel();
        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);

        presenterLogicSanPhamDaMua = new PresenterLogicSanPhamDaMua(this);
        presenterLogicSanPhamDaMua.LayDanhSachSanPhamDaMua("thanhcong",manv);

    }

    @Override
    public void HienThiDanhSachSanPhamDaMua(List<HoaDon> hoaDonList) {
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        for(int i=0; i<hoaDonList.size();i++)
        {
            for(int j=0; j< hoaDonList.get(i).getChiTietHoaDonList().size();j++)
            {
                chiTietHoaDonList.add(hoaDonList.get(i).getChiTietHoaDonList().get(j));
            }
        }

        ChiTietHoaDonAdapter chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(this,chiTietHoaDonList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerSanPhamDaMua.setLayoutManager(layoutManager);
        recyclerSanPhamDaMua.setAdapter(chiTietHoaDonAdapter);
        chiTietHoaDonAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
