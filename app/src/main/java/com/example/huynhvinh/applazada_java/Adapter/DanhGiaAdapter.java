package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;

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
        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            txtDuocDanhGiaBoi = (TextView) itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia = (TextView) itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia = (TextView) itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNgayDanhGia = (TextView) itemView.findViewById(R.id.txtNgayDanhGia);
            rbDanhGia = (RatingBar) itemView.findViewById(R.id.rbDanhGia);
        }
    }
}
