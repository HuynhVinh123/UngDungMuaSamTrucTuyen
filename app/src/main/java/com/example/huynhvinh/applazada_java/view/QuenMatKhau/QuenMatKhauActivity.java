package com.example.huynhvinh.applazada_java.view.QuenMatKhau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Presenter.QuenMatKhau.PresenterLogicQuenMatKhau;
import com.example.huynhvinh.applazada_java.R;

import es.dmoral.toasty.Toasty;

public class QuenMatKhauActivity extends AppCompatActivity implements View.OnClickListener,ViewQuenMatKhau {

    PresenterLogicQuenMatKhau presenterLogicQuenMatKhau;
    EditText edtEmailDN;
    Button btnLayLaiMatKhau;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);

        toolbar = (Toolbar) findViewById(R.id.toolbarQuenMatKhau);
        edtEmailDN = (EditText) findViewById(R.id.edtEmailQuenMatKhau);
        btnLayLaiMatKhau = (Button) findViewById(R.id.btnLayLaiMatKhau);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Quên mật khẩu");

        btnLayLaiMatKhau.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        String Email = edtEmailDN.getText().toString();
        if(!Email.trim().equals(""))
        {
            boolean kiemtra  = Patterns.EMAIL_ADDRESS.matcher(Email).matches();
            if(kiemtra)
            {
                presenterLogicQuenMatKhau = new PresenterLogicQuenMatKhau(this::EmailHopLe);
                presenterLogicQuenMatKhau.KiemTraEMail(Email);
            }else {
                Toasty.warning(this, "Bạn nhập chưa đúng email !", Toast.LENGTH_SHORT,true).show();
            }
        }
        else {
            Toasty.warning(this, "Bạn không được để trống !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void EmailHopLe(int manv) {
        if(manv!=0) {
            Intent iThongTinMatKhau = new Intent(this, ThongTinMatKhauActivity.class);
            iThongTinMatKhau.putExtra("manv", manv);
            startActivity(iThongTinMatKhau);
        }
    }
}
