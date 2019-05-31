package com.example.huynhvinh.applazada_java.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.Presenter.SanPhamDaBan.PresenterLogicSanPhamDaBan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;
import com.example.huynhvinh.applazada_java.view.SuaThongTinSanPham.SuaThongTinSabPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamDaBanAdapter extends RecyclerView.Adapter<SanPhamDaBanAdapter.ViewHolder> {

    Context context;
    List<SanPham> sanPhamList;
    PresenterLogicSanPhamDaBan presenterLogicSanPhamDaBan;

    public SanPhamDaBanAdapter(Context context, List<SanPham> sanPhamList, PresenterLogicSanPhamDaBan presenterLogicSanPhamDaBan) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.presenterLogicSanPhamDaBan = presenterLogicSanPhamDaBan;
    }

    @NonNull
    @Override
    public SanPhamDaBanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_sanphamdaban,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SanPhamDaBanAdapter.ViewHolder viewHolder, int i) {

        Picasso.with(context).load(sanPhamList.get(i).getANHLON()).into(viewHolder.img_sanpham);
        viewHolder.txt_tensanpham.setText(sanPhamList.get(i).getTENSP());

        int giatien = sanPhamList.get(i).getGIA();
        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        String gia = formatter.format(giatien);
        viewHolder.txt_giasanpam.setText(gia + " VNĐ");

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", sanPhamList.get(i).getMASP());
                context.startActivity(iChiTietSanPham);
            }
        });

        viewHolder.img_suasanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSuaSanPham = new Intent(context, SuaThongTinSabPhamActivity.class);
                iSuaSanPham.putExtra("masp", sanPhamList.get(i).getMASP());
                context.startActivity(iSuaSanPham);
            }
        });

        viewHolder.img_xoasanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterLogicSanPhamDaBan.XoaSanPham(sanPhamList.get(i).getMASP());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sanpham,img_suasanpham,img_xoasanpham;
        TextView txt_tensanpham,txt_giasanpam,txt_giakhuyenmai;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_xoasanpham = (ImageView) itemView.findViewById(R.id.imXoaSanPhamDaBan);
            img_suasanpham = (ImageView) itemView.findViewById(R.id.imSuaSanPham);
            img_sanpham = (ImageView) itemView.findViewById(R.id.imSanPhamDaBan);
            txt_tensanpham = (TextView) itemView.findViewById(R.id.txtTenSanPhamDaBan);
            txt_giasanpam = (TextView) itemView.findViewById(R.id.txtGiaSanPhamDaBan);
            txt_giakhuyenmai = (TextView) itemView.findViewById(R.id.txtGiamGiaSanPhamDaBan);
            cardView = (CardView) itemView.findViewById(R.id.cardViewSanPhamDaBan);

        }
    }
}
