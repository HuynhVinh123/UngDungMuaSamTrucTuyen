package com.example.huynhvinh.applazada_java.model.ThanhToan;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietHoaDon;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.HoaDon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThanhToanModel {

    public boolean ThemHoaDon(HoaDon hoaDon){

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemHoaDon");


        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();

        // Parse list sản phẩm về dạng chuỗi Json
        String chuoijson = "{\"DANHSACHSANPHAM\" :[ " ;
        for(int i=0; i < chiTietHoaDonList.size();i++)
        {

            chuoijson += "{";

            chuoijson += "\"masp\" : " + chiTietHoaDonList.get(i).getMaSP() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSoLuong();

            if(i==chiTietHoaDonList.size()-1) {
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }
        }

        chuoijson+="]}";

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",hoaDon.getTenNguoiNhan());

        HashMap<String,String> hsMaNguoiNhan = new HashMap<>();
        hsMaNguoiNhan.put("manguoinhan",hoaDon.getMaNguoiNhan());

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt",String.valueOf(hoaDon.getSoDT()));

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", hoaDon.getDiaChi());

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email", hoaDon.getEmail());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan","0");

        HashMap<String,String> hsSoTien = new HashMap<>();
        hsSoTien.put("sotien",String.valueOf(hoaDon.getSoTien()));

        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);
        attrs.add(hsEmail);
        attrs.add(hsSoTien);
        attrs.add(hsMaNguoiNhan);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");

            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

}
