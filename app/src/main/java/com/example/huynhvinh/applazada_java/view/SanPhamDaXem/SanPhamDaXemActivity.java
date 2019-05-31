package com.example.huynhvinh.applazada_java.view.SanPhamDaXem;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.SanPhamDaXemAdapter;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;
import com.example.huynhvinh.applazada_java.model.Room.viewmodel.SanPhamViewModel;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;

import java.util.List;

public class SanPhamDaXemActivity  extends AppCompatActivity {

    SanPhamViewModel sanPhamViewModel;
    SanPhamDaXemAdapter sanPhamDaXemAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamdaxem);

        recyclerView = (RecyclerView) findViewById(R.id.recySanPhamYeuThich);
        toolbar = (Toolbar) findViewById(R.id.toolbarSPYeuThich);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Sản phẩm đã xem");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Add sản phẩm vào danh sách sản phẩm đã xem
        sanPhamViewModel = ViewModelProviders.of(this).get(SanPhamViewModel.class);
        sanPhamViewModel.layDanhSachSanPham().observe(this, new Observer<List<SanPham_Room>>() {
            @Override
            public void onChanged(@Nullable List<SanPham_Room> sanPham_rooms) {
                    sanPhamDaXemAdapter = new SanPhamDaXemAdapter(SanPhamDaXemActivity.this,R.layout.custom_layout_sanphamyeuthich,sanPham_rooms);
                    recyclerView.setAdapter(sanPhamDaXemAdapter);
                    sanPhamDaXemAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itDelete:
                sanPhamViewModel.xoaSanPham();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}