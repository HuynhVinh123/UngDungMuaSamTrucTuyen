package com.example.huynhvinh.applazada_java.model.QuanLyHoaDon;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuanLyHoaDonModel {

    public List<HoaDon> LayDanhSachHoaDon(String manguoinhan){
        List<HoaDon> hoaDonList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachHoaDon");

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("manguoinhan",manguoinhan);

        attrs.add(hashHam);
        attrs.add(hashMaSP);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayHoaDon = jsonObject.getJSONArray("DANHSACHHOADON");
            int dem = jsonArrayHoaDon.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayHoaDon.getJSONObject(i);
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(object.getInt("MAHD"));
                hoaDon.setNgayMua(object.getString("NGAYMUA"));
                hoaDon.setTrangThai(object.getString("TRANGTHAI"));
                hoaDon.setTenNguoiNhan(object.getString("TENNGUOINHAN"));
                hoaDon.setSoDT(object.getString("SODT"));
                hoaDon.setDiaChi(object.getString("DIACHI"));
                hoaDon.setEmail(object.getString("EMAIL"));
                hoaDon.setSoTien(object.getInt("SOTIEN"));
                hoaDon.setMaNguoiNhan(object.getString("MANGUOINHAN"));

                hoaDonList.add(hoaDon);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  hoaDonList;
    }

    public List<ChiTietHoaDon> LayDanhSachSPChiTietHoaDon(int mahd){
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LaySanPhamChiTietHoaDon");

        HashMap<String,String> hashMaHD = new HashMap<>();
        hashMaHD.put("mahd",String.valueOf(mahd));

        attrs.add(hashHam);
        attrs.add(hashMaHD);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayHoaDon = jsonObject.getJSONArray("DANHSACHSANPHAMCHITIETHOADON");
            int dem = jsonArrayHoaDon.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayHoaDon.getJSONObject(i);
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaSP(object.getInt("MASP"));
                chiTietHoaDon.setSoLuong(object.getInt("SOLUONG"));
                chiTietHoaDon.setTenSP(object.getString("TENSP"));
                chiTietHoaDon.setGia(object.getInt("GIA"));
                chiTietHoaDon.setHinhLon(object.getString("HINHLON"));


                chiTietHoaDonList.add(chiTietHoaDon);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  chiTietHoaDonList;
    }

    public List<HoaDon> LayDanhSachHoaDonTheoTrangThai(String trangthai,String manguoinhan){
        List<HoaDon> hoaDonList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachHoaDonTheoTrangThai");

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("trangthai",trangthai);

        HashMap<String,String> hashMaNguoiNhan = new HashMap<>();
        hashMaNguoiNhan.put("manguoinhan",manguoinhan);

        attrs.add(hashHam);
        attrs.add(hashMaSP);
        attrs.add(hashMaNguoiNhan);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayHoaDon = jsonObject.getJSONArray("DANHSACHHOADON");
            int dem = jsonArrayHoaDon.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayHoaDon.getJSONObject(i);
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(object.getInt("MAHD"));
                hoaDon.setNgayMua(object.getString("NGAYMUA"));
                hoaDon.setTrangThai(object.getString("TRANGTHAI"));
                hoaDon.setTenNguoiNhan(object.getString("TENNGUOINHAN"));
                hoaDon.setSoDT(object.getString("SODT"));
                hoaDon.setDiaChi(object.getString("DIACHI"));
                hoaDon.setEmail(object.getString("EMAIL"));
                hoaDon.setSoTien(object.getInt("SOTIEN"));
                hoaDon.setMaNguoiNhan(object.getString("MANGUOINHAN"));

                hoaDonList.add(hoaDon);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  hoaDonList;
    }

    public boolean HuyDonHang(int mahd){
        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = true;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashid = new HashMap<>();
        hashid.put("mahd",String.valueOf(mahd));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","HuyDonHang");

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

    public int KiemTraHoaDonCoTrongThoiGianKM(String ngaymua,int masp){

        int phanTramKhuyenMai =0;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan =IPConnect.IP;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraHoaDonCoKhuyenMai");

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(masp));

        HashMap<String,String> hsNgayMua = new HashMap<>();
        hsNgayMua.put("ngaymua",ngaymua);

        attrs.add(hsHam);
        attrs.add(hsMaSP);
        attrs.add(hsNgayMua);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int khuyenmai = object.getInt("ketqua");
            if(khuyenmai > 0)
            {
                phanTramKhuyenMai = khuyenmai;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  phanTramKhuyenMai;

    }

}
