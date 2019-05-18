package com.example.huynhvinh.applazada_java.view.GioHang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.AdapterGioHang;
import com.example.huynhvinh.applazada_java.Presenter.GioHang.PresenterLogicGioHang;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.CodeKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.DangNhap.DangNhapActivity;
import com.example.huynhvinh.applazada_java.view.ThanhToan.ThanhToanActivity;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    LinearLayout lnGiamGia;
    RecyclerView recyGioHang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbarGioHang;
    Button btnMuaNgay,btnGuiMaCode;
    RecyclerView.LayoutManager layoutManager;
    List<SanPham> sanPhams = new ArrayList<>();
    TextView txtTongTien,txtSoTienTamTinh,txtSoTienGiam;
    EditText edtMaCode;
    String gia;
    int TongTien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_list_giohang);

        lnGiamGia = (LinearLayout) findViewById(R.id.lnGiamGia);
        txtSoTienGiam = (TextView) findViewById(R.id.txtSoTienGiam);
        edtMaCode = (EditText) findViewById(R.id.edtMaCode);
        txtSoTienTamTinh = (TextView) findViewById(R.id.txtSoTienTamTinh);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        recyGioHang = (RecyclerView) findViewById(R.id.recyclerGioHang);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);
        btnGuiMaCode = (Button) findViewById(R.id.btnGuiMaCode);
        toolbarGioHang = (Toolbar) findViewById(R.id.toolbarGioHang);
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Danh sách sản phẩm của bạn");

        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);
        presenterLogicGioHang.CapNhatTongTienGioHang(this);


        btnMuaNgay.setOnClickListener(this);
        btnGuiMaCode.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        this.sanPhams = sanPhamList;
        layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,sanPhamList,presenterLogicGioHang);
        recyGioHang.setLayoutManager(layoutManager);
        recyGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void CapNhatTongTienSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        TongTien = 0;
        for(int i=0; i<sanPhamList.size();i++)
        {
            int GiaTien = sanPhamList.get(i).getGIA();
            int SoLuong = sanPhamList.get(i).getSOLUONG();
            int Tong = GiaTien*SoLuong;
            TongTien += Tong;
        }

        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        gia = formatter.format(TongTien);
        txtSoTienTamTinh.setText(gia + " VNĐ");
        txtTongTien.setText(gia + " VNĐ");

    }

    @Override
    public void KhongCoSanPham()
    {
        txtSoTienTamTinh.setText("0 VNĐ");
        txtTongTien.setText("0 VNĐ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id  = item.getItemId();
        switch (id)
        {
            case  android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id)
        {
            case  R.id.btnMuaNgay:
                DangNhapModel dangNhapModel = new DangNhapModel();
                String tennv =  dangNhapModel.LayCachedDangNhap(this);
                if(!tennv.trim().equals(""))
                {
                    if(sanPhams.size()>0) {
                        Intent iThanhToan = new Intent(this, ThanhToanActivity.class);
                        iThanhToan.putExtra("sotien",TongTien);
                        startActivity(iThanhToan);

                    }
                    else {
                        Toast.makeText(this, "Bạn chưa có sản phẩm nào trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.btnGuiMaCode:
                String macode = edtMaCode.getText().toString();
                if(!macode.trim().equals(""))
                {
                    CodeKhuyenMai codeKhuyenMai = presenterLogicGioHang.KiemTraMaCodeKhuyenMai(macode);
                    if(codeKhuyenMai!=null)
                    {
                        if(codeKhuyenMai.getSoTienGiam()!=0)
                        {
                            if(TongTien>codeKhuyenMai.getGiaTriDonHangMin())
                            {
                                Toast.makeText(this, "Nhập mã thành công !", Toast.LENGTH_SHORT).show();
                                @SuppressLint({"NewApi", "LocalSuppress"})
                                NumberFormat formatter = new DecimalFormat("###,###");
                                String tiengiam = formatter.format(codeKhuyenMai.getSoTienGiam());
                                txtSoTienGiam.setText(tiengiam + " VNĐ");
                                lnGiamGia.setVisibility(View.VISIBLE);

                                // set lại cho textview tổng tiền

                                TongTien-= codeKhuyenMai.getSoTienGiam();
                                @SuppressLint({"NewApi", "LocalSuppress"})
                                NumberFormat formatter1 = new DecimalFormat("###,###");
                                String gia = formatter.format(TongTien);
                                txtTongTien.setText(gia + " VNĐ");
                            }
                            else {
                                Toast.makeText(this, "Hóa đơn của bạn tối thiểu phải " + codeKhuyenMai.getGiaTriDonHangMin() + " VNĐ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else {
                    Toast.makeText(this, "Bạn chưa nhập mã code !", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}