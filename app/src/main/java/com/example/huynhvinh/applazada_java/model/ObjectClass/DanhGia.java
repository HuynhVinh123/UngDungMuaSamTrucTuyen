package com.example.huynhvinh.applazada_java.model.ObjectClass;

public class DanhGia {
    String MADG,TENTHIETBI,TIEUDE,NOIDUNG,NGAYDANHGIA,HINHDANHGIA;
    int SOSAO,MASP,MANV;
    SanPham sanPham;

    public String getHINHDANHGIA() {
        return HINHDANHGIA;
    }

    public void setHINHDANHGIA(String HINHDANHGIA) {
        this.HINHDANHGIA = HINHDANHGIA;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getMADG() {
        return MADG;
    }

    public void setMADG(String MADG) {
        this.MADG = MADG;
    }

    public String getTENTHIETBI() {
        return TENTHIETBI;
    }

    public void setTENTHIETBI(String TENTHIETBI) {
        this.TENTHIETBI = TENTHIETBI;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public String getNOIDUNG() {
        return NOIDUNG;
    }

    public void setNOIDUNG(String NOIDUNG) {
        this.NOIDUNG = NOIDUNG;
    }

    public String getNGAYDANHGIA() {
        return NGAYDANHGIA;
    }

    public void setNGAYDANHGIA(String NGAYDANHGIA) {
        this.NGAYDANHGIA = NGAYDANHGIA;
    }

    public int getSOSAO() {
        return SOSAO;
    }

    public void setSOSAO(int SOSAO) {
        this.SOSAO = SOSAO;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }
}
