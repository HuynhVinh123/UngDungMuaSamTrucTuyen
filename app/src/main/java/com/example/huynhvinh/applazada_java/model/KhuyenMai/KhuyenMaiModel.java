package com.example.huynhvinh.applazada_java.model.KhuyenMai;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.KhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class KhuyenMaiModel {

    public List<KhuyenMai> LayDanhSachSanPhamKhuyenMai(String tenham, String tenmang){
        List<KhuyenMai> khuyenMais = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan =IPConnect.IP;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachKhuyenMai");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKhuyenMai = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachKhuyenMai.length();
            for(int i=0; i< dem;i++)
            {
                JSONObject object = jsonArrayDanhSachKhuyenMai.getJSONObject(i);
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(TrangChuActivity.SERER + object.getString("HINHKHUYENMAI"));
                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAM");
                int demsanpham = arrayDanhSachSanPham.length();
                for(int j=0; j< demsanpham; j++)
                {
                    JSONObject objectSanPham = arrayDanhSachSanPham.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSanPham.getInt("MASP"));
                    sanPham.setTENSP(objectSanPham.getString("TENSP"));
                    sanPham.setGIA(objectSanPham.getInt("GIA"));
                    sanPham.setANHLON(TrangChuActivity.SERER + objectSanPham.getString("HINHLON"));
                    sanPham.setANHNHO(objectSanPham.getString("HINHNHO"));
                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);

                }

                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMais.add(khuyenMai);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }


        return khuyenMais;
    }

    public int KiemTraKhuyenMai(int masp){
        int phanTramKhuyenMai =0;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = IPConnect.IP;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraSanPhamKhuyenMai");

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMaSP);

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
