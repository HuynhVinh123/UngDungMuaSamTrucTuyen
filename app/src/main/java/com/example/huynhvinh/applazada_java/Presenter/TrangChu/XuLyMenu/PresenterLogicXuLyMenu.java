package com.example.huynhvinh.applazada_java.Presenter.TrangChu.XuLyMenu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewXuLyMenu;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu)
    {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
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

            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public AccessToken LayTenNguoiDungFacebook() {
        DangNhapModel dangNhapModel = new DangNhapModel();
        AccessToken accessToken = dangNhapModel.LayTokenFacebookHienTai();

        return  accessToken;
    }
}