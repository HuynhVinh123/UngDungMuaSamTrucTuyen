package com.example.huynhvinh.applazada_java.model.QuanLyTaiKhoan;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuanLyTaiKhoanModel {

    public NhanVien LayThongTinNhanVien(String manv){
        NhanVien nhanVien = new NhanVien();

        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan =IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayThongTinNhanVienBangMaNV");

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("manv", manv);


        attrs.add(hashHam);
        attrs.add(hashMaNV);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhGia = jsonObject.getJSONArray("THONGTINNHANVIEN");
            int dem = jsonArrayDanhGia.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayDanhGia.getJSONObject(i);
                nhanVien.setMaNV(object.getInt("MANV"));
                nhanVien.setTenNV(object.getString("TENNV"));
                nhanVien.setDiaChi(object.getString("DIACHI"));
                nhanVien.setNgaySinh(object.getString("NGAYSINH"));
                nhanVien.setSoDT(object.getString("SODT"));
                nhanVien.setTenDN(object.getString("TENDN"));
                nhanVien.setGioiTinh(object.getInt("GIOITINH"));
                nhanVien.setUyTin(object.getInt("UYTIN"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  nhanVien;
    }

    public NhanVien LayThongTinNhanVienBangId(String id){
        NhanVien nhanVien = new NhanVien();

        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayThongTinNhanVienBangIdNV");

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("id", id);


        attrs.add(hashHam);
        attrs.add(hashMaNV);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhGia = jsonObject.getJSONArray("THONGTINNHANVIEN");
            int dem = jsonArrayDanhGia.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayDanhGia.getJSONObject(i);
                nhanVien.setMaNV(object.getInt("MANV"));
                nhanVien.setTenNV(object.getString("TENNV"));
                nhanVien.setDiaChi(object.getString("DIACHI"));
                nhanVien.setNgaySinh(object.getString("NGAYSINH"));
                nhanVien.setSoDT(object.getString("SODT"));
                nhanVien.setTenDN(object.getString("TENDN"));
                nhanVien.setGioiTinh(object.getInt("GIOITINH"));
                nhanVien.setUyTin(object.getInt("UYTIN"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  nhanVien;
    }

    public  boolean CapNhatThongTinNhanVienBangMaNV(NhanVien nhanVien){
        boolean kiemTra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        // Lấy dữ liệu bằng phương thức POST
        String duongdan =  IPConnect.IP ;

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("manv",String.valueOf(nhanVien.getMaNV()));

        HashMap<String,String> hashTenNV = new HashMap<>();
        hashTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hashDiaChi = new HashMap<>();
        hashDiaChi.put("diachi",nhanVien.getDiaChi());

        HashMap<String,String> hashNgaySinh = new HashMap<>();
        hashNgaySinh.put("ngaysinh",nhanVien.getNgaySinh());

        HashMap<String,String> hashSoDT = new HashMap<>();
        hashSoDT.put("sodt",nhanVien.getSoDT());

        HashMap<String,String> hashGioiTinh = new HashMap<>();
        hashGioiTinh.put("gioitinh",String.valueOf(nhanVien.getGioiTinh()));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","CapNhatThongTinNhanVien");

        attrs.add(hashTenNV);
        attrs.add(hashMaNV);
        attrs.add(hashDiaChi);
        attrs.add(hashNgaySinh);
        attrs.add(hashSoDT);
        attrs.add(hashGioiTinh);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.trim().equals("true"))
            {

                kiemTra =true;

            }
            else {
                kiemTra = false;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return  kiemTra;
    }

    public boolean CapNhatThongTinNhanVienBangIdNV(NhanVien nhanVien){
        boolean kiemTra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        // Lấy dữ liệu bằng phương thức POST
        String duongdan =IPConnect.IP;

        HashMap<String,String> hashId = new HashMap<>();
        hashId.put("id",nhanVien.getIdDangNhap());

        HashMap<String,String> hashTenNV = new HashMap<>();
        hashTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hashDiaChi = new HashMap<>();
        hashDiaChi.put("diachi",nhanVien.getDiaChi());

        HashMap<String,String> hashNgaySinh = new HashMap<>();
        hashNgaySinh.put("ngaysinh",nhanVien.getNgaySinh());

        HashMap<String,String> hashSoDT = new HashMap<>();
        hashSoDT.put("sodt",nhanVien.getSoDT());

        HashMap<String,String> hashGioiTinh = new HashMap<>();
        hashGioiTinh.put("gioitinh",String.valueOf(nhanVien.getGioiTinh()));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","CapNhatThongTinNhanvienBangId");

        attrs.add(hashTenNV);
        attrs.add(hashId);
        attrs.add(hashDiaChi);
        attrs.add(hashNgaySinh);
        attrs.add(hashSoDT);
        attrs.add(hashGioiTinh);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.trim().equals("true"))
            {

                kiemTra =true;

            }
            else {
                kiemTra = false;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return  kiemTra;
    }

}
