package com.example.huynhvinh.applazada_java.model.DangNhap_DangKy;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DangKyModel {

    public boolean DangKyThanhVien(NhanVien nhanVien){

        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = true;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan =IPConnect.IP;

        HashMap<String,String> hashTenNV = new HashMap<>();
        hashTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hashTenDN = new HashMap<>();
        hashTenDN.put("tendangnhap",nhanVien.getTenDN());

        HashMap<String,String> hashSoDT = new HashMap<>();
        hashSoDT.put("sodt",nhanVien.getSoDT());

        HashMap<String,String> hashMatKhau = new HashMap<>();
        hashMatKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hashMaLoaiNV = new HashMap<>();
        hashMaLoaiNV.put("maloainv", String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String,String> hashEmailDocQuyen = new HashMap<>();
        hashEmailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());

        HashMap<String,String> hashGioiTinh = new HashMap<>();
        hashGioiTinh.put("gioitinh",String.valueOf(nhanVien.getGioiTinh()));

        HashMap<String,String> hashid = new HashMap<>();
        hashid.put("id",nhanVien.getIdDangNhap());

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","DangKyThanhVien");

        attrs.add(hashTenNV);
        attrs.add(hashTenDN);
        attrs.add(hashMatKhau);
        attrs.add(hashMaLoaiNV);
        attrs.add(hashEmailDocQuyen);
        attrs.add(hashSoDT);
        attrs.add(hashHam);
        attrs.add(hashGioiTinh);
        attrs.add(hashid);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            Log.d("kiemtra",dulieuJSON);
            try {
                JSONObject jsonObject = new JSONObject(dulieuJSON);
                String ketqua = jsonObject.getString("ketqua");
                if(ketqua.equals("true"))
                {
                    kiemtra =true;
                }
                else {
                    kiemtra = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return kiemtra;
    }

    public boolean KiemTraThanhVienDaTonTai(String id){
        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = true;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan =IPConnect.IP;

        HashMap<String,String> hashid = new HashMap<>();
        hashid.put("id",id);

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","KiemTraThanhVienDaTonTai");

        attrs.add(hashid);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            try {
                JSONObject jsonObject = new JSONObject(dulieuJSON);
                String ketqua = jsonObject.getString("ketqua");
                if(ketqua.equals("true"))
                {
                    kiemtra =true;
                }
                else {
                    kiemtra = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  kiemtra;
    }

}
