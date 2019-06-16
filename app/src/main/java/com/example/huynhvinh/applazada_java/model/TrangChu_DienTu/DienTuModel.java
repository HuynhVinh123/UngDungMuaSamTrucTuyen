package com.example.huynhvinh.applazada_java.model.TrangChu_DienTu;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DienTuModel {



    public List<SanPham> LayDanhSachSanPhamTOP(String tenham,String tenmang){
        List<SanPham> sanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham",tenham);

        attrs.add(hashHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("kiemtra",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayThuongHieu.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));
                sanPhamList.add(sanPham);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

    public List<ThuongHieu> LayDanhSachThuongHieuLon(String tenham,String tenmang){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham",tenham);

        attrs.add(hashHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayThuongHieu.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayThuongHieu.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));
                thuongHieuList.add(thuongHieu);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }

}
