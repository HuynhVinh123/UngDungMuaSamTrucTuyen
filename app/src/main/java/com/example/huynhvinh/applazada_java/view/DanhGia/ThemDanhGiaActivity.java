package com.example.huynhvinh.applazada_java.view.DanhGia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhgia, View.OnClickListener {

    TextInputLayout input_edTieuDe, input_edNoiDung;
    EditText edTieuDe, edNoiDung;
    RatingBar rbDanhGia;
    Button btnDongYDanhGia;
    int masp = 0;
    int sosao = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    ImageView imgHinhSPDanhGia;
    TextView txtTenSPDanhGia;
    Toolbar toolbarDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_themdanhgia);

        input_edNoiDung = (TextInputLayout) findViewById(R.id.input_edtNoiDungDanhgia);
        input_edTieuDe = (TextInputLayout) findViewById(R.id.input_edtTieuDeDanhGia);
        edTieuDe = (EditText) findViewById(R.id.edtTieuDe);
        edNoiDung = (EditText) findViewById(R.id.edtNoiDung);
        rbDanhGia = (RatingBar) findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = (Button) findViewById(R.id.btnDongYDanhGia);
        imgHinhSPDanhGia = (ImageView) findViewById(R.id.imgHinhSPDanhGia);
        txtTenSPDanhGia = (TextView) findViewById(R.id.txtTenSPDanhGia);
        toolbarDanhGia = (Toolbar) findViewById(R.id.toolbarDanhGia);
        setSupportActionBar(toolbarDanhGia);
        getSupportActionBar().setTitle("Viết đánh giá");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        masp = getIntent().getIntExtra("masp", 0);

        initView();

        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);
    }

    private void initView() {

        String hinhsp = getIntent().getStringExtra("hinhsp");
        String tensp = getIntent().getStringExtra("tensp");

        Picasso.with(this).load(hinhsp).into(imgHinhSPDanhGia);
        txtTenSPDanhGia.setText(tensp);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toasty.success(this, "Đánh giá thành công !", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toasty.error(this, "Bạn không thể đánh giá sản phẩm này !", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList) {

    }

    @Override
    public void HienThiDanhSachDanhGiaCuaNhanVien(List<DanhGia> danhGiaList) {

    }


    @Override
    public void onClick(View v) {

        DangNhapModel dangNhapModel = new DangNhapModel();
        String madg = Build.MODEL + masp; // lấy tên thiết bị

        String tennguoidanhgia =  dangNhapModel.LayCachedDangNhap(this);
        String idnv = dangNhapModel.LayCacheMaNVDangNhap(this);
        String keyCheck = dangNhapModel.LayCacheKiemTraDangNhap(this);
        int manv = -1;

        // kiểm tra đánh giá để lấy thông tin nhân viên bằng id (vd: facebook or google)

        if(!keyCheck.trim().equals("3"))
        {
            presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();
            NhanVien nhanVien = presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(idnv);
            manv = nhanVien.getMaNV();
        }

        //String madg= Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        String tenthietbi = tennguoidanhgia;
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();

        if(tieude.trim().length()>0)
        {
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        }else{
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập tiêu đề !");
        }

        if(noidung.trim().length()>0){
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        }else{
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập nội dung !");
        }

        if(!input_edTieuDe.isErrorEnabled() && !input_edNoiDung.isErrorEnabled())
        {
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setMASP(masp);
            danhGia.setNOIDUNG(noidung);
            danhGia.setTIEUDE(tieude);
            danhGia.setSOSAO(sosao);
            danhGia.setTENTHIETBI(tenthietbi);
            danhGia.setMANV(manv);

            presenterLogicDanhGia.ThemDanhGia(danhGia);

            finish();
        }
    }
}
