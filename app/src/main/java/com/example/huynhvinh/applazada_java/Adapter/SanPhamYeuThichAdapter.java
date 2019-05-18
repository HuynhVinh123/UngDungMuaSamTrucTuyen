package com.example.huynhvinh.applazada_java.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;

import java.util.List;

public class SanPhamYeuThichAdapter extends RecyclerView.Adapter<SanPhamYeuThichAdapter.ViewHolder> {

    Context context;
    List<SanPham> sanPhamList;
    PresenterLogicSanPhamYeuThich presenterLogicSanPhamYeuThich;
    int layout;

    public SanPhamYeuThichAdapter(Context context, int layout, List<SanPham> sanPhamList, PresenterLogicSanPhamYeuThich presenterLogicSanPhamYeuThich) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
        this.presenterLogicSanPhamYeuThich = presenterLogicSanPhamYeuThich;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Bitmap bitmapSPYeuThich = BitmapFactory.decodeByteArray(sanPhamList.get(position).getHinhgiohang(),0,sanPhamList.get(position).getHinhgiohang().length);
        holder.imgHinhSanPham.setImageBitmap(bitmapSPYeuThich);
        holder.txtTenSP.setText(sanPhamList.get(position).getTENSP());
        int giatien = sanPhamList.get(position).getGIA();

        ChiTietKhuyenMai chiTietKhuyenMai =  sanPhamList.get(position).getChiTietKhuyenMai();
        if(chiTietKhuyenMai!=null)
        {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if(phantramkm != 0)
            {

                @SuppressLint({"NewApi", "LocalSuppress"})
                NumberFormat formatter = new DecimalFormat("###,###");
                String gia = formatter.format(giatien);
                holder.txtGiamGia.setVisibility(View.VISIBLE);
                // Thêm dấu gạch ngang
                holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtGiamGia.setText(gia + " VNĐ");

                giatien = giatien*phantramkm/100;
            }
        }
        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        String gia = formatter.format(giatien);

        holder.txtGiaTien.setText(gia +" VNĐ");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", sanPhamList.get(position).getMASP());
                context.startActivity(iChiTietSanPham);
            }
        });

        holder.imXoaSanPhamYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterLogicSanPhamYeuThich.XoaSanPhamYeuThichTheoMaSP(sanPhamList.get(position).getMASP(),context);
            }
        });

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
