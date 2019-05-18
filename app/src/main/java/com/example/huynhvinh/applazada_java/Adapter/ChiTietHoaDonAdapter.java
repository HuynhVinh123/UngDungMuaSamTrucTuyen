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

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.view.ChitietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHolder> {

    Context context;
    List<ChiTietHoaDon> chiTietHoaDonList;

    public ChiTietHoaDonAdapter(Context context, List<ChiTietHoaDon> chiTietHoaDonList) {
        this.context = context;
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_list_topdienthoaivamaytinhbang,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(chiTietHoaDonList.get(i).getHinhLon()).resize(120,150).into(viewHolder.imgHinhSanPham);
        viewHolder.txtTenSP.setText(chiTietHoaDonList.get(i).getTenSP());

        int giatien = chiTietHoaDonList.get(i).getGia();
        ChiTietKhuyenMai chiTietKhuyenMai = chiTietHoaDonList.get(i).getChiTietKhuyenMai();
        if(chiTietKhuyenMai!=null)
        {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if(phantramkm != 0)
            {

                @SuppressLint({"NewApi", "LocalSuppress"})
                NumberFormat formatter = new DecimalFormat("###,###");
                String gia = formatter.format(giatien);
                viewHolder.txtGiamGia.setVisibility(View.VISIBLE);
                // Thêm dấu gạch ngang
                viewHolder.txtGiamGia.setPaintFlags(viewHolder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.txtGiamGia.setText(gia + " VNĐ");

                giatien = giatien*phantramkm/100;
            }
        }

        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        String gia = formatter.format(giatien);

        viewHolder.txtGiaTien.setText(gia +" VNĐ ");
        viewHolder.cardView.setTag(chiTietHoaDonList.get(i).getMaSP());
        viewHolder.txtSoLuongMua.setText("x" + chiTietHoaDonList.get(i).getSoLuong());
        viewHolder.txtSoLuongMua.setVisibility(View.VISIBLE);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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
        return chiTietHoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia,txtSoLuongMua;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaDienTu);
            txtSoLuongMua = (TextView) itemView.findViewById(R.id.txtSoLuongSPMua);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
