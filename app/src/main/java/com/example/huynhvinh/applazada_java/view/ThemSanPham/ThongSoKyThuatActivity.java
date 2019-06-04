package com.example.huynhvinh.applazada_java.view.ThemSanPham;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThongSo;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;
import com.example.huynhvinh.applazada_java.model.Room.viewmodel.ThongSoKyThuatViewModel;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ThongSoKyThuatActivity extends AppCompatActivity  implements View.OnClickListener {

    Toolbar toolbar;
    EditText edt_BoNhoTrong,edt_KheSim,edt_ChongThamNuoc,edt_KichThuoc,edt_HeDieuHanh,edt_RAM,edt_CameraSau,edt_CameraTruoc,edt_Blutooth,edt_Pin,edt_BaoHanh;
    TextView txt_BoNhoTrong,txt_KheCamSim,txt_ChongThamNuoc,txt_KichThuoc,txt_HeDieuHanh,txt_RAM,txt_CameraSau,txt_CameraTruoc,txt_Blutooth,txt_Pin,txt_BaoHanh;
    ThongSoKyThuatViewModel thongSoKyThuatViewModel;
    FButton btn_XacNhan;
    private List<ThongSo> thongSoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongsokythuat);

        txt_RAM = (TextView) findViewById(R.id.txt_RAM);
        txt_Pin = (TextView) findViewById(R.id.txt_Pin);
        txt_KichThuoc = (TextView) findViewById(R.id.txt_KichThuoc);
        txt_KheCamSim = (TextView) findViewById(R.id.txt_KheCamSim);
        txt_HeDieuHanh = (TextView) findViewById(R.id.txt_HeDieuHanh);
        txt_ChongThamNuoc = (TextView) findViewById(R.id.txt_ChongThamNuoc);
        txt_CameraTruoc = (TextView) findViewById(R.id.txt_CameraTruoc);
        txt_CameraSau = (TextView) findViewById(R.id.txt_CameraSau);
        txt_BoNhoTrong = (TextView) findViewById(R.id.txt_BoNhoTrong);
        txt_Blutooth = (TextView) findViewById(R.id.txt_Blutooth);
        txt_BaoHanh = (TextView) findViewById(R.id.txt_BaoHanh);
        btn_XacNhan = (FButton) findViewById(R.id.btn_XacNhan);
        edt_Pin = (EditText) findViewById(R.id.edt_Pin);
        edt_RAM = (EditText) findViewById(R.id.edt_RAM);
        edt_KichThuoc = (EditText) findViewById(R.id.edt_KichThuoc);
        edt_KheSim = (EditText) findViewById(R.id.edt_KheSim);
        edt_HeDieuHanh = (EditText) findViewById(R.id.edt_HeDieuHanh);
        edt_ChongThamNuoc = (EditText) findViewById(R.id.edt_ChongThamNuoc);
        edt_CameraTruoc = (EditText) findViewById(R.id.edt_CameraTruoc);
        edt_CameraSau = (EditText) findViewById(R.id.edt_CameraSau);
        edt_BoNhoTrong = (EditText) findViewById(R.id.edt_BoNhoTrong);
        edt_Blutooth = (EditText) findViewById(R.id.edt_Blutooth);
        edt_BaoHanh = (EditText) findViewById(R.id.edt_BaoHanh);
        toolbar = (Toolbar) findViewById(R.id.toolbarThongSoKyThuat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông số kỹ thuật");

        thongSoKyThuatViewModel = ViewModelProviders.of(this).get(ThongSoKyThuatViewModel.class);

        btn_XacNhan.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if(edt_BoNhoTrong.getText().toString().equals("") || edt_CameraSau.getText().toString().equals("") || edt_CameraTruoc.getText().toString().equals("") || edt_ChongThamNuoc.getText().toString().equals("") || edt_HeDieuHanh.getText().toString().equals("") || edt_KheSim.getText().toString().equals("") || edt_KichThuoc.getText().toString().equals("") || edt_Pin.getText().toString().equals("") || edt_RAM.getText().toString().equals("") || edt_Blutooth.getText().toString().equals("") || edt_BaoHanh.getText().toString().equals(""))
        {
            Toasty.warning(this, "Bạn phải điền đầy đủ tất cả các thông sô !",Toasty.LENGTH_SHORT,true);
        }
        else {
            ThongSoKyThuat thongSo = new ThongSoKyThuat();
            thongSo.setTenchitiet(txt_BaoHanh.getText().toString());
            thongSo.setGiatri(edt_BaoHanh.getText().toString() + " tháng");
            thongSoKyThuatViewModel.themThongSo(thongSo);

            ThongSoKyThuat thongSo1 = new ThongSoKyThuat();
            thongSo1.setTenchitiet(txt_Blutooth.getText().toString());
            thongSo1.setGiatri(edt_Blutooth.getText().toString());
            thongSoKyThuatViewModel.themThongSo(thongSo1);

            ThongSoKyThuat thongSo2 = new ThongSoKyThuat();
            thongSo2.setTenchitiet(txt_RAM.getText().toString());
            thongSo2.setGiatri(edt_RAM.getText().toString() + " GB");
            thongSoKyThuatViewModel.themThongSo(thongSo2);

            ThongSoKyThuat thongSo3 = new ThongSoKyThuat();
            thongSo3.setTenchitiet(txt_Pin.getText().toString());
            thongSo3.setGiatri(edt_Pin.getText().toString() + " mAh");
            thongSoKyThuatViewModel.themThongSo(thongSo3);

            ThongSoKyThuat thongSo4 = new ThongSoKyThuat();
            thongSo4.setTenchitiet(txt_KichThuoc.getText().toString());
            thongSo4.setGiatri(edt_KichThuoc.getText().toString() + " Inches");
            thongSoKyThuatViewModel.themThongSo(thongSo4);

            ThongSoKyThuat thongSo5 = new ThongSoKyThuat();
            thongSo5.setTenchitiet(txt_KheCamSim.getText().toString());
            thongSo5.setGiatri(edt_KheSim.getText().toString());
            thongSoKyThuatViewModel.themThongSo(thongSo5);

            ThongSoKyThuat thongSo6 = new ThongSoKyThuat();
            thongSo6.setTenchitiet(txt_HeDieuHanh.getText().toString());
            thongSo6.setGiatri(edt_HeDieuHanh.getText().toString());
            thongSoKyThuatViewModel.themThongSo(thongSo6);

            ThongSoKyThuat thongSo7 = new ThongSoKyThuat();
            thongSo7.setTenchitiet(txt_ChongThamNuoc.getText().toString());
            thongSo7.setGiatri(edt_ChongThamNuoc.getText().toString());
            thongSoKyThuatViewModel.themThongSo(thongSo7);

            ThongSoKyThuat thongSo8 = new ThongSoKyThuat();
            thongSo8.setTenchitiet(txt_CameraTruoc.getText().toString());
            thongSo8.setGiatri(edt_CameraTruoc.getText().toString() + " MP");
            thongSoKyThuatViewModel.themThongSo(thongSo8);

            ThongSoKyThuat thongSo9 = new ThongSoKyThuat();
            thongSo9.setTenchitiet(txt_CameraSau.getText().toString());
            thongSo9.setGiatri(edt_CameraSau.getText().toString() + " MP");
            thongSoKyThuatViewModel.themThongSo(thongSo9);

            ThongSoKyThuat thongSo10 = new ThongSoKyThuat();
            thongSo10.setTenchitiet(txt_BoNhoTrong.getText().toString());
            thongSo10.setGiatri(edt_BoNhoTrong.getText().toString() + " GB");
            thongSoKyThuatViewModel.themThongSo(thongSo10);
        }

        onBackPressed();
    }
}
