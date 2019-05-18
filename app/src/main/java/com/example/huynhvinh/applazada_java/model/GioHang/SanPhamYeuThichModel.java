package com.example.huynhvinh.applazada_java.model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamYeuThichModel{

    SQLiteDatabase sqLiteDatabase;

    public SanPhamYeuThichModel() {
    }

    public void MoKetNoi(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        sqLiteDatabase =  dataSanPham.open();
    }

    public boolean ThemSanPhamYeuThich(SanPham sanPham,int phantramkm){

        boolean kiemtra ;

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_YEUTHICH_MASP,sanPham.getMASP());
        contentValues.put(DataSanPham.TB_YEUTHICH_TENSP,sanPham.getTENSP());
        contentValues.put(DataSanPham.TB_YEUTHICH_HINHANH,sanPham.getHinhgiohang());
        contentValues.put(DataSanPham.TB_YEUTHICH_GIATIEN,sanPham.getGIA());
        contentValues.put(DataSanPham.TB_YEUTHICH_PHANTRAM,phantramkm);

        long id = sqLiteDatabase.insert(DataSanPham.TB_YEUTHICH,null,contentValues);

        if (id>0)
        {
            kiemtra = true;
        }else{
            kiemtra = false;
        }
        return  kiemtra;
    }

    public boolean XoaSanPhamTheoMaSanPham(int masp){

        boolean checkDelete;

        int kiemtra = sqLiteDatabase.delete(DataSanPham.TB_YEUTHICH,DataSanPham.TB_YEUTHICH_MASP + " = " + masp,null);
        if(kiemtra >0)
        {
            checkDelete = true;
        }else{
            checkDelete = false;
        }
        return  checkDelete;
    }

    public List<SanPham> LayDanhSachSanPhamYeuThich(){

        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_YEUTHICH;

        Cursor cursor = sqLiteDatabase.rawQuery(truyvan,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_YEUTHICH_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_YEUTHICH_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_YEUTHICH_GIATIEN));
            int phantramkm = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_YEUTHICH_PHANTRAM));
            byte[] hinhsp = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_YEUTHICH_HINHANH));

            ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
            chiTietKhuyenMai.setPHANTRAMKM(phantramkm);

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhsp);
            sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }

        return  sanPhamList;
    }
}
