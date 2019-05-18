package com.example.huynhvinh.applazada_java.model.NhaCuaVaDoiSong;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NhaCuaVaDoiSongModel {

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoaiCha(int maloaisp) {

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        // Lấy dữ liệu bằng phướng thức get
        /*
        String duongdan = "http://192.168.1.133:8080/weblazada/loaisanpham.php?maloaicha=0";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);*/
        // End

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP;

        HashMap<String, String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "LayDanhSachLoaiSanPhamNhaCuaVaDoiSong");

        attrs.add(hashMaLoaiCha);
        attrs.add(hashHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray loaisanphams = jsonObject.getJSONArray("DANHSACHLOAISANPHAMNHACUAVADOISONG");
            int count = loaisanphams.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisanphams.getJSONObject(i);

                LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
                dataLoaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataLoaiSanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

    public List<SanPham> LayDanhSachSanPhamTheoMaLoaiSP(String tenham,String tenmang,int maloaisp){
        List<SanPham> sanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP;

        HashMap<String,String> hashMaLoaiSP = new HashMap<>();
        hashMaLoaiSP.put("maloaisanpham",String.valueOf(maloaisp));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham",tenham);

        attrs.add(hashMaLoaiSP);
        attrs.add(hashHam);
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
                sanPham.setGIA(object.getInt("GIA"));
                sanPham.setANHLON(object.getString("HINHLON"));
                sanPham.setANHNHO(object.getString("HINHNHO"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));
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
