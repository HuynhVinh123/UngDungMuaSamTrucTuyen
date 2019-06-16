package com.example.huynhvinh.applazada_java.Adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopDienThoaiDienTuAdapter extends RecyclerView.Adapter<TopDienThoaiDienTuAdapter.ViewHolder> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public TopDienThoaiDienTuAdapter(Context context,int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(sanPhamList.get(position).getANHLON().toString()).into(holder.imgHinhSanPham);
        holder.txtTenSP.setText(sanPhamList.get(position).getTENSP());

        // Xử lý khuyến mãi
        ChiTietKhuyenMai chiTietKhuyenMai = sanPhamList.get(position).getChiTietKhuyenMai();
        int giatien = sanPhamList.get(position).getGIA();

        if(chiTietKhuyenMai != null)
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
        holder.cardView.setTag(sanPhamList.get(position).getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSanPham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
