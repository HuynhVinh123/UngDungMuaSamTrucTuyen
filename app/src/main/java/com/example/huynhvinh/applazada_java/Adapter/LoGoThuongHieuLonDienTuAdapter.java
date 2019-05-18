package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LoGoThuongHieuLonDienTuAdapter extends RecyclerView.Adapter<LoGoThuongHieuLonDienTuAdapter.ViewHolder> {


    Context context;
    List<ThuongHieu> thuongHieuList;
    public LoGoThuongHieuLonDienTuAdapter(Context context, List<ThuongHieu> thuongHieuList) {
            this.thuongHieuList = thuongHieuList;
            this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recycler_logo_topthuonghieulon_dientu,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(thuongHieuList.get(position).getHINHTHUONGHIEU().toString()).into(holder.imgLogoTopThuonHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogoTopThuonHieuLon;
        public ViewHolder(View itemView) {
            super(itemView);
            imgLogoTopThuonHieuLon = (ImageView) itemView.findViewById(R.id.imgLogoTopThuonHieuLon);
        }
    }
}
