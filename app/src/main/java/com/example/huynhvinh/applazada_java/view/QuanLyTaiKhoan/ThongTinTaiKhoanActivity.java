package com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import es.dmoral.toasty.Toasty;

public class ThongTinTaiKhoanActivity extends AppCompatActivity implements ViewThongTinTaiKhoan, View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Toolbar toolbarThongTinTaiKhoan;
    PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    DangNhapModel dangNhapModel;
    EditText edtHoTenNV,edtTenDN,edtNgaySinhDN,edtDiaChi,edtPhone;
    RadioButton rdbNam,rdbNu;
    TextInputLayout textInput_HoTen,textInput_Email,textInput_Phone,textInput_DiaChi;
    FButton btnLuuThayDoi;
    private  int manv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtintaikhoan);

        btnLuuThayDoi = (FButton) findViewById(R.id.btnLuuThayDoi);
        rdbNu = (RadioButton) findViewById(R.id.rdbNu);
        rdbNam = (RadioButton) findViewById(R.id.rdbNam);
        edtNgaySinhDN = (EditText) findViewById(R.id.edtNgaySinhDN);
        edtTenDN = (EditText) findViewById(R.id.edtEmailDN);
        edtHoTenNV = (EditText) findViewById(R.id.edtHoTenNV);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        textInput_HoTen = (TextInputLayout) findViewById(R.id.textInput_Hoten);
        textInput_Email = (TextInputLayout) findViewById(R.id.textInput_Email);
        textInput_Phone = (TextInputLayout) findViewById(R.id.textInput_Phone);
        textInput_DiaChi = (TextInputLayout) findViewById(R.id.textInput_DiaChi);
        toolbarThongTinTaiKhoan = (Toolbar) findViewById(R.id.toolbarThongTinTaiKhoan);
        setSupportActionBar(toolbarThongTinTaiKhoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dangNhapModel = new DangNhapModel();
        String manv = dangNhapModel.LayCacheMaNVDangNhap(this);

        String keyCheck = dangNhapModel.LayCacheKiemTraDangNhap(this);

        presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan(this);

        if(keyCheck=="3") {
            presenterLogicQuanLyTaiKhoan.LayThongTinNhanVien(manv);
        }else {
            presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienBangIdNV(manv);
        }

        edtHoTenNV.setOnFocusChangeListener(this);
        edtPhone.setOnFocusChangeListener(this);
        edtDiaChi.setOnFocusChangeListener(this);
        edtTenDN.setOnFocusChangeListener(this);
        edtNgaySinhDN.setOnFocusChangeListener(this);
        edtNgaySinhDN.setOnClickListener(this);

        btnLuuThayDoi.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void HienThiThongTinNguoiDung(NhanVien nhanVien) {

        manv = nhanVien.getMaNV();

        edtHoTenNV.setText(nhanVien.getTenNV());
        edtTenDN.setText(nhanVien.getTenDN());
        if(nhanVien.getNgaySinh().trim().equals("null"))
        {
            edtNgaySinhDN.setText("");
        }else {
            edtNgaySinhDN.setText(nhanVien.getNgaySinh());
        }

       int gioitinh = nhanVien.getGioiTinh();

       if (gioitinh==0)
       {
           rdbNam.setChecked(true);
       }else if (gioitinh==1){
           rdbNu.setChecked(true);
       }

       if (nhanVien.getDiaChi().trim().equals("null"))
       {
           edtDiaChi.setText("");
       }else{
           edtDiaChi.setText(nhanVien.getDiaChi());
       }


       if (nhanVien.getSoDT().trim().equals("null"))
       {
           edtPhone.setText("");
       }else{
           edtPhone.setText(nhanVien.getSoDT());
       }
    }

    @Override
    public void CapNhatThanhCong() {
        Toasty.success(this, "Cập nhật thành công!", Toast.LENGTH_SHORT,true).show();
        Intent iQuanLyTK = new Intent(this,QuanLyTaiKhoanActivity.class);
        startActivity(iQuanLyTK);
    }

    @Override
    public void CapNhatThatBai() {
        Toasty.error(this, "Cập nhật thất bại!", Toast.LENGTH_SHORT,true).show();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edtHoTenNV:
                if(!hasFocus)
                {
                    if(edtHoTenNV.getText().equals("") || edtHoTenNV.getText().equals(null))
                    {
                        textInput_HoTen.setErrorEnabled(true);
                        textInput_HoTen.setError("Bạn chưa nhập mục này!");
                    }else{
                        textInput_HoTen.setErrorEnabled(false);
                        textInput_HoTen.setError("");
                    }
                }
                break;
            case R.id.edtEmailDN:
                if(!hasFocus){
                    String chuoi = edtTenDN.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        textInput_Email.setErrorEnabled(true);
                        textInput_Email.setError("Bạn chưa nhập mục này !");
                    }else{
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches(); // check Email
                        if(!kiemtraemail){
                            textInput_Email.setErrorEnabled(true);
                            textInput_Email.setError("Đây không phải là địa chỉ Email !");
                        }else{
                            textInput_Email.setErrorEnabled(false);
                            textInput_Email.setError("");
                        }
                    }
                }
                break;
            case R.id.edtPhone:
                if(!hasFocus){
                    String chuoi = edtPhone.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        textInput_Phone.setErrorEnabled(true);
                        textInput_Phone.setError("Bạn chưa nhập mục này !");

                    }else{
                        textInput_Phone.setErrorEnabled(false);
                        textInput_Phone.setError("");
                    }
                }
                break;
            case R.id.edtDiaChi:

                if(!hasFocus)
                {
                    if(edtDiaChi.getText().equals("") || edtDiaChi.getText().equals(null))
                    {
                        textInput_DiaChi.setErrorEnabled(true);
                        textInput_DiaChi.setError("Bạn chưa nhập mục này!");
                    }else{
                        textInput_DiaChi.setErrorEnabled(false);
                        textInput_DiaChi.setError("");
                    }
                }
                break;
            case R.id.edtNgaySinhDN:
                if(hasFocus) {
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.show(getFragmentManager(), "");
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.edtNgaySinhDN:

                Calendar calendar =Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(),"");
                break;
            case R.id.btnLuuThayDoi:

                String tennv = edtHoTenNV.getText().toString();
                String email = edtTenDN.getText().toString();
                String sodt = edtPhone.getText().toString();
                String diachi = edtDiaChi.getText().toString();
                String ngaysinh = edtNgaySinhDN.getText().toString();
                int gioitinh;
                if(rdbNam.isChecked())
                {
                    gioitinh = 0;
                }else {
                    gioitinh = 1;
                }
                if(tennv.trim().equals("") || email.trim().equals("") || sodt.trim().equals("") || diachi.trim().equals("") || ngaysinh.trim().equals(""))
                {
                    Toasty.warning(this, "Bạn chưa nhập đầy đủ các thông tin!", Toast.LENGTH_SHORT,true).show();
                }else {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(manv);
                    nhanVien.setTenNV(tennv);
                    nhanVien.setDiaChi(diachi);
                    nhanVien.setNgaySinh(ngaysinh);
                    nhanVien.setSoDT(sodt);
                    nhanVien.setGioiTinh(gioitinh);

                    presenterLogicQuanLyTaiKhoan.CapNhatThongTinNhanVienBangMaNV(nhanVien);
                }

                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            String date = dayOfMonth + "-" + monthOfYear + "-" + year;
            edtNgaySinhDN.setText(date);
    }
}