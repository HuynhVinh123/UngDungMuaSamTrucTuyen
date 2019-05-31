package com.example.huynhvinh.applazada_java.view.SanPhamYeuThich;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.SanPhamYeuThichAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Presenter.GioHang.PresenterLogicSanPhamYeuThich;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class SanPhamYeuThichActivity extends AppCompatActivity implements ViewSanPhamYeuThich {

    PresenterLogicSanPhamYeuThich presenterLogicSanPhamYeuThich;
    RecyclerView recySPYeuThich;
    Toolbar toolbarSPYeuThich;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamyeuthich);

        recySPYeuThich = (RecyclerView) findViewById(R.id.recySanPhamYeuThich);
        toolbarSPYeuThich = (Toolbar) findViewById(R.id.toolbarSPYeuThich);
        setSupportActionBar(toolbarSPYeuThich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Danh sách sản phẩm yêu thích");

        presenterLogicSanPhamYeuThich = new PresenterLogicSanPhamYeuThich(this);

        presenterLogicSanPhamYeuThich.LayDanhSachSanPhamYeuThich(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void HienThiDanhSachSanPhamYeuThich(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        SanPhamYeuThichAdapter adapter = new SanPhamYeuThichAdapter(this,R.layout.custom_layout_sanphamyeuthich,sanPhamList,presenterLogicSanPhamYeuThich);
        recySPYeuThich.setLayoutManager(layoutManager);
        recySPYeuThich.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void XoaSanPhamThanhcong() {
        Toasty.success(this, "Xóa sản phẩm thành công!", Toast.LENGTH_SHORT,true).show();
        presenterLogicSanPhamYeuThich.LayDanhSachSanPhamYeuThich(this);
    }

    @Override
    public void XoaSanPhamThatBai() {
        Toasty.error(this, "Xóa sản phẩm thất bại!", Toast.LENGTH_SHORT,true).show();

    }
}