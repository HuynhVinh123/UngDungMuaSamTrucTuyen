package com.example.huynhvinh.applazada_java.view.ThemSanPham;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.Presenter.ThemSanPham.PrensenterLogicThemSanPham;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;
import com.example.huynhvinh.applazada_java.model.Room.viewmodel.ThongSoKyThuatViewModel;
import com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan.QuanLyTaiKhoanActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ThemSanPhamActivity extends AppCompatActivity implements View.OnClickListener {

    PrensenterLogicThemSanPham prensenterLogicThemSanPham;
    PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    private ImageView imgThemSP1,imgThemSP2,imgThemSP3;
    private int GALLERY = 1, CAMERA = 4;
    private Bitmap bitmap1 = null,bitmap2 = null,bitmap3 =null;
    private TextView txtLoaiSanPham,txtTenLoaisp;
    private int maloaisp,mathuonghieu;
    private String tenloaisp = "",convertImage1 = "1",convertImage2="1",convertImage3="1" ;
    private FButton btnThemGioHang,btnThemSanPham;
    private EditText edtTenSanPham,edtMoTaSanPham,edtGiaSanPham,edtSoLuongSanPham,edtCanNangSanPham;
    private ByteArrayOutputStream byteArrayOutputStream1,byteArrayOutputStream2,byteArrayOutputStream3;
    private byte[] byteArray1,byteArray2,byteArray3;
    private SanPham sanPham;
    private LinearLayout lnSanPhamTrong,lnThongTinSanPham,lnThuongHieu,lnThongSoKyThuat,lnLoaiSanPham;
    private ThongSoKyThuatViewModel thongSoKyThuatViewModel;
    private Toolbar toolbar;
    private Spinner spinner;
    private List<String> listTenThuongHieu = new ArrayList<>();
    private boolean checkPause = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsanpham);

        imgThemSP1 = (ImageView) findViewById(R.id.imgThemSanPham1);
        imgThemSP2 = (ImageView) findViewById(R.id.imgThemSanPham2);
        imgThemSP3 = (ImageView) findViewById(R.id.imgThemSanPham3);
        txtLoaiSanPham = (TextView) findViewById(R.id.txtLoaiSanPham);
        txtTenLoaisp = (TextView) findViewById(R.id.txtTenLoaiSanPham);
        btnThemGioHang = (FButton) findViewById(R.id.btnThemGioHang);
        edtTenSanPham = (EditText) findViewById(R.id.edtTenSanPham);
        edtMoTaSanPham = (EditText) findViewById(R.id.edtMoTaSanPham);
        edtGiaSanPham = (EditText) findViewById(R.id.edtGiaSanPham);
        edtSoLuongSanPham = (EditText) findViewById(R.id.edtSoLuong);
        edtCanNangSanPham = (EditText) findViewById(R.id.edtCanNang);
        lnSanPhamTrong = (LinearLayout) findViewById(R.id.linearThongTinSanPhamTrong);
        lnThongTinSanPham = (LinearLayout) findViewById(R.id.linearThongTinSanPham);
        lnThongSoKyThuat = (LinearLayout) findViewById(R.id.lnThongSoKyThuatThemSanPham);
        lnLoaiSanPham = (LinearLayout) findViewById(R.id.lnLoaiSanPham);
        btnThemSanPham = (FButton) findViewById(R.id.btnThemSanPham);
        toolbar = (Toolbar) findViewById(R.id.toolbarThemSanPham);
        lnThuongHieu = (LinearLayout) findViewById(R.id.lnThuongHieuThemSanPham);
        spinner = (Spinner) findViewById(R.id.spinnerThuongHieu);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thêm sản phẩm");

        presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();
        prensenterLogicThemSanPham = new PrensenterLogicThemSanPham();
        byteArrayOutputStream1 = new ByteArrayOutputStream();
        byteArrayOutputStream2 = new ByteArrayOutputStream();
        byteArrayOutputStream3 = new ByteArrayOutputStream();

        maloaisp = getIntent().getIntExtra("maloaisp",0);
        if(maloaisp!=0)
        {
            tenloaisp = getIntent().getStringExtra("tenloaisp");
            lnThongTinSanPham.setVisibility(View.VISIBLE);
            lnSanPhamTrong.setVisibility(View.GONE);
            if(maloaisp == 2 || maloaisp == 3 || maloaisp == 4)
            {
                // Sử lý cho spinner của thương hiệu
                lnThuongHieu.setVisibility(View.VISIBLE);
                lnThongSoKyThuat.setVisibility(View.VISIBLE);
                List<ThuongHieu> thuongHieuList = prensenterLogicThemSanPham.LayDanhSachThuongHieu();
                listTenThuongHieu.clear();
                for (int i=0; i< thuongHieuList.size();i++)
                {
                    listTenThuongHieu.add(thuongHieuList.get(i).getTENTHUONGHIEU());
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listTenThuongHieu);

                spinner.setAdapter(arrayAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mathuonghieu = thuongHieuList.get(position).getMATHUONGHIEU();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        }else {
            lnThongTinSanPham.setVisibility(View.GONE);
            lnSanPhamTrong.setVisibility(View.VISIBLE);
        }

        initView();

        imgThemSP1.setOnClickListener(this);
        imgThemSP2.setOnClickListener(this);
        imgThemSP3.setOnClickListener(this);
        txtLoaiSanPham.setOnClickListener(this);
        btnThemGioHang.setOnClickListener(this);
        btnThemSanPham.setOnClickListener(this);
        lnThuongHieu.setOnClickListener(this);
        lnThongSoKyThuat.setOnClickListener(this);
        lnLoaiSanPham.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkPause==true)
        {
            SharedPreferences cachedDangNhap = this.getSharedPreferences("loaisanpham", Context.MODE_PRIVATE);
            maloaisp = cachedDangNhap.getInt("maloaisanpham",-1);
            tenloaisp = cachedDangNhap.getString("tenloaisanpham","");
            txtTenLoaisp.setText(tenloaisp);
            if(maloaisp == 2 || maloaisp == 3 || maloaisp == 4)
            {
                // Sử lý cho spinner của thương hiệu
                lnThuongHieu.setVisibility(View.VISIBLE);
                lnThongSoKyThuat.setVisibility(View.VISIBLE);
                List<ThuongHieu> thuongHieuList = prensenterLogicThemSanPham.LayDanhSachThuongHieu();
                for (int i=0; i< thuongHieuList.size();i++)
                {
                    listTenThuongHieu.add(thuongHieuList.get(i).getTENTHUONGHIEU());
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listTenThuongHieu);

                spinner.setAdapter(arrayAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mathuonghieu = thuongHieuList.get(position).getMATHUONGHIEU();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else {
               lnThuongHieu.setVisibility(View.GONE);
               lnThongSoKyThuat.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
       checkPause = true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void initView() {
        txtTenLoaisp.setText(tenloaisp);
        imgThemSP2.setEnabled(false);
        imgThemSP3.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imgThemSanPham1:
                showDialogForImage(1);
                break;
            case  R.id.imgThemSanPham2:
                showDialogForImage(2);
                break;
            case R.id.imgThemSanPham3:
                showDialogForImage(3);
                break;
            case R.id.lnLoaiSanPham:
                Intent intent = new Intent(this,DanhSachLoaiSanPhamActivity.class);
                startActivity(intent);
                break;
            case R.id.btnThemGioHang:
                if (edtTenSanPham.getText().toString().trim().equals("") || edtMoTaSanPham.getText().toString().trim().equals("") || edtGiaSanPham.getText().toString().trim().equals("") || edtSoLuongSanPham.getText().toString().trim().equals("") || edtCanNangSanPham.getText().toString().trim().equals("") )
                {
                    Toasty.warning(this, "Bạn phải điền đầy đủ tất cả các mục", Toast.LENGTH_SHORT,true).show();
                }else {

                    if(bitmap1==null    ){
                        Toasty.warning(this, "Bạn không được để trống ảnh !", Toast.LENGTH_SHORT,true).show();
                    }
                    else {
                        DangNhapModel dangNhapModel = new DangNhapModel();
                        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);
                        NhanVien nhanVien =  presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(manv);
                        sanPham = new SanPham();
                        // get Thông sô kỹ thuật
                        thongSoKyThuatViewModel = ViewModelProviders.of(this).get(ThongSoKyThuatViewModel.class);
                        thongSoKyThuatViewModel.layDanhSachThongSo().observe(ThemSanPhamActivity.this, new Observer<List<ThongSoKyThuat>>() {
                            @Override
                            public void onChanged(@Nullable List<ThongSoKyThuat> thongSoKyThuats) {
                                Log.d("thongsokythuat",thongSoKyThuats.size() + "");
                                if(thongSoKyThuats.size()>0)
                                {
                                    sanPham.setThongSoKyThuatList(thongSoKyThuats);
                                    sanPham.setTENSP(edtTenSanPham.getText().toString());
                                    sanPham.setGIA(Integer.parseInt(edtGiaSanPham.getText().toString()));
                                    sanPham.setTHONGTIN(edtMoTaSanPham.getText().toString());
                                    sanPham.setMALOAISP(maloaisp);
                                    sanPham.setSOLUONG(Integer.parseInt(edtSoLuongSanPham.getText().toString()));
                                    if(nhanVien.getMaNV()==0)
                                    {
                                        sanPham.setMANV(Integer.parseInt(manv));
                                    }else {
                                        sanPham.setMANV(nhanVien.getMaNV());
                                    }

                                    if (maloaisp == 2 || maloaisp == 3 || maloaisp == 4)
                                    {
                                        sanPham.setMATHUONGHIEU(mathuonghieu);
                                    }
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

                                    boolean kiemtra =prensenterLogicThemSanPham.ThemSanPham(convertImage1,convertImage2,convertImage3,sanPham,sanPham.getThongSoKyThuatList());
                                    if(kiemtra)
                                    {
                                        Toasty.success(ThemSanPhamActivity.this, "Thêm sản phẩm thành công !", Toast.LENGTH_SHORT,true).show();
                                        thongSoKyThuatViewModel.xoaThongSo();
                                        Intent iQuanLyTaiKhoan = new Intent(ThemSanPhamActivity.this, QuanLyTaiKhoanActivity.class);
                                        startActivity(iQuanLyTaiKhoan);
                                    }else {
                                        Toasty.error(ThemSanPhamActivity.this, "Thêm sản phẩm thất bại !", Toast.LENGTH_SHORT,true).show();
                                    }
                                }
                            }
                        });

                    }
                }
                break;
            case R.id.btnThemSanPham:
                Intent iLoaiSanPham = new Intent(this,DanhSachLoaiSanPhamActivity.class);
                iLoaiSanPham.putExtra("checkThemSanPham",0);
                startActivity(iLoaiSanPham);
                break;
            case R.id.lnThongSoKyThuatThemSanPham:
                Intent intent1 = new Intent(this,ThongSoKyThuatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sanpham",sanPham);
               // intent1.putExtra("")
                startActivity(intent1);
                break;
        }
    }

    public void showDialogForImage(int vitrianh){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn ảnh");
        final String[] LuaChon = {"Chọn ảnh mới","Chọn ảnh có sẵn"};
        builder.setItems(LuaChon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        switch (vitrianh)
                        {
                            case 1:
                                CAMERA = 4;
                                chooseCamera();
                                break;
                            case 2:
                                CAMERA = 5;
                                chooseCamera();
                                break;
                            case 3:
                                CAMERA = 6;
                                chooseCamera();
                                break;
                        }
                        break;
                    case 1:
                        switch (vitrianh)
                        {
                            case 1:
                                GALLERY =1;
                                choosePhotoFromGallary();
                                break;
                            case 2:
                                GALLERY = 2;
                                choosePhotoFromGallary();
                                break;
                            case 3:
                                GALLERY = 3;
                                choosePhotoFromGallary();
                                break;
                        }
                        break;
                }
            }
        });
        AlertDialog dialog3 = builder.create();
        dialog3.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void chooseCamera(){
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA);
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
                        imgThemSP1.setImageBitmap(bitmap1);
                        imgThemSP2.setEnabled(true);
                        break;
                    case 2:
                        try {
                            bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgThemSP2.setImageBitmap(bitmap2);
                        imgThemSP3.setEnabled(true);
                        break;
                    case 3:
                        try {
                            bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentURI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgThemSP3.setImageBitmap(bitmap3);
                        break;
                }
            }
        }
        else if(requestCode==CAMERA)
        {
            switch (CAMERA)
            {
                case 4:
                    bitmap1 = (Bitmap) data.getExtras().get("data");
                    imgThemSP1.setImageBitmap(bitmap1);
                    break;
                case 5:
                    bitmap2 = (Bitmap) data.getExtras().get("data");
                    imgThemSP2.setImageBitmap(bitmap2);
                    break;
                case 6:
                    bitmap3 = (Bitmap) data.getExtras().get("data");
                    imgThemSP3.setImageBitmap(bitmap3);
                    break;
            }
        }
    }
}