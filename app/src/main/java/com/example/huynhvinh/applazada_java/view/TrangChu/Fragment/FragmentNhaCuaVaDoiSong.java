package com.example.huynhvinh.applazada_java.view.TrangChu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.huynhvinh.applazada_java.Adapter.NhaCuaVaDoiSongAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Presenter.NhaCuaVaDoiSong.PresenterLogicNhaCuaVaDoiSong;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewNhaCuaVaDoiSong;

import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class FragmentNhaCuaVaDoiSong extends Fragment implements ViewNhaCuaVaDoiSong {

    PresenterLogicNhaCuaVaDoiSong presenterLogicNhaCuaVaDoiSong;
    RecyclerView recyclerView,recySPBanChayNhat,recySPMoiNhat;
    FlipperLayout flipperLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_nhacuavadoisong,container,false);

        recySPMoiNhat = (RecyclerView) view.findViewById(R.id.recyclerSPMoiNhat);
        recySPBanChayNhat = (RecyclerView) view.findViewById(R.id.recyclerSPBanChayNhatNhaCuaVaDoiSong);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerNhaCuaVaDoiSong);
        flipperLayout = (FlipperLayout) view.findViewById(R.id.flipper);

        setLayout();

        presenterLogicNhaCuaVaDoiSong = new PresenterLogicNhaCuaVaDoiSong(this);
        presenterLogicNhaCuaVaDoiSong.LayDanhSachLoaiSanPham();

        return view;
    }

    private void setLayout() {

        String url[] = new String[]{"http://sieuthi247vn.com/image/cache/catalog/category/Banner-Categories-1-1-870x300.jpg","https://cf.shopee.vn/file/09ba881ec36bf762bbc31c0a3e3d8aa3","https://img.nhabanhang.com/2019/03/san-pham-doi-song-nha-cua.jpg"};

        for(int i=0; i<3;i++)
        {
            FlipperView view = new FlipperView(getContext());
            view.setImageUrl(url[i]);
            flipperLayout.addFlipperView(view);
        }
    }


    @Override
    public void HienThiDanhSachLoaiSanPhamNhaCuaVaDoiSong(List<LoaiSanPham> loaiSanPhams, List<String> anhLoaiSP) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        NhaCuaVaDoiSongAdapter songAdapter = new NhaCuaVaDoiSongAdapter(getContext(),loaiSanPhams,anhLoaiSP);
        recyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachSanPhamBanChayNhat(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        TopDienThoaiDienTuAdapter adapter = new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList);

        recySPBanChayNhat.setLayoutManager(layoutManager);
        recySPBanChayNhat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachSanPhamMoiNhat(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        TopDienThoaiDienTuAdapter adapter = new TopDienThoaiDienTuAdapter(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList);

        recySPMoiNhat.setLayoutManager(layoutManager);
        recySPMoiNhat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
