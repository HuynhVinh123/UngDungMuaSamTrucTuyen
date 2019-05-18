package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.KhuyenMai;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewKhuyenMai;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewNhaCuaVaDoiSong;

import java.util.List;

public class DanhSachKhuyenMaiAdapter extends RecyclerView.Adapter<DanhSachKhuyenMaiAdapter.ViewHolder> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public DanhSachKhuyenMaiAdapter(Context context, List<KhuyenMai> khuyenMaiList) {
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.custom_layout_itemkhuyenmai,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        KhuyenMai khuyenMai = khuyenMaiList.get(i);
        viewHolder.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());

        TopDienThoaiDienTuAdapter topDienThoaiDienTuAdapter = new TopDienThoaiDienTuAdapter(context,R.layout.custom_layout_topdienthoaivamaytinhbang,khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        viewHolder.recyclerViewItemKhuyenMai.setLayoutManager(layoutManager);
        viewHolder.recyclerViewItemKhuyenMai.setAdapter(topDienThoaiDienTuAdapter);
        topDienThoaiDienTuAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewItemKhuyenMai;
        TextView txtTieuDeKhuyenMai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItemKhuyenMai = (RecyclerView) itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieuDeKhuyenMai = (TextView) itemView.findViewById(R.id.txtTieuDeKhuyenMai);
        }
    }
}
