package com.example.huynhvinh.applazada_java.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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

import com.example.huynhvinh.applazada_java.Presenter.GioHang.PresenterLogicSanPhamYeuThich;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamDaXemAdapter extends RecyclerView.Adapter<SanPhamDaXemAdapter.ViewHolder> {

    Context context;
    List<SanPham_Room> sanPhamList;

    int layout;

    public SanPhamDaXemAdapter(Context context, int layout, List<SanPham_Room> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public SanPhamDaXemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SanPhamDaXemAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(sanPhamList.get(position).getImage()).into(holder.imgHinhSanPham);
        holder.txtTenSP.setText(sanPhamList.get(position).getTensp());
        int giatien = sanPhamList.get(position).getGia();

        if(giatien != sanPhamList.get(position).getGiakm())
        {
            @SuppressLint({"NewApi", "LocalSuppress"})
            NumberFormat formatter = new DecimalFormat("###,###");
            String gia = formatter.format(sanPhamList.get(position).getGiakm());

            holder.txtGiaTien.setText(gia +" VNĐ");

            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giagoc = numberFormat.format(giatien);
            holder.txtGiamGia.setVisibility(View.VISIBLE);
            holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiamGia.setText(giagoc + " VNĐ");
        }
        else {
            @SuppressLint({"NewApi", "LocalSuppress"})
            NumberFormat formatter = new DecimalFormat("###,###");
            String gia = formatter.format(giatien);

            holder.txtGiaTien.setText(gia +" VNĐ");
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", sanPhamList.get(position).getMasp());
                context.startActivity(iChiTietSanPham);
            }
        });

        holder.imXoaSanPhamYeuThich.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imXoaSanPhamYeuThich;
        ImageView imgHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imXoaSanPhamYeuThich = (ImageView) itemView.findViewById(R.id.imXoaSPYeuThich);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}