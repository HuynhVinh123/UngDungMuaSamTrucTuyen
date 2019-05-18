package com.example.huynhvinh.applazada_java.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentDienTu;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentLamDep;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentMeVaBe;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentNoiBat;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentThoiTrang;
import com.example.huynhvinh.applazada_java.view.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new FragmentDienTu());
//        listFragment.add(new FragmentLamDep());
//        listFragment.add(new FragmentMeVaBe());
        listFragment.add(new FragmentNhaCuaVaDoiSong());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
//        listFragment.add(new FragmentNoiBat());
//        listFragment.add(new FragmentTheThaoVaDuLich());
//        listFragment.add(new FragmentThoiTrang());
//        listFragment.add(new FragmentThuongHieu());



          titleFragment.add("Điện tử");
//        titleFragment.add("Làm đẹp");
//        titleFragment.add("Mẹ và bé");
          titleFragment.add("Nhà cửa & đời sống");
          titleFragment.add("Chương trình khuyến mãi");
//        titleFragment.add("Nổi bật");
//        titleFragment.add("Thể thao & du lịch");
//        titleFragment.add("Thời trang");
//        titleFragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}