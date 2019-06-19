package com.example.huynhvinh.applazada_java.model.DanhGia;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DanhGiaModel {

    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp,int limit){
        List<DanhGia> danhGiaList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan =IPConnect.IP ;

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

    public boolean ThemDanhGia(DanhGia danhGia,String convertImage1,String convertImage2,String convertImage3,int uyTin){

        // Lấy dữ liệu bằng phương thức POST
        String duongdan =  IPConnect.IP ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMss");
        String image1 = sdf.format(new Date());
        double image2 = Double.parseDouble(image1) + 1;
        double image3 = Double.parseDouble(image1) + 2;

        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsUyTin = new HashMap<>();
        hsUyTin.put("uytin",String.valueOf(uyTin));

        HashMap<String,String> hashImageName1 = new HashMap<>();
        hashImageName1.put("image_tag1",image1);

        HashMap<String,String> hashImageName2 = new HashMap<>();
        hashImageName2.put("image_tag2",String.valueOf(image2));

        HashMap<String,String> hashImageName3 = new HashMap<>();
        hashImageName3.put("image_tag3",String.valueOf(image3));

        HashMap<String,String> hashConvertImage1 = new HashMap<>();
        HashMap<String,String> hashConvertImage2 = new HashMap<>();
        HashMap<String,String> hashConvertImage3 = new HashMap<>();

        hashConvertImage1.put("image_data1",convertImage1);

        hashConvertImage2.put("image_data2",convertImage2);

        hashConvertImage3.put("image_data3",convertImage3);

        HashMap<String,String> hsMADG = new HashMap<>();
        hsMADG.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        HashMap<String,String> hsMaNV = new HashMap<>();
        hsMaNV.put("manv", String.valueOf(danhGia.getMANV()));

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tennguoidanhgia", String.valueOf(danhGia.getTENTHIETBI()));

        attrs.add(hashImageName1);
        attrs.add(hashImageName2);
        attrs.add(hashImageName3);
        attrs.add(hashConvertImage1);
        attrs.add(hashConvertImage2);
        attrs.add(hashConvertImage3);
        attrs.add(hsHam);
        attrs.add(hsMADG);
        attrs.add(hsMaSP);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);
        attrs.add(hsMaNV);
        attrs.add(hsTenThietBi);
        attrs.add(hsUyTin);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            Log.d("kiemttt",dulieuJSON);
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

    public List<DanhGia> LayDanhSachDanhGiaCuaNhanVien(int manv){
        List<DanhGia> danhGiaList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachDanhGiaTheoMaNV");

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("manv", String.valueOf(manv));



        attrs.add(hashHam);
        attrs.add(hashMaSP);

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
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                SanPham sanPham = new SanPham();
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setANHLON(object.getString("HINHLON"));

                danhGia.setSanPham(sanPham);
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

    public boolean KiemTraSanPhamDaDuocMua(String manv, int masp){

        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP;

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("manv",manv);

        HashMap<String,String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp",String.valueOf(masp));

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","KiemTraKhachHangDaTungMuaSanPham");

        attrs.add(hashMaNV);
        attrs.add(hashMaSP);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true"))
            {
                kiemtra =true;
            }
            else {
                kiemtra = false;
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
        return  kiemtra;
    }

}
