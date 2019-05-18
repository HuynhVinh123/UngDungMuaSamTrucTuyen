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

public class TrangChuTopDienThoaiVaMayTinhBangAdapter extends RecyclerView.Adapter<TrangChuTopDienThoaiVaMayTinhBangAdapter.ViewHoler> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public TrangChuTopDienThoaiVaMayTinhBangAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,viewGroup,false);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Picasso.with(context).load(sanPhamList.get(position).getANHLON().toString()).resize(120,150).into(holder.imgHinhSanPham);
        holder.txtTenSP.setText(sanPhamList.get(position).getTENSP());

        // Xử lý khuyến mãi
        ChiTietKhuyenMai chiTietKhuyenMai = sanPhamList.get(position).getChiTietKhuyenMai();
        int giatien = sanPhamList.get(position).getGIA();

        if(chiTietKhuyenMai != null)
        {

            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if(phantramkm != 0)
            {
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

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
