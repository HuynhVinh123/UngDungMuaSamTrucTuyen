package com.example.huynhvinh.applazada_java.model.ObjectClass;

import java.util.List;

public class HoaDon {

    int MaHD,ChuyenKhoan,SoTien;
    String NgayMua,NgayGiao,TrangThai,TenNguoiNhan,SoDT,DiaChi,MaChuyenKhoan,Email,MaNguoiNhan;
    List<ChiTietHoaDon> chiTietHoaDonList;


    public String getMaNguoiNhan() {
        return MaNguoiNhan;
    }

    public void setMaNguoiNhan(String maNguoiNhan) {
        MaNguoiNhan = maNguoiNhan;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int soTien) {
        SoTien = soTien;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public int getChuyenKhoan() {
        return ChuyenKhoan;
    }

    public void setChuyenKhoan(int chuyenKhoan) {
        ChuyenKhoan = chuyenKhoan;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public String getNgayGiao() {
        return NgayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        NgayGiao = ngayGiao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenNguoiNhan() {
        return TenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        TenNguoiNhan = tenNguoiNhan;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getMaChuyenKhoan() {
        return MaChuyenKhoan;
    }

    public void setMaChuyenKhoan(String maChuyenKhoan) {
        MaChuyenKhoan = maChuyenKhoan;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

}
