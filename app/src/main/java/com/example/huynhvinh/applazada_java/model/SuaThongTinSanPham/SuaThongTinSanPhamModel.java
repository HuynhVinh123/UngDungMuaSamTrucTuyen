package com.example.huynhvinh.applazada_java.model.SuaThongTinSanPham;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SuaThongTinSanPhamModel {

    public boolean CapNhatThongTinSanPham(SanPham sanPham)
    {

        String dulieuJSON;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP;

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp",String.valueOf(sanPham.getMASP()));

        HashMap<String,String> hashTenSP = new HashMap<>();
        hashTenSP.put("tensp",sanPham.getTENSP());

        HashMap<String,String> hashGia = new HashMap<>();
        hashGia.put("gia",String.valueOf(sanPham.getGIA()));

        HashMap<String,String> hashThongTin = new HashMap<>();
        hashThongTin.put("thongtin",sanPham.getTHONGTIN());

        HashMap<String,String> hashSoLuong = new HashMap<>();
        hashSoLuong.put("soluong",String.valueOf(sanPham.getSOLUONG()));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","CapNhatSanPhamDaDang");

        attrs.add(hashMaSP);
        attrs.add(hashTenSP);
        attrs.add(hashGia);
        attrs.add(hashThongTin);
        attrs.add(hashSoLuong);
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
