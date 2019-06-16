package com.example.huynhvinh.applazada_java.view.QuenMatKhau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.QuenMatKhau.PresenterLogicQuenMatKhau;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.view.DangNhap.DangNhapActivity;

public class ThongTinMatKhauActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextInputEditText edtMatKhau,edtNhapLaiMatKhau;
    FButton btnDoiMatKhau;
    PresenterLogicQuenMatKhau presenterLogicQuenMatKhau;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doimatkhau);

        btnDoiMatKhau = (FButton) findViewById(R.id.btnDoiMatKhau);
        edtNhapLaiMatKhau = (TextInputEditText) findViewById(R.id.edtNhapLaiMatKhauDoiMatKhau);
        edtMatKhau = (TextInputEditText) findViewById(R.id.edtMatKhauDoiMatKhau);
        toolbar = (Toolbar) findViewById(R.id.toolbarDoiMatKhau);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quên mật khẩu");

        btnDoiMatKhau.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        String matkhau = edtMatKhau.getText().toString();
        String nhaplaimatkhau = edtNhapLaiMatKhau.getText().toString();
        if(nhaplaimatkhau.trim().equals("") || !nhaplaimatkhau.trim().equals(matkhau))
        {
            Toast.makeText(this, "Dữ liệu không hợp lệ !", Toast.LENGTH_SHORT).show();
        }else {
            int manv = getIntent().getIntExtra("manv",0);

            presenterLogicQuenMatKhau = new PresenterLogicQuenMatKhau();
            boolean ketqua =  presenterLogicQuenMatKhau.CapNhatMatKhau(nhaplaimatkhau,manv);
            if(ketqua)
            {
                Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(iDangNhap);
                Toast.makeText(this, "Đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Đổi mật khẩu thất bại !", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
