package com.example.huynhvinh.applazada_java.model.ObjectClass;

import com.example.huynhvinh.applazada_java.model.Room.object.ThongSoKyThuat;

import java.io.Serializable;
import java.util.List;

public class SanPham implements Serializable {
    int MASP;
    int GIA;
    int SOLUONG;
    int MALOAISP;
    int MATHUONGHIEU;
    int MANV;
    int LUOTMUA;
    int SOLUONGTONKHO;
    int GIAKHUYENMAI; // nếu có khuyến mãi thì push giá vào
    String ANHLON;
    String ANHNHO;
    String THONGTIN;
    String TENSP;
    String TENNHANVIEN;
    byte[]  hinhgiohang;
    ChiTietKhuyenMai chiTietKhuyenMai;

    List<ChiTietSanPham> chiTietSanPhamList;

    List<ThongSoKyThuat> thongSoKyThuatList;

    public List<ThongSoKyThuat> getThongSoKyThuatList() {
        return thongSoKyThuatList;
    }

    public void setThongSoKyThuatList(List<ThongSoKyThuat> thongSoKyThuatList) {
        this.thongSoKyThuatList = thongSoKyThuatList;
    }

    public ChiTietKhuyenMai getChiTietKhuyenMai() {
        return chiTietKhuyenMai;
    }

    public void setChiTietKhuyenMai(ChiTietKhuyenMai chiTietKhuyenMai) {
        this.chiTietKhuyenMai = chiTietKhuyenMai;
    }

    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public int getGIAKHUYENMAI() {
        return GIAKHUYENMAI;
    }

    public void setGIAKHUYENMAI(int GIAKHUYENMAI) {
        this.GIAKHUYENMAI = GIAKHUYENMAI;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMATHUONGHIEU() {
        return MATHUONGHIEU;
    }

    public void setMATHUONGHIEU(int MATHUONGHIEU) {
        this.MATHUONGHIEU = MATHUONGHIEU;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public int getLUOTMUA() {
        return LUOTMUA;
    }

    public void setLUOTMUA(int LUOTMUA) {
        this.LUOTMUA = LUOTMUA;
    }

    public int getSOLUONGTONKHO() {
        return SOLUONGTONKHO;
    }

    public void setSOLUONGTONKHO(int SOLUONGTONKHO) {
        this.SOLUONGTONKHO = SOLUONGTONKHO;
    }

    public String getANHLON() {
        return ANHLON;
    }

    public void setANHLON(String ANHLON) {
        this.ANHLON = ANHLON;
    }

    public String getANHNHO() {
        return ANHNHO;
    }

    public void setANHNHO(String ANHNHO) {
        this.ANHNHO = ANHNHO;
    }

    public String getTHONGTIN() {
        return THONGTIN;
    }

    public void setTHONGTIN(String THONGTIN) {
        this.THONGTIN = THONGTIN;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public String getTENNHANVIEN() {
        return TENNHANVIEN;
    }

    public void setTENNHANVIEN(String TENNHANVIEN) {
        this.TENNHANVIEN = TENNHANVIEN;
    }
}
