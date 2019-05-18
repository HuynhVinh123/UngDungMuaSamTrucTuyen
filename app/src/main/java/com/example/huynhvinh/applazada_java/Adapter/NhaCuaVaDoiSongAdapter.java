package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NhaCuaVaDoiSongAdapter extends RecyclerView.Adapter<NhaCuaVaDoiSongAdapter.ViewHolder> {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    List<String> listAnh;

    public NhaCuaVaDoiSongAdapter(Context context, List<LoaiSanPham> loaiSanPhamList, List<String> listAnh) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        this.listAnh = listAnh;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_nhacuavadoisong,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            if(loaiSanPhamList.get(i).getTENLOAISP().equals("") || loaiSanPhamList.get(i).getTENLOAISP().equals(null))
            {
                viewHolder.txtTieuDeNhaCuaVaDoiSong.setText("Nha Cửa và đời sống");
            }
            else {
                viewHolder.txtTieuDeNhaCuaVaDoiSong.setText(loaiSanPhamList.get(i).getTENLOAISP());
            }
            Picasso.with(context).load(listAnh.get(i)).into(viewHolder.imageView);
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                    iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI",loaiSanPhamList.get(i).getMALOAISP());
                    iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI",loaiSanPhamList.get(i).getTENLOAISP());
                    iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA",false);
                    context.startActivity(iHienThiSanPhamTheoDanhMuc);
                }
            });

    }

    @Override
    public int getItemCount() {
        return loaiSanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView txtTieuDeNhaCuaVaDoiSong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imHinhSPNhaCuaVaDoiSong);
            txtTieuDeNhaCuaVaDoiSong = (TextView) itemView.findViewById(R.id.txtTieuDeNhaCuaVaDoiSong);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnNhaCuaVaDoiSong);
        }
    }
}