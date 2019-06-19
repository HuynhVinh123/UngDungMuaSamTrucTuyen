package com.example.huynhvinh.applazada_java.model.ChiTietSanPham;

import android.util.Log;

import com.example.huynhvinh.applazada_java.Adapter.LoGoThuongHieuLonDienTuAdapter;
import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ThuongHieu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChiTietSanPhamModel {

    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp,int limit){
        List<DanhGia> danhGiaList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachDanhGiaTheoMaSP");

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp", String.valueOf(masp));

        HashMap<String,String> hashLimit = new HashMap<>();
        hashLimit.put("limit", String.valueOf(limit));

        attrs.add(hashHam);
        attrs.add(hashMaSP);
        attrs.add(hashLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhGia = jsonObject.getJSONArray("DANHSACHDANHGIA");
            int dem = jsonArrayDanhGia.length();

            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayDanhGia.getJSONObject(i);

                DanhGia danhGia = new DanhGia();
                danhGia.setTENTHIETBI(object.getString("TENNGUOIDG"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));
                danhGia.setMANV(object.getInt("MANV"));
                danhGia.setHINHDANHGIA(object.getString("HINHDANHGIA"));

                danhGiaList.add(danhGia);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGiaList;
    }

    public SanPham LayChiTietSanPham(String tenham,String tenmang,int masp){

        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham",tenham);
        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp",String.valueOf(masp));

        attrs.add(hashHam);
        attrs.add(hashMaSP);
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

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNHANVIEN(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat =  object.getJSONArray("THONGSOKYTHUAT");

                for(int j =0 ; j<jsonArrayThongSoKyThuat.length();j++)
                {
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);

                    for(int k =0; k< jsonObject1.names().length();k++)  // Lấy ra key của tất cả các giá trị trong 1 đối tượng
                    {
                        String tenchitiet = jsonObject1.names().getString(k); // Lấy ra tên ở vị trí đầu

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenchitiet));
                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }

    public ThuongHieu kiemTraThuongHieu(int math,String tenth){
        ThuongHieu thuongHieu = new ThuongHieu();

        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","KiemTraCoPhaiLaThuongHieuLon");
        HashMap<String,String> hashMaTH = new HashMap<>();
        hashMaTH.put("math",String.valueOf(math));
        HashMap<String,String> hashTenTH = new HashMap<>();
        hashTenTH.put("tenth",tenth);

        attrs.add(hashHam);
        attrs.add(hashMaTH);
        attrs.add(hashTenTH);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("THUONGHIEULON");
            int dem = jsonArrayThuongHieu.length();
            for(int i=0; i<dem;i++)
            {
                JSONObject object = jsonArrayThuongHieu.getJSONObject(i);
                thuongHieu.setTENTHUONGHIEU(object.getString("TENTH"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  thuongHieu;
    }

}
