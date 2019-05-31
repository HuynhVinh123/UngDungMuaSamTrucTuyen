package com.example.huynhvinh.applazada_java.model.ThemSanPham;

import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThemSanPhamModel {

    public List<LoaiSanPham> LayDanhSachLoaiSanPham(){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        // Lấy dữ liệu bằng phướng thức get
        /*
        String duongdan = "http://192.168.1.133:8080/weblazada/loaisanpham.php?maloaicha=0";

        DownloadJSON downloadJSON = new DownloadJSON(duongdan);*/
        // End

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("maloaicha","0");

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayDanhSachMenu");;
        attrs.add(hashMaLoaiCha);
        attrs.add(hashHam);
        DownloadJSON downloadJSON =  new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }




    public boolean ThemSanPham(String convertImage1, String convertImage2, String convertImage3, SanPham sanPham){
        boolean ketqua = false;
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMss");
        String image1 = sdf.format(new Date());
        double image2 = Double.parseDouble(image1) + 1;
        double image3 = Double.parseDouble(image1) + 2;


        HashMap<String,String> hashMaLoaiCha = new HashMap<>();
        hashMaLoaiCha.put("ham","ThemSanPham");

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


        HashMap<String,String> hashTenSP = new HashMap<>();
        hashTenSP.put("tensp",sanPham.getTENSP());

        HashMap<String,String> hashGia = new HashMap<>();
        hashGia.put("gia",String.valueOf(sanPham.getGIA()));

        HashMap<String,String> hashThongTin = new HashMap<>();
        hashThongTin.put("thongtin",sanPham.getTHONGTIN());

        HashMap<String,String> hashSoLuong = new HashMap<>();
        hashSoLuong.put("soluong",String.valueOf(sanPham.getSOLUONG()));

        HashMap<String,String> hashMaLoaiSP = new HashMap<>();
        hashMaLoaiSP.put("maloaisp",String.valueOf(sanPham.getMALOAISP()));

        HashMap<String,String> hashMaThuongHieu = new HashMap<>();
        if(sanPham.getMATHUONGHIEU() == 0)
        {

            hashMaThuongHieu.put("mathuonghieu",String.valueOf(18));
        }
        else {
            hashMaThuongHieu.put("mathuonghieu",String.valueOf(sanPham.getMATHUONGHIEU()));
        }

        HashMap<String,String> hashMaNV = new HashMap<>();
        hashMaNV.put("manv",String.valueOf(sanPham.getMANV()));




        attrs.add(hashMaLoaiCha);
        attrs.add(hashImageName1);
        attrs.add(hashImageName2);
        attrs.add(hashImageName3);
        attrs.add(hashConvertImage1);
        attrs.add(hashConvertImage2);
        attrs.add(hashConvertImage3);
        //attrs.add(hashDanhSachHinhAnh);
        attrs.add(hashTenSP);
        attrs.add(hashGia);
        attrs.add(hashThongTin);
        attrs.add(hashSoLuong);
        attrs.add(hashMaLoaiSP);
        attrs.add(hashMaNV);
        attrs.add(hashMaThuongHieu);

        DownloadJSON downloadJSON =  new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject object = new JSONObject(dataJSON);
            String kiemtra = object.getString("ketqua");
            if(kiemtra.equals("true"))
            {
                ketqua=true;
            }
            else {
                ketqua = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ketqua;
    }


    public void themChiTietSanPham(List<ThongSoKyThuat> thongSoKyThuatList,int masp){
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","ThemChiTietSanPham");

        HashMap<String,String> hashMasp = new HashMap<>();
        hashMasp.put("masp",String.valueOf(masp));

        // Parse list sản phẩm về dạng chuỗi Json
        String chuoijson = "{\"DANHSACHTHONGSO\" :[ " ;

        for(int i=0; i< thongSoKyThuatList.size();i++)
        {
            chuoijson += "{";

            chuoijson += "\"tenchitiet\" : \"" + thongSoKyThuatList.get(i).getTenchitiet() + "\",";
            chuoijson += "\"giatri\" : \"" + thongSoKyThuatList.get(i).getGiatri() + "\"";

            if(i==thongSoKyThuatList.size()-1) {
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }
        }

        chuoijson+="]}";

        HashMap<String,String> hashDanhSachThongSo = new HashMap<>();
        hashDanhSachThongSo.put("danhsachthongso",chuoijson);

        attrs.add(hashHam);
        attrs.add(hashDanhSachThongSo);
        attrs.add(hashMasp);

        DownloadJSON downloadJSON =  new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int layMaSanPhamMoiNhat(){
        String dataJSON = "";
        int masp =0;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP ;

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","LayMaSanPhamMoiNhat");

        attrs.add(hashHam);

        DownloadJSON downloadJSON =  new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {

            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            masp = object.getInt("ketqua");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  masp;
    }
}
