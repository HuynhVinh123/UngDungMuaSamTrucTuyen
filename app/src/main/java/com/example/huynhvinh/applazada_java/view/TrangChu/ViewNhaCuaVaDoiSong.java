package com.example.huynhvinh.applazada_java.view.TrangChu;

import com.example.huynhvinh.applazada_java.model.ObjectClass.LoaiSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;

import java.util.List;

public interface ViewNhaCuaVaDoiSong {
    void HienThiDanhSachLoaiSanPhamNhaCuaVaDoiSong(List<LoaiSanPham> loaiSanPhams, List<String> anhLoaiSP);
    void HienThiDanhSachSanPhamBanChayNhat(List<SanPham> sanPhamList);
    void HienThiDanhSachSanPhamMoiNhat(List<SanPham> sanPhamList);
}
