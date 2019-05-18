package com.example.huynhvinh.applazada_java.view.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.DanhSachKhuyenMaiAdapter;
import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.QuangCao.QuangCaoDienTu;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.KhuyenMai;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {

    PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    RecyclerView recyclerViewDanhSachKhuyenMai;
    FlipperLayout flipperLayoutKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chuongtrinhkhuyenmai,container,false);

        recyclerViewDanhSachKhuyenMai = (RecyclerView) view.findViewById(R.id.recyclerDanhSachKhuyenMai);
        flipperLayoutKhuyenMai = (FlipperLayout) view.findViewById(R.id.flipperChuongTrinhKhuyenMai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        setLayout(khuyenMaiList);
        DanhSachKhuyenMaiAdapter sachKhuyenMaiAdapter = new DanhSachKhuyenMaiAdapter(getContext(),khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewDanhSachKhuyenMai.setLayoutManager(layoutManager);
        recyclerViewDanhSachKhuyenMai.setAdapter(sachKhuyenMaiAdapter);
        sachKhuyenMaiAdapter.notifyDataSetChanged();
    }

    private void setLayout(List<KhuyenMai> khuyenMaiList) {

        if(khuyenMaiList.size()>3)
        {
            for(int i=0; i<3;i++)
            {
                FlipperView view = new FlipperView(getContext());
                view.setImageUrl(khuyenMaiList.get(i).getHINHKHUYENMAI());
                // flipperDienTu.addFlipperView(view);
                flipperLayoutKhuyenMai.addFlipperView(view);
            }
        }else{
            for(int i=0; i<khuyenMaiList.size();i++)
            {
                FlipperView view = new FlipperView(getContext());
                view.setImageUrl(khuyenMaiList.get(i).getHINHKHUYENMAI());
                flipperLayoutKhuyenMai.addFlipperView(view);
            }
        }
    }
}
