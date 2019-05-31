package com.example.huynhvinh.applazada_java.model.SanPhamDaBan;

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

public class SanPhamDaBanModel {

    public List<SanPham> LayDanhSachTatSanPhamTheoMaLoai(int  manv){

        List<SanPham> sanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachSanPhamDaDang");

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("manv", String.valueOf(manv));

        attrs.add(hashHam);
        attrs.add(hashMaNV);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACCHSANPHAM");
            int dem = jsonArrayThuongHieu.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIA"));
                sanPham.setANHLON(object.getString("HINHLON"));
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

    public  boolean XoaSanPham(int masp)
    {
        String dulieuJSON;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP;

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp",String.valueOf(masp));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","XoaSanPham");

        attrs.add(hashMaSP);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dulieuJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dulieuJSON);
            String ketqua = object.getString("ketqua");
            if(ketqua.trim().equals("true"))
            {
                kiemtra = true;
            }else {
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  kiemtra;
    }

}
