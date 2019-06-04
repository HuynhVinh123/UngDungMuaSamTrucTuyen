package com.example.huynhvinh.applazada_java.view.TrangChu;

import android.content.ContentUris;
import android.content.Intent;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.ExpandAdapter;
import com.example.huynhvinh.applazada_java.Adapter.LoGoThuongHieuLonDienTuAdapter;
import com.example.huynhvinh.applazada_java.Adapter.ViewPagerAdapter;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.huynhvinh.applazada_java.Presenter.DangNhap_DangKy.PresenterLogicDangKy;
import com.example.huynhvinh.applazada_java.Presenter.QuanLyTaiKhoan.PresenterLogicQuanLyTaiKhoan;
import com.example.huynhvinh.applazada_java.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.model.QuanLyHoaDon.QuanLyHoaDonModel;
import com.example.huynhvinh.applazada_java.view.DangNhap.DangNhapActivity;
import com.example.huynhvinh.applazada_java.view.GioHang.GioHangActivity;
import com.example.huynhvinh.applazada_java.view.QuanLyHoaDon.QuanLyHoaDonActivity;
import com.example.huynhvinh.applazada_java.view.QuanLyTaiKhoan.QuanLyTaiKhoanActivity;
import com.example.huynhvinh.applazada_java.view.SanPhamDaXem.SanPhamDaXemActivity;
import com.example.huynhvinh.applazada_java.view.SanPhamYeuThich.SanPhamYeuThichActivity;
import com.example.huynhvinh.applazada_java.view.TimKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener,ViewXuLyMenu {

    public static final String SERER = "http://192.168.1.142:8080/weblazada/webadmin";

    Button btnTimKiem;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle; // thiết kế dấu 3 gạch
    ExpandableListView expandableListView;
    DangNhapModel dangNhapModel;
    MenuItem itemDangNhap,itemDangXuat;
    Menu menu;
    TextView txtGioHang;
    boolean onPause = false;
    PresenterLogicXuLyMenu presenterLogicXuLyMenu;
    PresenterLogicQuanLyTaiKhoan presenterLogicQuanLyTaiKhoan;
    String tennguoidung,emailFaceBook,id;
    AccessToken accessToken;
    PresenterLogicDangKy presenterLogicDangKy;
    FloatingActionButton btn_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.trangchu_layout);

        anhXa();

        // Show TabLayout
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        presenterLogicDangKy = new PresenterLogicDangKy();
        // Xử lý show loại sản phẩm lên Menu
        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu(this);
        accessToken = presenterLogicXuLyMenu.LayTenNguoiDungFacebook();

        presenterLogicXuLyMenu.LayDanhSachMenu();

        presenterLogicQuanLyTaiKhoan = new PresenterLogicQuanLyTaiKhoan();

        dangNhapModel = new DangNhapModel();

        btnTimKiem.setOnClickListener(this);
        btn_Message.setOnClickListener(this);

    }

    private void anhXa() {

        btn_Message = (FloatingActionButton) findViewById(R.id.button_message);
        btnTimKiem = (Button) findViewById(R.id.btnTimKiem);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        expandableListView = (ExpandableListView) findViewById(R.id.epmenu);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        // set Drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        // thực hiện set dấu 3 gạch với ExpanListView
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menutrangchu,menu);

        this.menu = menu;
        MenuItem itemHome = menu.findItem(R.id.itHome);
        itemHome.setVisible(false);
        itemDangNhap = menu.findItem(R.id.itDangNhap);
        itemDangXuat = menu.findItem(R.id.itDangXuat);


        final String tennv = dangNhapModel.LayCachedDangNhap(this);

        if(!tennv.equals(""))
        {
            itemDangNhap.setTitle(tennv);
            itemDangXuat.setVisible(true);
        }

        // Show số lượng sản phẩm trên giỏ hàng
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);

        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);

        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));

        // Lấy tên facebook

        accessToken = presenterLogicXuLyMenu.LayTenNguoiDungFacebook();
        if(accessToken!=null)
        {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        emailFaceBook = object.getString("email");

                        boolean kiemtraNV = presenterLogicDangKy.KiemTraNVTonTai(object.getString("id"));

                        if(!kiemtraNV)
                        {
                            // Thực hiện việc đăng ký thông tin nhân viên lên bảng nhân viên
                            NhanVien nhanVien = new NhanVien();
                            nhanVien.setTenNV(tennguoidung);
                            nhanVien.setTenDN(emailFaceBook);
                            nhanVien.setIdDangNhap(object.getString("id"));
                            nhanVien.setMaLoaiNV(2);
                            boolean kiemtra = presenterLogicDangKy.DangKyByGoogkeAndFacebook(nhanVien);
                            if(kiemtra)
                            {
                                Toast.makeText(TrangChuActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(TrangChuActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }

                        NhanVien nv = presenterLogicQuanLyTaiKhoan.LayThongTinNhanVienID(object.getString("id"));

                        // Set Cache cho Sharedeference
                        dangNhapModel.CapNhatCachedDangNhap(TrangChuActivity.this,tennguoidung,emailFaceBook,object.getString("id"),nv.getDiaChi());
                        dangNhapModel.CapNhatCacheKiemTraDangNhap(TrangChuActivity.this,"1");

                        MenuItem menuItem = menu.findItem(R.id.itDangNhap);
                        menuItem.setTitle(tennguoidung);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields","name,email,id");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();

            MenuItem menuItemDangXuat = menu.findItem(R.id.itDangXuat);
            menuItemDangXuat.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Bắt sự kiền click vào DrawerTogger(Dấu 3 gạch)
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        int id = item.getItemId();
        switch (id)
        {
            case R.id.itDangNhap:
                if(dangNhapModel.LayCachedDangNhap(this).equals("") && accessToken==null) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.itDangXuat:
                if(accessToken!=null)
                {
            LoginManager.getInstance().logOut();
            dangNhapModel.CapNhatCachedDangNhap(this,"","","","");
            this.menu.clear();
            this.onCreateOptionsMenu(this.menu);
        }
            if(!dangNhapModel.LayCachedDangNhap(this).equals(""))
            {
                dangNhapModel.CapNhatCachedDangNhap(this,"","","","");
                this.menu.clear();
                this.onCreateOptionsMenu(this.menu);
            }
            break;
            case  R.id.itMongMuon:
                Intent iYeuThich = new Intent(this, SanPhamYeuThichActivity.class);
                startActivity(iYeuThich);
                break;
            case R.id.itQuanLyTaiKhoan:
                String tennvQL = dangNhapModel.LayCachedDangNhap(this);
                if(tennvQL=="" || tennvQL==null)
                {
                    Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                    startActivity(iDangNhap);
                }else {
                    Intent iQuanLyTaiKhoan = new Intent(this, QuanLyTaiKhoanActivity.class);
                    startActivity(iQuanLyTaiKhoan);
                }
                break;
            case R.id.itDonHangCuaToi:
                String tennv = dangNhapModel.LayCachedDangNhap(this);
                if(tennv=="" || tennv==null)
                {
                    Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                    startActivity(iDangNhap);
                }else {
                    Intent iQuanLyDonHang = new Intent(this, QuanLyHoaDonActivity.class);
                    startActivity(iQuanLyDonHang);
                }
                break;
            case R.id.itDaXem:
                Intent iDaXem = new Intent(this, SanPhamDaXemActivity.class);
                startActivity(iDaXem);
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnTimKiem:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
            case R.id.button_message:
                Uri uri = Uri.parse("fb-messenger://user/");
                 uri = ContentUris.withAppendedId(uri,Long.valueOf("100010434228127"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(intent);
                }
                catch(Exception e) {
                    Toast.makeText(this,"Oups!Can't open Facebook messenger right now. Please try again later.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this,loaiSanPhamList,drawerLayout);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause == true) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));
        }
    }
}