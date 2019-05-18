package com.example.huynhvinh.applazada_java.model.QuenMatKhau;

import com.example.huynhvinh.applazada_java.ConnectInternet.DownloadJSON;
import com.example.huynhvinh.applazada_java.ConnectInternet.IPConnect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuenMatKhauModel {

    public int KiemTraEmail(String email)
    {
        int manv = 0;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = IPConnect.IP ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraEmailQuenMatKhau");

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email",email);

        attrs.add(hsHam);
        attrs.add(hsEmail);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int ma = object.getInt("ketqua");
            if(ma!=0)
            {
                manv = ma;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  manv;
    }

    public boolean CapNhatMatKhau(String matkhau,int manv){
        boolean kiemtra = false;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan =IPConnect.IP ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","CapNhatMatKhau");

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        HashMap<String,String> hsMaNV = new HashMap<>();
        hsMaNV.put("manv",String.valueOf(manv));

        attrs.add(hsHam);
        attrs.add(hsMatKhau);
        attrs.add(hsMaNV);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            String ketqua = object.getString("ketqua");
            if(ketqua.trim().equals("true"))
            {
                kiemtra = true;
            }else {
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  kiemtra;
    }

}
