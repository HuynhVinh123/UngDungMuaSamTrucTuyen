package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
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
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DanhGiaAdapter  extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolderDanhGia> {

    Context context;
    List<DanhGia> danhGiaList;
    int limit;
    int layout;

    public DanhGiaAdapter(Context context,List<DanhGia> danhGiaList, int limit,int layout) {
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDanhGia holder, int position) {
        

        DanhGia danhGia = danhGiaList.get(position);

        holder.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.txtDuocDanhGiaBoi.setText(danhGia.getTENTHIETBI());
        holder.rbDanhGia.setRating(danhGia.getSOSAO());
        holder.txtNgayDanhGia.setText(danhGia.getNGAYDANHGIA());

        String[] linkAnh = danhGia.getHINHDANHGIA().split(",");
        Log.d("kiemtrasoluong",linkAnh.length + "" + linkAnh[0]);
        if(linkAnh.length ==1 )
        {
            if(linkAnh[0] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[0]).into(holder.imgBinhLuan1);
                holder.imgBinhLuan1.setVisibility(View.VISIBLE);
                holder.imgBinhLuan2.setVisibility(View.VISIBLE);
                holder.imgBinhLuan3.setVisibility(View.VISIBLE);
            }
        }else if(linkAnh.length == 2)
        {
            holder.imgBinhLuan1.setVisibility(View.VISIBLE);
            holder.imgBinhLuan2.setVisibility(View.VISIBLE);
            holder.imgBinhLuan3.setVisibility(View.VISIBLE);
            if(linkAnh[0] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[0]).into(holder.imgBinhLuan1);

            }
            else if(linkAnh[1] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[1]).into(holder.imgBinhLuan2);
                holder.imgBinhLuan2.setVisibility(View.VISIBLE);
            }
        }else if(linkAnh.length == 3)
        {
            holder.imgBinhLuan1.setVisibility(View.VISIBLE);
            holder.imgBinhLuan2.setVisibility(View.VISIBLE);
            holder.imgBinhLuan3.setVisibility(View.VISIBLE);
            if(linkAnh[0] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[0]).into(holder.imgBinhLuan1);

            }
            if(linkAnh[1] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[1]).into(holder.imgBinhLuan2);

            }

            if(linkAnh[2] != "")
            {
                Picasso.with(context).load(TrangChuActivity.SERER + linkAnh[2]).into(holder.imgBinhLuan3);

            }
        }
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }else{
            if(limit !=0){
                dong = limit;
            }else{
                dong = danhGiaList.size();
            }
        }
        return dong;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txtTieuDeDanhGia,txtNoiDungDanhGia,txtDuocDanhGiaBoi,txtNgayDanhGia;
        RatingBar rbDanhGia;
        ImageView imgBinhLuan1,imgBinhLuan2,imgBinhLuan3;
        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            txtDuocDanhGiaBoi = (TextView) itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia = (TextView) itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia = (TextView) itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNgayDanhGia = (TextView) itemView.findViewById(R.id.txtNgayDanhGia);
            rbDanhGia = (RatingBar) itemView.findViewById(R.id.rbDanhGia);
            imgBinhLuan1 = (ImageView) itemView.findViewById(R.id.imgBinhLuan1);
            imgBinhLuan2 = (ImageView) itemView.findViewById(R.id.imgBinhLuan2);
            imgBinhLuan3 = (ImageView) itemView.findViewById(R.id.imgBinhLuan3);
        }
    }
}
