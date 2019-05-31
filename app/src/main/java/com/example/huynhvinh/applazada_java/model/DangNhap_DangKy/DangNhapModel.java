package com.example.huynhvinh.applazada_java.model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DangNhapModel {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    public AccessToken LayTokenFacebookHienTai(){

            accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
            accessToken = AccessToken.getCurrentAccessToken();
        return  accessToken;
    }

    public  void HuyTokenFacebook(){
        accessTokenTracker.stopTracking();
    }

    public boolean KiemTraDangNhap(Context context,String TaiKhoan, String MatKhau){

        List<HashMap<String,String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        // Lấy dữ liệu bằng phương thức POST
        String duongdan = IPConnect.IP;

        HashMap<String,String> hashTenDN = new HashMap<>();
        hashTenDN.put("tendangnhap",TaiKhoan);

        HashMap<String,String> hashPassword = new HashMap<>();
        hashPassword.put("matkhau",MatKhau);

        HashMap<String,String> hashHam = new HashMap<>();
        hashHam.put("ham","KiemTraDangNhap");

        attrs.add(hashTenDN);
        attrs.add(hashPassword);
        attrs.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true"))
            {
                String tennv = jsonObject.getString("tennv");
                String sodt = jsonObject.getString("sodt");
                String manv = jsonObject.getString("manv");
                String diachi = jsonObject.getString("diachi");
                Log.d("kiemtra",diachi);
                kiemtra =true;
                CapNhatCachedDangNhap(context,tennv,sodt,manv,diachi);
                CapNhatCacheKiemTraDangNhap(context,"3");
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
    // key = 0 : Google, key = 1 : Facebook, key = 3: Tai khoan DK
    public void CapNhatCacheKiemTraDangNhap(Context context,String keyCheck)
    {
        SharedPreferences cachedKiemTraDangNhap = context.getSharedPreferences("kiemtradangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedKiemTraDangNhap.edit();
        editor.putString("checkDN",keyCheck);
        editor.commit();
    }

    public String LayCacheKiemTraDangNhap(Context context){
        SharedPreferences cachedKiemTraDangNhap = context.getSharedPreferences("kiemtradangnhap",Context.MODE_PRIVATE);
        String keyCheck = cachedKiemTraDangNhap.getString("checkDN","");
        return  keyCheck;
    }

    public void CapNhatCachedDangNhap(Context context,String tennv,String sodt,String manv,String diachi)
    {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv",tennv);
        editor.putString("sodt",sodt);
        editor.putString("manv",manv);
        editor.putString("diachi",diachi);

        editor.commit();
    }

    public String LayCacheDiaChiDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String diachi = cachedDangNhap.getString("diachi","");
        return diachi;
    }

    public String LayCachedDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");
        return tennv;
    }

    public String LayCacheSoDTDangNhap(Context context)
    {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String sodt = cachedDangNhap.getString("sodt","");
        return sodt;
    }

    public  String LayCacheMaNVDangNhap(Context context)
    {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String manv = cachedDangNhap.getString("manv","");
        return manv;
    }

}