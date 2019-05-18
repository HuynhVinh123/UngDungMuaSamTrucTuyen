package com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.huynhvinh.applazada_java.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ILoadMore;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoadMoreScroll;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.GioHang.GioHangActivity;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import java.util.List;

public class FragmentHienThiSanPhamTheoDanhMuc extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener, ILoadMore {
    PresenterLogicHienThiSanPhamTheoDanhMuc presenterLogicHienThiSanPhamTheoDanhMuc;
    RecyclerView recyclerHienThiSanPhamTheoDanhMuc,recyclerHienThiSanPhamTheoDanhMucChonLoc;
    ImageButton btnThaydoiTrangThaiRecycler;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    TopDienThoaiDienTuAdapter dienTuAdapter;
    ProgressBar progressBar;
    Toolbar toolbar;
    int MaSP;
    boolean KiemTra,onPause = false; // Variable để kiểm tra xem sản phẩm cần lấy là của các thương hiệu lớn hay là phụ kiện
    List<SanPham> sanPhamList1;
    TextView txtGioHang,txtChonLoc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc,container,false);
        setHasOptionsMenu(false);
        txtChonLoc = (TextView) view.findViewById(R.id.txtChonLoc);
        recyclerHienThiSanPhamTheoDanhMuc = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        recyclerHienThiSanPhamTheoDanhMucChonLoc = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMucChonLoc);
        btnThaydoiTrangThaiRecycler = (ImageButton) view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        MaSP = bundle.getInt("MALOAI",-1);
        String TenSP = bundle.getString("TENLOAI");
        KiemTra = bundle.getBoolean("KIEMTRA",false);

        presenterLogicHienThiSanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
            presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(MaSP,KiemTra);




        btnThaydoiTrangThaiRecycler.setOnClickListener(this);
        txtChonLoc.setOnClickListener(this);

        toolbar.setTitle(TenSP);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity",FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);
//        txtChonLoc = (TextView) findViewById(R.id.txtChonLoc);
//        recyclerHienThiSanPhamTheoDanhMuc = (RecyclerView) findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
//        recyclerHienThiSanPhamTheoDanhMucChonLoc = (RecyclerView) findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMucChonLoc);
//        btnThaydoiTrangThaiRecycler = (ImageButton) findViewById(R.id.btnThayDoiTrangThaiRecycler);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//
//        Intent intent = getIntent();
//        MaSP = intent.getIntExtra("MALOAI",-1);
//        String TenSP = intent.getStringExtra("TENLOAI");
//        KiemTra = intent.getBooleanExtra("KIEMTRA",false);
//
//        presenterLogicHienThiSanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
//        presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(MaSP,KiemTra);
//
//        btnThaydoiTrangThaiRecycler.setOnClickListener(this);
//        txtChonLoc.setOnClickListener(this);
//
//        toolbar.setTitle(TenSP);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {

        this.sanPhamList1 = sanPhamList;

        dienTuAdapter = new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList1);

        if(dangGrid)
        {
            layoutManager = new GridLayoutManager(getContext(),2);
            dienTuAdapter = new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList1);
        }
        else {
            layoutManager = new LinearLayoutManager(getContext());
            dienTuAdapter=  new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList1);
        }

        recyclerHienThiSanPhamTheoDanhMuc.setLayoutManager(layoutManager);
        recyclerHienThiSanPhamTheoDanhMuc.setAdapter(dienTuAdapter);
        recyclerHienThiSanPhamTheoDanhMuc.addOnScrollListener(new LoadMoreScroll(layoutManager,this));// thực hiện việc loadmore cho sp sau khi scroll
        dienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void HienThiDanhSachTatCaCacSanPhamMoi(List<SanPham> sanPhamList) {
        layoutManager = new LinearLayoutManager(getContext());
        dienTuAdapter=  new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList);
        recyclerHienThiSanPhamTheoDanhMuc.setLayoutManager(layoutManager);
        recyclerHienThiSanPhamTheoDanhMuc.setAdapter(dienTuAdapter);
        dienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menutrangchu,menu);
        // Ẩn thanh menu đăng nhập và các item chức năng
        MenuItem itemFunction = menu.findItem(R.id.itemFunction);
        itemFunction.setVisible(false);


        // Show số lượng sản phẩm trên giỏ hàng
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);

        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(getContext(), GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(getContext())));

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menutrangchu,menu);
//
//        // Ẩn thanh menu đăng nhập và các item chức năng
//        MenuItem itemFunction = menu.findItem(R.id.itemFunction);
//        itemFunction.setVisible(false);
//
//
//        // Show số lượng sản phẩm trên giỏ hàng
//        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
//
//        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
//
//        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iGioHang = new Intent(FragmentHienThiSanPhamTheoDanhMuc.this, GioHangActivity.class);
//                startActivity(iGioHang);
//            }
//        });
//
//        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
//
//        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
//
//        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            case R.id.itHome:
                Intent iTrangChu = new Intent(getContext(), TrangChuActivity.class);
                startActivity(iTrangChu);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnThayDoiTrangThaiRecycler:
                dangGrid = !dangGrid;
                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(MaSP,KiemTra);
                break;
            case R.id.txtChonLoc:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Sắp xếp:");

                final String[] LuaChon = {"Hàng mới","Bán chạy","Giá thấp","Giá cao"};
                int checkItem = 0;
                builder.setSingleChoiceItems(LuaChon, checkItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachTatCaSanPhamTheoMaLoai(MaSP,KiemTra,0);
                                dialog.dismiss();
                                txtChonLoc.setText(LuaChon[0]);
                                break;
                            case 1:
                                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachTatCaSanPhamTheoMaLoai(MaSP,KiemTra,1);
                                dialog.dismiss();
                                txtChonLoc.setText(LuaChon[1]);
                                break;
                            case 2:
                                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachTatCaSanPhamTheoMaLoai(MaSP,KiemTra,2);
                                dialog.dismiss();
                                txtChonLoc.setText(LuaChon[2]);
                                break;
                            case 3:
                                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachTatCaSanPhamTheoMaLoai(MaSP,KiemTra,3);
                                dialog.dismiss();
                                txtChonLoc.setText(LuaChon[3]);
                                break;

                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
        }
    }

    @Override
    public void LoadMore(int tongitem) {

        List<SanPham> listSanPhamLoadMore = presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(MaSP, KiemTra, tongitem, progressBar); // tongsoitem là số lượng item đã đc ta get từ database từ lúc ta truy vấn csdl
        sanPhamList1.addAll(listSanPhamLoadMore);

        dienTuAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(onPause == true) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(getContext())));
        }
    }
}
