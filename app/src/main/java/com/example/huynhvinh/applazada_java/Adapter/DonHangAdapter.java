package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;
import com.example.huynhvinh.applazada_java.view.ChiTietHoaDon.ChiTietHoaDonActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    Context context;
    List<HoaDon> hoaDonList;

    public DonHangAdapter(Context context, List<HoaDon> hoaDonList) {
        this.context = context;
        this.hoaDonList = hoaDonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_quanlydonhang,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtTenSPHoaDon.setText(hoaDonList.get(i).getChiTietHoaDonList().get(0).getTenSP());
        viewHolder.txtMaHoaDon.setText(hoaDonList.get(i).getMaHD()+"");
        viewHolder.txtNgayDat.setText(hoaDonList.get(i).getNgayMua());
        if(hoaDonList.get(i).getTrangThai().trim().equals("chokiemduyet")) {
            viewHolder.txtTrangThai.setText("Chờ kiểm duyệt");
        }else if(hoaDonList.get(i).getTrangThai().trim().equals("dahuy")){
            viewHolder.txtTrangThai.setText("Đã hủy");
        }else if(hoaDonList.get(i).getTrangThai().trim().equals("thanhcong"))
        {
            viewHolder.txtTrangThai.setText("Giao hàng thành công");
        }else if(hoaDonList.get(i).getTrangThai().trim().equals("dangvanchuyen"))
        {
            viewHolder.txtTrangThai.setText("Đang vận chuyển");
        }else if(hoaDonList.get(i).getTrangThai().trim().equals("dakiemduyet")){
            viewHolder.txtTrangThai.setText("Đã kiểm duyệt");
        }

        viewHolder.txtSoLuongSP.setText(hoaDonList.get(i).getChiTietHoaDonList().size()+ " sản phẩm");
        Picasso.with(context).load(hoaDonList.get(i).getChiTietHoaDonList().get(0).getHinhLon()).into(viewHolder.imgHoaDon);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietHoaDon = new Intent(context, ChiTietHoaDonActivity.class);
                iChiTietHoaDon.putExtra("mahd",hoaDonList.get(i).getMaHD());
                context.startActivity(iChiTietHoaDon)   ;
            }
        });


    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSPHoaDon,txtMaHoaDon,txtNgayDat,txtTrangThai,txtSoLuongSP;
        ImageView imgHoaDon;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = (TextView) itemView.findViewById(R.id.txtMaHoaDon);
            txtTenSPHoaDon = (TextView) itemView.findViewById(R.id.txtTenSanPhamHoaDon);
            txtNgayDat = (TextView) itemView.findViewById(R.id.txtNgayDatHangHoaDon);
            txtTrangThai = (TextView) itemView.findViewById(R.id.txtTrangThaiHoaDon);
            txtSoLuongSP = (TextView) itemView.findViewById(R.id.txtSoLuongSPHoaDon);
            imgHoaDon = (ImageView) itemView.findViewById(R.id.imgHinhQuanLyHoaDon);
            cardView = (CardView) itemView.findViewById(R.id.cardViewQLDH);
        }
    }
}
