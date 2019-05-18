package com.example.huynhvinh.applazada_java.view.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huynhvinh.applazada_java.Adapter.DienTuAdapter;
import com.example.huynhvinh.applazada_java.Adapter.LoGoThuongHieuLonDienTuAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TopDienThoaiDienTuAdapter;
import com.example.huynhvinh.applazada_java.Adapter.TrangChuTopDienThoaiVaMayTinhBangAdapter;
import com.example.huynhvinh.applazada_java.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.huynhvinh.applazada_java.QuangCao.QuangCaoDienTu;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DienTu;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView,recyclerViewLogoThuongHieuLon,recyclerHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    FlipperLayout flipperDienTu,flipperDienTu0;
    ImageView imgQuanCaoDt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDienTu);
        recyclerViewLogoThuongHieuLon = (RecyclerView) view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recyclerHangMoiVe = (RecyclerView) view.findViewById(R.id.recylerHangMoiVe);
      //  flipperDienTu = (FlipperLayout) view.findViewById(R.id.flipperDienTu);
        flipperDienTu0 = (FlipperLayout) view.findViewById(R.id.flipperDienTu0);
        imgQuanCaoDt = (ImageView) view.findViewById(R.id.imgQuangCaoDT);

        setLayout();

        dienTuList = new ArrayList<>();

        presenterLogicDienTu = new PresenterLogicDienTu(this);
        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    private void setLayout() {

        Picasso.with(getContext()).load(QuangCaoDienTu.HinhOne).into(imgQuanCaoDt);

        String url[] = new String[]{QuangCaoDienTu.HinhOne,QuangCaoDienTu.HinhTwo,QuangCaoDienTu.HinhThree};

        for(int i=0; i<3;i++)
        {
            FlipperView view = new FlipperView(getContext());
            view.setImageUrl(url[i]);
           // flipperDienTu.addFlipperView(view);
            flipperDienTu0.addFlipperView(view);
        }
    }

    @Override
    public void HienThiSachDienTu(List<DienTu> dienTuList) {

        this.dienTuList = dienTuList;

        DienTuAdapter dienTuAdapter = new DienTuAdapter(getActivity(),dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dienTuAdapter);
        dienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList) {
        LoGoThuongHieuLonDienTuAdapter adapter = new LoGoThuongHieuLonDienTuAdapter(getActivity(),thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        recyclerViewLogoThuongHieuLon.setLayoutManager(layoutManager);
        recyclerViewLogoThuongHieuLon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhamList) {
        TrangChuTopDienThoaiVaMayTinhBangAdapter topDienThoaiDienTuAdapter = new TrangChuTopDienThoaiVaMayTinhBangAdapter(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerHangMoiVe.setLayoutManager(layoutManager);
        recyclerHangMoiVe.setAdapter(topDienThoaiDienTuAdapter);
        topDienThoaiDienTuAdapter.notifyDataSetChanged();
    }
}
