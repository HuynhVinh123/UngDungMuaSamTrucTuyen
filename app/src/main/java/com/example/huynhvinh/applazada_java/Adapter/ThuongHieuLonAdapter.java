package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThuongHieuLonAdapter extends RecyclerView.Adapter<ThuongHieuLonAdapter.ViewHolder> {

    Context context;
    List<ThuongHieu> thuongHieuList;
    boolean kiemtra;

    public ThuongHieuLonAdapter(Context context, List<ThuongHieu> thuongHieuList,boolean kiemtra) {
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kiemtra = kiemtra;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_thuonghieulon,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.txtThuongHieu.setText(thuongHieuList.get(position).getTENTHUONGHIEU());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI",thuongHieuList.get(position).getMATHUONGHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI",thuongHieuList.get(position).getTENTHUONGHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA",kiemtra);
                context.startActivity(iHienThiSanPhamTheoDanhMuc);

            }
        });


        Picasso.with(context).load(thuongHieuList.get(position).getHINHTHUONGHIEU().toString()).into(holder.imgThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtThuongHieu;
        ImageView imgThuongHieu;
        LinearLayout linearLayout;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            txtThuongHieu = (TextView) itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imgThuongHieu = (ImageView) itemView.findViewById(R.id.imHinhThuongHieuLonDienTu);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnthuonghieulon);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarThuongHieuLon);
        }
    }
}
