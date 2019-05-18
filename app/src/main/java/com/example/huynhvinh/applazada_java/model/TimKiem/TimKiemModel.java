package com.example.huynhvinh.applazada_java.model.TimKiem;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TimKiemModel {
    public List<SanPham> LayDanhSachSanPhamTheoMaLoai(String  tensp, String tenmang, String tenham, int limit){
        List<SanPham> sanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan =IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham",tenham);

        HashMap<String,String> hashMaLoai = new HashMap<>();
        hashMaLoai.put("tensp", tensp);

        HashMap<String,String> hashLimit = new HashMap<>();
        hashLimit.put("limit", String.valueOf(limit));

        attrs.add(hashHam);
        attrs.add(hashMaLoai);
        attrs.add(hashLimit);

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
                SanPham sanPham = new SanPham();
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));
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
}
