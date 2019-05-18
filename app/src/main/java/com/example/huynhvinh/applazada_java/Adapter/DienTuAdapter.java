package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.QuangCao.QuangCaoDienTu;
import com.example.huynhvinh.applazada_java.QuangCao.QuangCaoPhuKien;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DienTu;
import com.squareup.picasso.Picasso;

import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class DienTuAdapter extends RecyclerView.Adapter<DienTuAdapter.ViewHolder> {

    Context context;
    List<DienTu> dienTuList;

    public DienTuAdapter(Context context, List<DienTu> dienTuList) {
            this.dienTuList = dienTuList;
            this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recyclerview_dientu,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTieuDeSanPhamNoiBat.setText(dienTuList.get(position).getTennoibat());
        holder.txtTopSanPhamNoiBat.setText(dienTuList.get(position).getTentopnoibat());


        DienTu dienTu = dienTuList.get(position);
        if(position == 0) // kiểm tra nếu sản phẩm là của Điện thoại và máy tính bảng thì ta ẩn banner
        {
            holder.imageView.setVisibility(View.GONE);
        }else {
            Picasso.with(context).load(QuangCaoDienTu.HinhOne).into(holder.imageView);
        }
        // Show ra list các thương hiệu lớn RecyclerView Thương hiệu lớn
        ThuongHieuLonAdapter thuongHieuLonAdapter = new ThuongHieuLonAdapter(context,dienTu.getThuongHieus(),dienTu.isThuonghieu());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false);
        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(thuongHieuLonAdapter);
        thuongHieuLonAdapter.notifyDataSetChanged();

        // Show ra TOP list các sản phẩm và máy tình bảng RecyclerView TopDienThoaiVaMayTinhBang
        TrangChuTopDienThoaiVaMayTinhBangAdapter topDienThoaiDienTuAdapter = new TrangChuTopDienThoaiVaMayTinhBangAdapter(context,R.layout.custom_layout_topdienthoaivamaytinhbang,dienTu.getSanPhams());
        RecyclerView.LayoutManager layoutManagerTopSP = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerViewTopSanPham.setLayoutManager(layoutManagerTopSP);
        holder.recyclerViewTopSanPham.setAdapter(topDienThoaiDienTuAdapter);
        topDienThoaiDienTuAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        RecyclerView recyclerViewThuongHieuLon,recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat,txtTopSanPhamNoiBat;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerViewThuongHieuLon = (RecyclerView) itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPham = (RecyclerView) itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            txtTieuDeSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTopSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);
            imageView = (ImageView) itemView.findViewById(R.id.imKhuyenMaiDienTu);
        }
    }


}
