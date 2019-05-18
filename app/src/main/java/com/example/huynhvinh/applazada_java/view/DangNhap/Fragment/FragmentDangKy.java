package com.example.huynhvinh.applazada_java.view.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.DangNhap_DangKy.PresenterLogicDangKy;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.view.DangNhap.DangNhapActivity;
import com.example.huynhvinh.applazada_java.view.DangNhap.ViewDangKy;

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener {

    PresenterLogicDangKy presenterLogicDangKy;
    FButton btnDangKy;
    EditText edHoTen,edDiaChiEmail,edSoDT;
    TextInputEditText edMatKhau,edNhapLaiMatKhau;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen;
    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edDiaChiEmail;
    TextInputLayout input_edSoDT;
    RadioButton rdbNam,rdbNu;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        anhXa(view);

        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy.setOnClickListener(this);

        edHoTen.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);
        edSoDT.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);

        return view;
    }

    private void anhXa(View view) {
        btnDangKy = (FButton) view.findViewById(R.id.btnDangKy);
        edHoTen = (EditText) view.findViewById(R.id.edHoTenDK);
        edMatKhau = (TextInputEditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = (TextInputEditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailDK);
        edSoDT = (EditText) view.findViewById(R.id.edSoDT);
        sEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.sEmailDocQuyen);
        input_edHoTen = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = (TextInputLayout)view.findViewById(R.id.input_edDiaChiEmailDK);
        input_edSoDT = (TextInputLayout) view.findViewById(R.id.input_edSoDT);
        rdbNam = (RadioButton) view.findViewById(R.id.rdbNamDK);
        rdbNu = (RadioButton) view.findViewById(R.id.rdbNuDK);
    }

    @Override
    public void DangKyThanhcong() {
        Intent iDangNhap = new Intent(getActivity(), DangNhapActivity.class);
        startActivity(iDangNhap);
        Toast.makeText(getContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getContext(), "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnDangKy:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen = "";
    private  void btnDangKy(){
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String sodt = edSoDT.getText().toString();
        Log.d("kiemtra",sodt);
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();
        int gioitinh = 0;
        if(rdbNam.isChecked())
        {
            gioitinh = 0;
        }
        else if(rdbNu.isChecked())
        {
            gioitinh = 1;
        }


        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if(kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setSoDT(sodt);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);
            nhanVien.setGioiTinh(gioitinh);
            nhanVien.setIdDangNhap("0");


            boolean kiemtra = presenterLogicDangKy.KiemTraEMail(email);
            if(!kiemtra)
            {
                presenterLogicDangKy.ThucHienDangKy(nhanVien);
            }else {
                Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại !", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id)
        {
            case R.id.edHoTenDK:
                if(!hasFocus){
                    String chuoi = edHoTen.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edDiaChiEmailDK:
                if(!hasFocus){
                    String chuoi = edDiaChiEmail.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches(); // check Email
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;

            case R.id.edSoDT:
                if(!hasFocus){
                    String chuoi = edSoDT.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edSoDT.setErrorEnabled(true);
                        input_edSoDT.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edSoDT.setErrorEnabled(false);
                        input_edSoDT.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edMatKhauDK:
                if(!hasFocus){
                    String chuoi = edMatKhau.getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edMatKhau.setErrorEnabled(true);
                        input_edMatKhau.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edMatKhau.setErrorEnabled(false);
                        input_edMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edNhapLaiMatKhauDK:
                if(!hasFocus){
                    String chuoi = edNhapLaiMatKhau.getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;
        }
    }
}