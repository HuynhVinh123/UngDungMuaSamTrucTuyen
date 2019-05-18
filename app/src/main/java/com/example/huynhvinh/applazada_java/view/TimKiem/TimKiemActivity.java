package com.example.huynhvinh.applazada_java.view.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TrangChuTopDienThoaiVaMayTinhBangAdapter;
import com.example.huynhvinh.applazada_java.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ILoadMore;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoadMoreScroll;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore, SearchView.OnQueryTextListener {


    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;
    SearchView editsearch;

    SearchView searchView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);


        searchView = (SearchView) findViewById(R.id.search);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        editsearch.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerTimKiem);


        presenterLogicTimKiem = new PresenterLogicTimKiem(this);

        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TopDienThoaiDienTuAdapter trangChuTopDienThoaiVaMayTinhBangAdapter = new TopDienThoaiDienTuAdapter(this,R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trangChuTopDienThoaiVaMayTinhBangAdapter);
        //recyclerView.addOnScrollListener(new LoadMoreScroll(new LoadMoreScroll(layoutManager,this)));
        trangChuTopDienThoaiVaMayTinhBangAdapter.notifyDataSetChanged();


    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSanPham(s,0);
        return false;
    }
}
