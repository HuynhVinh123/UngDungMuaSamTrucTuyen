package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NhanVienDanhGiaAdapter extends RecyclerView.Adapter<NhanVienDanhGiaAdapter.ViewHolder> {

    Context context;
    List<DanhGia> danhGiaList;
    int layout;

    public NhanVienDanhGiaAdapter(Context context, List<DanhGia> danhGiaList,  int layout) {

        this.danhGiaList = danhGiaList;
        this.context = context;
        this.layout = layout;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(danhGiaList.get(i).getSanPham().getANHLON()).into(viewHolder.imgHinhSanPham);

        viewHolder.ratingBarDanhGia.setRating(danhGiaList.get(i).getSOSAO());

        viewHolder.txtTenSanPhamDanhGia.setText(danhGiaList.get(i).getSanPham().getTENSP());
        viewHolder.txtTieuDeDanhGia.setText(danhGiaList.get(i).getTIEUDE());
        viewHolder.txtNoiDungDanhGia.setText(danhGiaList.get(i).getNOIDUNG());
    }

    @Override
    public int getItemCount() {
        return danhGiaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        RatingBar ratingBarDanhGia;
        TextView txtTieuDeDanhGia,txtTenSanPhamDanhGia,txtNoiDungDanhGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imgHinhSPDanhGiaCuaNhanVien);
            ratingBarDanhGia = (RatingBar) itemView.findViewById(R.id.ratingDanhGiaCuaNhanVien);
            txtTenSanPhamDanhGia = (TextView) itemView.findViewById(R.id.txtTenSPDanhGiaCuaNhanVien);
            txtTieuDeDanhGia = (TextView) itemView.findViewById(R.id.txtTieuDeDanhGiaCuaNhanVien);
            txtNoiDungDanhGia = (TextView) itemView.findViewById(R.id.txtNoiDungDanhGiaCuaNhanvien);
        }
    }
}
