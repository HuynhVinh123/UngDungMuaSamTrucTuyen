package com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanphams = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanphams.length();
            for(int i = 0; i<count;i++)
            {
                JSONObject value = loaisanphams.getJSONObject(i);

                LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
                dataLoaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSanPham.setTENLOAISP(value.getString( "TENLOAISP"));

                loaiSanPhamList.add(dataLoaiSanPham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

    public  List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp){

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        // Lấy dữ liệu bằng phướng thức get
        /*
        String duongdan = "http://192.168.1.133:8080/weblazada/loaisanpham.php?maloaicha=0";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);*/
        // End

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha",String.valueOf(maloaisp));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachMenu");

        attrs.add(hashMaLoaiCha);
        attrs.add(hashHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  loaiSanPhamList;
    }


}