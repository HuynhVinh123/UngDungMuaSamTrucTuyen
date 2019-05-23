package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.Presenter.ThemSanPham.EventClickExpan;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.huynhvinh.applazada_java.view.ThemSanPham.ThemSanPhamActivity;

import java.util.List;

public class ExpandLoaiSanPhamAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    ViewHolderMenu viewHolderMenu;
    EventClickExpan eventClickExpan;
    int checkThemSanPham;
    public ExpandLoaiSanPhamAdapter(Context context, List<LoaiSanPham> loaiSanPhamList, EventClickExpan eventClickExpan,int checkThemSanPham) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        this.eventClickExpan = eventClickExpan;
        this.checkThemSanPham = checkThemSanPham;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiSanPhamList.size();
        for(int i=0; i<count; i++)
        {
            int maloaisp  = loaiSanPhamList.get(i).getMALOAISP();
            loaiSanPhamList.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }

    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if(loaiSanPhamList.get(vitriGroupCha).getListCon().size() !=0) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhamList.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhamList.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhamList.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhamList.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView imageView;
    }

    @Override
    public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
        View viewGroupCha = convertView;
        if(viewGroupCha == null)
        {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = inflater.inflate(R.layout.custom_layout_hienthiloaisanpham,parent,false);
            viewHolderMenu.txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSanPham);
            viewHolderMenu.imageView = (ImageView) viewGroupCha.findViewById(R.id.imgMenu);

            viewGroupCha.setTag(viewHolderMenu);
        }
        else
        {
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhamList.get(vitriGroupCha).getTENLOAISP());

        int DemSPCon =  loaiSanPhamList.get(vitriGroupCha).getListCon().size(); //Đếm để kiểm tra group cho có cái con nào không

        // Kiểm tra nếu có SP con thì ta cho Image dấu cộng còn không thì ẩn đi
        if(DemSPCon>0)
        {
            viewHolderMenu.imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolderMenu.imageView.setVisibility(View.INVISIBLE);
        }

        if(isExpanded)  // nếu Group được xổ xuống
        {
            viewHolderMenu.imageView.setImageResource(R.drawable.ic_remove_black_24dp);
            viewHolderMenu.txtTenLoaiSP.setTextColor(context.getResources().getColor(R.color.colorBlack));

        }else{          // nếu Group không được xổ xuống
            viewHolderMenu.imageView.setImageResource(R.drawable.ic_add_black_24dp);
            viewHolderMenu.txtTenLoaiSP.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }

        int maloai = loaiSanPhamList.get(vitriGroupCha).getMALOAISP();

        if(maloai == 1 || maloai==5 || maloai == 17 || maloai == 18 || maloai==26 || maloai == 27 || maloai==33 || maloai== 42 || maloai==48|| maloai ==49|| maloai == 53 || maloai==58 || maloai==63 || maloai == 64 || maloai == 69 || maloai == 72 || maloai == 73 || maloai==77)
        {

        }else {
            viewHolderMenu.txtTenLoaiSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkThemSanPham==0) {
                    Intent intent = new Intent(context, ThemSanPhamActivity.class);
                    intent.putExtra("maloaisp",loaiSanPhamList.get(vitriGroupCha).getMALOAISP());
                    intent.putExtra("tenloaisp",loaiSanPhamList.get(vitriGroupCha).getTENLOAISP());
                    context.startActivity(intent);
                    }
                    else {
                        SharedPreferences cachedDangNhap = context.getSharedPreferences("loaisanpham",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = cachedDangNhap.edit();
                        editor.putInt("maloaisanpham",loaiSanPhamList.get(vitriGroupCha).getMALOAISP());
                        editor.putString("tenloaisanpham",loaiSanPhamList.get(vitriGroupCha).getTENLOAISP());
                        editor.commit();

                        eventClickExpan.onClick();
                    }
                }
            });
        }

        return viewGroupCha;
    }

    public class SecondExpandable extends ExpandableListView {

        public SecondExpandable(Context context) {
            super(context);
        }

        // Định lại chiều rộng và chiều cao cho ExphandableListView

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x; // lấy chiều rộng của màn hình
            int height = size.y;    // lấy chiều cao của màn hình
            //    widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public View getChildView(int vitriGroupCha, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandLoaiSanPhamAdapter secondAdapter = new ExpandLoaiSanPhamAdapter(context,loaiSanPhamList.get(vitriGroupCha).getListCon(),eventClickExpan,checkThemSanPham);
        secondExpandable.setAdapter(secondAdapter);

        secondExpandable.setGroupIndicator(null);
        notifyDataSetChanged();
        return secondExpandable;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}