package com.example.huynhvinh.applazada_java.view.DanhGia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhgia, View.OnClickListener {

    private TextInputLayout input_edTieuDe, input_edNoiDung;
    private EditText edTieuDe, edNoiDung;
    private RatingBar rbDanhGia;
    private FButton btnDongYDanhGia;
    private int masp = 0;
    private int sosao = 0;
    private PresenterLogicDanhGia presenterLogicDanhGia;
    private PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    private ImageView imgHinhSPDanhGia;
    private TextView txtTenSPDanhGia;
    private Toolbar toolbarDanhGia;
    private ImageView imgDanhGia1,imgDanhGia2,imgDanhGia3;
    private int GALLERY = 1;
    private Bitmap bitmap1 = null,bitmap2 = null,bitmap3 =null;
    private String convertImage1 = "1",convertImage2="1",convertImage3="1" ;
    private ByteArrayOutputStream byteArrayOutputStream1,byteArrayOutputStream2,byteArrayOutputStream3;
    private byte[] byteArray1,byteArray2,byteArray3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_themdanhgia);


        byteArrayOutputStream1 = new ByteArrayOutputStream();
        byteArrayOutputStream2 = new ByteArrayOutputStream();
        byteArrayOutputStream3 = new ByteArrayOutputStream();
        imgDanhGia3 = (ImageView) findViewById(R.id.imgDanhGia3);
        imgDanhGia2 = (ImageView) findViewById(R.id.imgDanhGia2);
        imgDanhGia1 = (ImageView) findViewById(R.id.imgDanhGia1);
        input_edNoiDung = (TextInputLayout) findViewById(R.id.input_edtNoiDungDanhgia);
        input_edTieuDe = (TextInputLayout) findViewById(R.id.input_edtTieuDeDanhGia);
        edTieuDe = (EditText) findViewById(R.id.edtTieuDe);
        edNoiDung = (EditText) findViewById(R.id.edtNoiDung);
        rbDanhGia = (RatingBar) findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = (FButton) findViewById(R.id.btnDongYDanhGia);
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
        imgDanhGia1.setOnClickListener(this);
        imgDanhGia2.setOnClickListener(this);
        imgDanhGia3.setOnClickListener(this);
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

        int id =v.getId();

        switch (id)
        {
            case R.id.btnDongYDanhGia:
                DangNhapModel dangNhapModel = new DangNhapModel();
                String madg = Build.MODEL + masp; // lấy tên thiết bị

                String tennguoidanhgia =  dangNhapModel.LayCachedDangNhap(this);
                String idnv = dangNhapModel.LayCacheMaNVDangNhap(this);
                String keyCheck = dangNhapModel.LayCacheKiemTraDangNhap(this);
                int manv = -1;

                // kiểm tra đánh giá để lấy thông tin nhân viên bằng id (vd: facebook or google)
                NhanVien nhanVien = null;
                presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();
                if(!keyCheck.trim().equals("3"))
                {
                    nhanVien = presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(idnv);
                    manv = nhanVien.getMaNV();
                }else {
                    nhanVien = presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienMaNV(idnv);
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

                // thêm ảnh
                if(bitmap1 != null) {
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream1);
                    byteArray1 = byteArrayOutputStream1.toByteArray();
                    convertImage1 = Base64.encodeToString(byteArray1, Base64.DEFAULT);
                }
                if(bitmap2!=null)
                {
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream2);
                    byteArray2 = byteArrayOutputStream2.toByteArray();
                    convertImage2 = Base64.encodeToString(byteArray2, Base64.DEFAULT);
                }
                if(bitmap3 != null)
                {
                    bitmap3.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream3);
                    byteArray3 = byteArrayOutputStream3.toByteArray();
                    convertImage3 = Base64.encodeToString(byteArray3, Base64.DEFAULT);
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

                    boolean kiemtra = false;
                    if (keyCheck.trim().equals("3"))
                    {
                        danhGia.setMANV(Integer.parseInt(idnv));

                    }else {
                        danhGia.setMANV(manv);
                    }
                    kiemtra = presenterLogicDanhGia.KiemTraThanhVienCoMuaHang(idnv,masp);
                    if(kiemtra) {
                        presenterLogicDanhGia.ThemDanhGia(danhGia, convertImage1, convertImage2, convertImage3,nhanVien.getUyTin() + 3);
                    }else {
                        Toasty.success(this,"Bạn vui lòng đợi hệ thống kiểm duyệt",Toasty.LENGTH_SHORT,true).show();
                    }

                    finish();
                }
                break;
            case R.id.imgDanhGia1:
                GALLERY =1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY);
                break;
            case R.id.imgDanhGia2:
                GALLERY =2;
                Intent galleryIntent2 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent2, GALLERY);
                break;
            case R.id.imgDanhGia3:
                GALLERY =3;
                Intent galleryIntent3 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent3, GALLERY);
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY)
        {
            if(data!=null)
            {
                Uri contentURI =data.getData();

                switch (GALLERY)
                {
                    case 1:
                        try {
                            bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgDanhGia1.setImageBitmap(bitmap1);
                        imgDanhGia2.setEnabled(true);
                        break;
                    case 2:
                        try {
                            bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgDanhGia2.setImageBitmap(bitmap2);
                        imgDanhGia3.setEnabled(true);
                        break;
                    case 3:
                        try {
                            bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgDanhGia3.setImageBitmap(bitmap3);
                        break;
                }
            }
        }
    }
}
