package com.example.huynhvinh.applazada_java.view.SanPhamDaBan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.huynhvinh.applazada_java.Adapter.SanPhamDaBanAdapter;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.Presenter.SanPhamDaBan.PresenterLogicSanPhamDaBan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class SanPhamDaBanActivity extends AppCompatActivity implements ViewSanPhamDaBan {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PresenterLogicSanPhamDaBan presenterLogicSanPhamDaBan;
    private PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    private DangNhapModel dangNhapModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamdaban);

        recyclerView = (RecyclerView) findViewById(R.id.recySanPhamDaBan);
        toolbar = (Toolbar) findViewById(R.id.toolbarSanPhamDaBan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenterLogicSanPhamDaBan = new PresenterLogicSanPhamDaBan(this);
        presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();

        dangNhapModel = new DangNhapModel();
        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);
        String checkDangNhap = dangNhapModel.LayCacheKiemTraDangNhap(this);
        if (checkDangNhap.trim().equals("3"))
        {
            presenterLogicSanPhamDaBan.LayDanhSachSanPham(Integer.parseInt(manv));
        }else if(checkDangNhap.trim().equals("1") || checkDangNhap.trim().equals("0"))
        {
            NhanVien nhanVien =  presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(manv);

            presenterLogicSanPhamDaBan.LayDanhSachSanPham(nhanVien.getMaNV());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        SanPhamDaBanAdapter sanPhamDaBanAdapter  = new SanPhamDaBanAdapter(this,sanPhamList,presenterLogicSanPhamDaBan);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sanPhamDaBanAdapter);
        sanPhamDaBanAdapter.notifyDataSetChanged();
    }

    @Override
    public void XoaSanPhamThanhCong() {
        Toasty.success(this,"Xóa sản phẩm thành công",Toasty.LENGTH_SHORT,true).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void XoaSanPhamThatBai() {
        Toasty.error(this,"Xóa sản phẩm thất bại",Toasty.LENGTH_SHORT,true).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dangNhapModel = new DangNhapModel();
        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);
        String checkDangNhap = dangNhapModel.LayCacheKiemTraDangNhap(this);
        if (checkDangNhap.trim().equals("3"))
        {
            presenterLogicSanPhamDaBan.LayDanhSachSanPham(Integer.parseInt(manv));
        }else if(checkDangNhap.trim().equals("1") || checkDangNhap.trim().equals("0"))
        {
            NhanVien nhanVien =  presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(manv);

            presenterLogicSanPhamDaBan.LayDanhSachSanPham(nhanVien.getMaNV());
        }
    }
}
