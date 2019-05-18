package com.example.huynhvinh.applazada_java.Presenter.NhaCuaVaDoiSong;

import android.util.Log;

import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.model.NhaCuaVaDoiSong.NhaCuaVaDoiSongModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.view.TrangChu.ViewNhaCuaVaDoiSong;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicNhaCuaVaDoiSong implements IPresenterNhaCuaVaDoiSong {

    NhaCuaVaDoiSongModel nhaCuaVaDoiSongModel;
    ViewNhaCuaVaDoiSong viewNhaCuaVaDoiSong;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    private  List<SanPham> sanPhamList;

    public PresenterLogicNhaCuaVaDoiSong(ViewNhaCuaVaDoiSong viewNhaCuaVaDoiSong) {
        nhaCuaVaDoiSongModel = new NhaCuaVaDoiSongModel();
        this.viewNhaCuaVaDoiSong = viewNhaCuaVaDoiSong;
        sanPhamList = new ArrayList<>();
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai();
    }

    @Override
    public void LayDanhSachLoaiSanPham() {
        List<LoaiSanPham> listLoaiSP = new ArrayList<>();
        List<LoaiSanPham> listLoaiSPCon = new ArrayList<>();
        List<String> listAnhLoaiSP = new ArrayList<>();

        listLoaiSP =  nhaCuaVaDoiSongModel.LayLoaiSanPhamTheoMaLoaiCha(17);
        if(listLoaiSP.size()>0)
        {
            for(int i=0; i < listLoaiSP.size();i++)
            {
                List<LoaiSanPham> listLoaiSPCon1 =  nhaCuaVaDoiSongModel.LayLoaiSanPhamTheoMaLoaiCha(listLoaiSP.get(i).getMALOAISP());  // 1 thằng loại sp get đc 1 list loại sản phẩm con
                for(int j = 0; j<listLoaiSPCon1.size();j++)
                {
                    listLoaiSPCon.add(listLoaiSPCon1.get(j));
                        List<SanPham> sanPhamList1 = nhaCuaVaDoiSongModel.LayDanhSachSanPhamTheoMaLoaiSP("LayDanhSachSanPhamThongQuaLoaiSanPhamNhaCuaVaDoiSong","DANHSACHSANPHAMNHACUAVADOISONG",listLoaiSPCon1.get(j).getMALOAISP());
                    if(sanPhamList1.size()==0)
                    {
                        listAnhLoaiSP.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSie9Lz9XP1PGwJkyHUkvRjSWMv4wAQFuJA9cFZhCazefcO3kCs");
                    }
                    else {
                        listAnhLoaiSP.add(sanPhamList1.get(0).getANHLON());
                        for(int k=0; k<sanPhamList1.size();k++)
                        {
                            SanPham sanPham = new SanPham();
                            sanPham = sanPhamList1.get(k);
                            sanPhamList.add(sanPham);
                        }
                    }
                }
            }
        }
        LayDanhSachSPBanChayNhat();
        LayDanhSachSPMoiNhat();
        viewNhaCuaVaDoiSong.HienThiDanhSachLoaiSanPhamNhaCuaVaDoiSong(listLoaiSPCon,listAnhLoaiSP);
    }

    private  void LayDanhSachSPBanChayNhat() {

        List<SanPham> listSPBanChayNhat = new ArrayList<>();

        for (int i = 0; i < sanPhamList.size()-1; i++)
        {
            int LuotMuaDau = sanPhamList.get(i).getLUOTMUA();
            for(int j=i+1;j<sanPhamList.size();j++)
            {
                int LuotMuaSau = sanPhamList.get(j).getLUOTMUA();
                if(LuotMuaDau <=  LuotMuaSau)
                {
                    LuotMuaDau = sanPhamList.get(j).getLUOTMUA();
                    SanPham sanPham = new SanPham();
                    sanPham = sanPhamList.get(i);
                    sanPhamList.set(i,sanPhamList.get(j));
                    sanPhamList.set(j,sanPham);

                }
            }
        }


        for(int i=0; i<10;i++)
        {
            listSPBanChayNhat.add(sanPhamList.get(i));
        }

        if(listSPBanChayNhat.size()>0) {
            for (int i = 0; i < listSPBanChayNhat.size(); i++) {
                int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(listSPBanChayNhat.get(i).getMASP());
                if(phantramkm!=0)
                {
                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                    listSPBanChayNhat.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                }
            }
        }

        viewNhaCuaVaDoiSong.HienThiDanhSachSanPhamBanChayNhat(listSPBanChayNhat);
    }

    private void LayDanhSachSPMoiNhat() {
        List<SanPham> sanPhamListMoinhat = new ArrayList<>();
        List<SanPham> listSanPhamMN = new ArrayList<>();
        listSanPhamMN.addAll(sanPhamList);


        for (int i = 0; i < listSanPhamMN.size()-1; i++)
        {
            int SanPhamDau = listSanPhamMN.get(i).getMASP();
            for(int j=i+1;j<listSanPhamMN.size();j++)
            {
                int SanPhamSau = listSanPhamMN.get(j).getMASP();
                if(SanPhamDau <=  SanPhamSau)
                {
                    SanPhamDau = listSanPhamMN.get(j).getMASP();
                    SanPham sanPham = new SanPham();
                    sanPham = listSanPhamMN.get(i);
                    listSanPhamMN.set(i,listSanPhamMN.get(j));
                    listSanPhamMN.set(j,sanPham);

                }
            }
        }

        for(int i=0; i<10 ; i++)
        {
            sanPhamListMoinhat.add(listSanPhamMN.get(i));
        }

        if(sanPhamListMoinhat.size()>0) {
            for (int i = 0; i < sanPhamListMoinhat.size(); i++) {
                int phantramkm = presenterLogicKhuyenMai.KiemTraKhuyenMai(sanPhamListMoinhat.get(i).getMASP());
                if(phantramkm!=0)
                {
                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(phantramkm);
                    sanPhamListMoinhat.get(i).setChiTietKhuyenMai(chiTietKhuyenMai);
                }
            }
        }

        viewNhaCuaVaDoiSong.HienThiDanhSachSanPhamMoiNhat(sanPhamListMoinhat);
    }
}