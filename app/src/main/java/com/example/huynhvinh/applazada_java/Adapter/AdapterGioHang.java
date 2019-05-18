package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Presenter.GioHang.PresenterLogicGioHang;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.GioHang.GioHangModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGiohang> {

    Context context;
    List<SanPham> sanPhamList ;
    GioHangModel gioHangModel;
    PresenterLogicGioHang presenterLogicGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList, PresenterLogicGioHang presenterLogicGioHang) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        gioHangModel = new GioHangModel();
        this.presenterLogicGioHang = presenterLogicGioHang;
    }

    @NonNull
    @Override
    public ViewHolderGiohang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang,viewGroup,false);

        ViewHolderGiohang viewHolderGiohang = new ViewHolderGiohang(view);

        return viewHolderGiohang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderGiohang holder, final int i) {

        final SanPham sanPham = sanPhamList.get(i);

        holder.txtTenTieuDenGioHang.setText(sanPham.getTENSP());

        holder.txtSoLuongSP.setText(sanPham.getSOLUONG() + "");
        // set envent tăng số lượng SP
        holder.imTangSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int soluong = Integer.parseInt(holder.txtSoLuongSP.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();
                if(soluong<soluongtonkho) {
                    soluong++;
                }else{
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua vượt quá số lượng tồn tại trong cửa hàng chúng tôi!", Toast.LENGTH_SHORT).show();
                }
                gioHangModel.MoKetNoi(context);
                gioHangModel.CapNhatSoLuongSPTrongGioHang(sanPham.getMASP(),soluong);

                holder.txtSoLuongSP.setText(String.valueOf(soluong));

                presenterLogicGioHang.CapNhatTongTienGioHang(context);

            }
        });

        holder.imGiamSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSP.getText().toString());
                if(soluong>1) {
                    soluong--;
                }
                gioHangModel.MoKetNoi(context);
                gioHangModel.CapNhatSoLuongSPTrongGioHang(sanPham.getMASP(),soluong);

                holder.txtSoLuongSP.setText(String.valueOf(soluong));
                presenterLogicGioHang.CapNhatTongTienGioHang(context);
            }
        });

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTienGioHang.setText(gia + " VNĐ");

        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(sanPham.getHinhgiohang(),0,sanPham.getHinhgiohang().length);

        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaSanPhamGioHang.setTag(sanPham.getMASP());

        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangModel gioHangModel = new GioHangModel();
                gioHangModel.MoKetNoi(context);
                boolean kiemtra = gioHangModel.XoaSanPhamTrongGioHang((int)v.getTag());
                if(kiemtra)
                {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
                sanPhamList.remove(i);
                notifyDataSetChanged();
                presenterLogicGioHang.CapNhatTongTienGioHang(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGiohang extends RecyclerView.ViewHolder {

        TextView txtTenTieuDenGioHang,txtGiaTienGioHang,txtSoLuongSP;
        ImageView imHinhGioHang,imXoaSanPhamGioHang;
        ImageButton imTangSoLuongSPGioHang, imGiamSoLuongSPGioHang;

        public ViewHolderGiohang(@NonNull View itemView) {
            super(itemView);

            txtTenTieuDenGioHang = (TextView) itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang = (TextView) itemView.findViewById(R.id.txtGiaGioHang);
            imHinhGioHang = (ImageView) itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = (ImageView) itemView.findViewById(R.id.imXoaSanPhamGioHang);
            txtSoLuongSP = (TextView) itemView.findViewById(R.id.txtSoLuongSanPham);
            imGiamSoLuongSPGioHang = (ImageButton) itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSPGioHang = (ImageButton) itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
        }
    }
}
