package com.example.huynhvinh.applazada_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.FragmentHienThiSanPhamTheoDanhMuc;
import com.example.huynhvinh.applazada_java.view.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;
        ViewHolderMenu viewHolderMenu;
    DrawerLayout drawerLayout;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams, DrawerLayout drawerLayout){
            this.drawerLayout = drawerLayout;
            this.context = context;
            this.loaiSanPhams = loaiSanPhams;
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            int count = loaiSanPhams.size();
            for(int i=0; i<count; i++)
            {
                int maloaisp = loaiSanPhams.get(i).getMALOAISP();
                loaiSanPhams.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
            }
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if(loaiSanPhams.get(vitriGroupCha).getListCon().size() !=0) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
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
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) { // isExpanded là kiểm tra cái Group có đc sổ xuống hay không

        View viewGroupCha = convertView;

        if(viewGroupCha == null)
        {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha,parent,false);
            viewHolderMenu.txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSanPham);
            viewHolderMenu.imageView = (ImageView) viewGroupCha.findViewById(R.id.imgMenu);

            viewGroupCha.setTag(viewHolderMenu);
        }
        else
        {
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());

        int DemSPCon =  loaiSanPhams.get(vitriGroupCha).getListCon().size(); //Đếm để kiểm tra group cho có cái con nào không

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
            viewHolderMenu.imageView.setImageResource(R.drawable.minus);
            viewGroupCha.setBackgroundResource(R.color.colorGreenBackGround);
        }else{          // nếu Group không được xổ xuống
            viewHolderMenu.imageView.setImageResource(R.drawable.plusmore);
            viewGroupCha.setBackgroundResource(R.drawable.custom_color);
        }

        // set sự kiện click ở View hoặc là ở LinearLayout bên Giao diện
//        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//
//                    if(loaiSanPhams.get(vitriGroupCha).getMALOAICHA() !=0)
//                    {
//                        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        FragmentHienThiSanPhamTheoDanhMuc fragmentHienThiSanPhamTheoDanhMuc = new FragmentHienThiSanPhamTheoDanhMuc();
//
//                        Bundle bundle = new Bundle();
//                        //Intent intent = new Intent(context,FragmentHienThiSanPhamTheoDanhMuc.class);
//
//                        bundle.putInt("MALOAI",loaiSanPhams.get(vitriGroupCha).getMALOAISP());
//                        bundle.putBoolean("KIEMTRA",false);
//                        bundle.putString("TENLOAI",loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
//
//                        fragmentHienThiSanPhamTheoDanhMuc.setArguments(bundle);
//                        fragmentTransaction.addToBackStack("TrangChuActivity");
//                        fragmentTransaction.replace(R.id.content,fragmentHienThiSanPhamTheoDanhMuc);
//                        fragmentTransaction.commit();
//                        drawerLayout.closeDrawers();
//                    }
//                    //context.startActivity(intent);
//
//                    return false;
//                }
//            }
//
//        );
            viewHolderMenu.txtTenLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaiSanPhams.get(vitriGroupCha).getMALOAICHA() !=0)
                {
                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    FragmentHienThiSanPhamTheoDanhMuc fragmentHienThiSanPhamTheoDanhMuc = new FragmentHienThiSanPhamTheoDanhMuc();

                    Bundle bundle = new Bundle();
                    //Intent intent = new Intent(context,FragmentHienThiSanPhamTheoDanhMuc.class);

                    bundle.putInt("MALOAI",loaiSanPhams.get(vitriGroupCha).getMALOAISP());
                    bundle.putBoolean("KIEMTRA",false);
                    bundle.putString("TENLOAI",loaiSanPhams.get(vitriGroupCha).getTENLOAISP());

                    fragmentHienThiSanPhamTheoDanhMuc.setArguments(bundle);
                    fragmentTransaction.addToBackStack("TrangChuActivity");
                    fragmentTransaction.replace(R.id.content,fragmentHienThiSanPhamTheoDanhMuc);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawers();
                }
            }
        });
        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View convertView, ViewGroup parent) {
      //  LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     //   View viewGroupCon = layoutInflater.inflate(R.layout.custom_layout_group_con,parent,false);

//        ExpandableListView expandableListView = (ExpandableListView) viewGroupCon.findViewById(R.id.epmenuCon);
//        SecondAdapter secondAdapter = new SecondAdapter(loaiSanPhams.get(vitriGroupCha).getListCon());
        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context,loaiSanPhams.get(vitriGroupCha).getListCon(),drawerLayout);
        secondExpandable.setAdapter(secondAdapter);

        secondExpandable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpandable;
    }

    public class SecondExpandable extends ExpandableListView{

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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    // Apter cho thawng con ben trong
//    public class SecondAdapter extends BaseExpandableListAdapter{
//
//        List<LoaiSanPham> listcon;
//        public SecondAdapter(List<LoaiSanPham> listcon){
//            this.listcon = listcon;
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//            int count = listcon.size();
//            for(int i=0; i<count; i++)
//            {
//                int maloaisp = listcon.get(i).getMALOAISP();
//                listcon.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listcon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listcon.get(vitriGroupCha).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listcon.get(vitriGroupCha);
//        }
//
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listcon.get(vitriGroupCha).getListCon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listcon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listcon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha,parent,false);
//            TextView txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSanPham);
//            txtTenLoaiSP.setText(listcon.get(vitriGroupCha).getTENLOAISP());
//            return viewGroupCha;
//        }
//
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View convertView, ViewGroup parent) {
//            TextView tv = new TextView(context);
//            tv.setText(listcon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15, 5, 5, 5);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            return tv;
//        }
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }

}
