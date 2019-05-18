package com.example.huynhvinh.applazada_java.model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.CodeKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GioHangModel {

    SQLiteDatabase sqLiteDatabase;

    public void MoKetNoi(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        sqLiteDatabase = dataSanPham.open();
    }

    public boolean ThemGioHang(SanPham sanPham,int phantramkhuyenmai){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP,sanPham.getMASP());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP,sanPham.getTENSP());
        if(phantramkhuyenmai!=0)
        {
            contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN,sanPham.getGIAKHUYENMAI());
        }
        else {
            contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN,sanPham.getGIA());
        }
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH,sanPham.getHinhgiohang());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,sanPham.getSOLUONG());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONGTONKHO,sanPham.getSOLUONGTONKHO());
        long id = sqLiteDatabase.insert(DataSanPham.TB_GIOHANG,null,contentValues);
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }

    public  boolean XoaSanPhamTrongGioHang(int masp)
    {
       int kiemtra =  sqLiteDatabase.delete(DataSanPham.TB_GIOHANG,DataSanPham.TB_GIOHANG_MASP + " = " + masp,null);
       if(kiemtra>0)
       {
            return true;
       }else{
           return  false;
       }
    }

    public boolean CapNhatSoLuongSPTrongGioHang(int masp,int soluong)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,soluong);

        int kiemtra = sqLiteDatabase.update(DataSanPham.TB_GIOHANG,contentValues,DataSanPham.TB_GIOHANG_MASP + " = " + masp,null);

        if(kiemtra>0)
        {
            return  true;
        }else{
            return  false;
        }

    }

    public List<SanPham> LayDanhSachSanPhamTrongGioHang()
    {
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;

        Cursor cursor = sqLiteDatabase.rawQuery(truyvan,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);

            sanPhamList.add(sanPham);

            cursor.moveToNext();
        }

        return  sanPhamList;
    }

    public CodeKhuyenMai KiemTraMaCodeKhuyenMai(String macode){
        CodeKhuyenMai codeKhuyenMai = new CodeKhuyenMai();

        List<HashMap<String,String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String duongdan =IPConnect.IP ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraMaCodeKhuyenMai");

        HashMap<String,String> hsMaCode = new HashMap<>();
        hsMaCode.put("macode",macode);

        attrs.add(hsHam);
        attrs.add(hsMaCode);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int sotiengiam = object.getInt("sotiengiam");
            int sotientoithieu = object.getInt("sotientoithieu");
            codeKhuyenMai.setGiaTriDonHangMin(sotientoithieu);
            codeKhuyenMai.setSoTienGiam(sotiengiam);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return codeKhuyenMai;
    }

}
