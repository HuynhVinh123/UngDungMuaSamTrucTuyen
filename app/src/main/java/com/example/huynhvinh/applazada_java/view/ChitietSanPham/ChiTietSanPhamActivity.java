package com.example.huynhvinh.applazada_java.view.ChitietSanPham;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.Adapter.DanhGiaAdapter;
import com.example.huynhvinh.applazada_java.Adapter.ViewPagerSliderAdapter;
import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.huynhvinh.applazada_java.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.huynhvinh.applazada_java.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietKhuyenMai;
import com.example.huynhvinh.applazada_java.model.ObjectClass.ChiTietSanPham;
import com.example.huynhvinh.applazada_java.model.ObjectClass.DanhGia;
import com.example.huynhvinh.applazada_java.model.ObjectClass.SanPham;
import com.example.huynhvinh.applazada_java.model.Room.object.SanPham_Room;
import com.example.huynhvinh.applazada_java.model.Room.viewmodel.SanPhamViewModel;
import com.example.huynhvinh.applazada_java.view.DangNhap.DangNhapActivity;
import com.example.huynhvinh.applazada_java.view.DanhGia.DanhSachDanhGiaActivity;
import com.example.huynhvinh.applazada_java.view.DanhGia.ThemDanhGiaActivity;
import com.example.huynhvinh.applazada_java.view.GioHang.GioHangActivity;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    private PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    private PresenterLogicKhuyenMai presenterLogicKhuyenMai;
    private TextView[] txtDots;
    private LinearLayout layoutDots;
    private List<Fragment> fragmentList;
    private TextView txtTenSP,txtGiaTien,txtTenCuaHangDongGoi,txtThongTinSP,txtXemTatCaNhanXet,txtGioHang,txtGiamGia;
    private Toolbar toolbar;
    private ImageView imgXemThemChiTiet,imgYeuThich;
    private FButton imThemGioHang, btn_SuaSanPham;
    private boolean kiemtraxochitiet = false;
    private boolean onPause = false;
    private LinearLayout lnThongSoKyThuat;
    private TextView txtVietDanhGia;
    private RecyclerView recyclerDanhGiaChiTiet;
    private RatingBar rtbDanhGiaSanPham;
    private SanPham sanPhamGioHang,sanPhamYeuThich;
    private NestedScrollView nestedScrollView;
    private SanPhamViewModel sanPhamViewModel;
    private String hinhSanPham,tenSanPham;
    private int masp;
    private int phantramkm=0;
    private int giatien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_chitietsanpham);

        btn_SuaSanPham = (FButton) findViewById(R.id.btn_Suasanpham);
        txtGiamGia = (TextView) findViewById(R.id.txtGiamgia);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestScrollviewChiTietSanPham);
        viewPager = (ViewPager) findViewById(R.id.viewPagerSlider);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        txtTenSP = (TextView) findViewById(R.id.txtTenSanPham);
        txtGiaTien = (TextView) findViewById(R.id.txtGiaSanPham);
        txtTenCuaHangDongGoi = (TextView) findViewById(R.id.txtTenCHDongGoi);
        txtThongTinSP = (TextView) findViewById(R.id.txtThongTinChiTiet);
        imgXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat = (LinearLayout) findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = (TextView) findViewById(R.id.txtVietDanhGia);
        recyclerDanhGiaChiTiet = (RecyclerView) findViewById(R.id.recyclerDanhGiaChiTiet);
        txtXemTatCaNhanXet = (TextView) findViewById(R.id.txtXemTatCaNhanXet);
        rtbDanhGiaSanPham  = (RatingBar) findViewById(R.id.ratingBarChiTietSanPham);
        imThemGioHang = (FButton) findViewById(R.id.imThemGioHang);
        imgYeuThich = (ImageView) findViewById(R.id.imgYeuThich);
        toolbar = (Toolbar) findViewById(R.id.toolBarChiTietSanPham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(" ");

        int MaSP = getIntent().getIntExtra("masp",0);



        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(MaSP);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp,0);

        ThemDotSlider(0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imThemGioHang.setOnClickListener(this);
        imgYeuThich.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {

        String[] hinhs = sanPham.getANHNHO().split(",");

        sanPhamYeuThich = sanPham;
        sanPhamGioHang = sanPham;
        // Cập nhật số lượng sản phẩm tối đa mà của hàng đang có
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        // để chuyển dữ liệu qua trang đánh giá sản phẩm
        masp = sanPham.getMASP();
        hinhSanPham = TrangChuActivity.SERER + hinhs[0];

        tenSanPham = sanPham.getTENSP();

        txtTenSP.setText(sanPham.getTENSP());


        // Tính khuyến mãi cho sản phẩm

        giatien = sanPham.getGIA();

        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
        chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        if(chiTietKhuyenMai!=null)
        {
            phantramkm = sanPham.getChiTietKhuyenMai().getPHANTRAMKM();

            if(phantramkm!=0)
            {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + " VNĐ");

                giatien  = giatien*phantramkm/100;


            }
        }

        @SuppressLint({"NewApi", "LocalSuppress"})
        NumberFormat formatter = new DecimalFormat("###,###");
        String gia = formatter.format(giatien);
        txtGiaTien.setText(gia + " VNĐ");

        txtTenCuaHangDongGoi.setText(sanPham.getTENNHANVIEN());

        if(sanPham.getTHONGTIN().length()<100)
        {
            imgXemThemChiTiet.setVisibility(View.GONE);
            txtThongTinSP.setText(sanPham.getTHONGTIN()); // Giới hạn 100 kí tự

        }
        else{
            txtThongTinSP.setText(sanPham.getTHONGTIN().substring(0,100)); // Giới hạn 100 kí tự
            imgXemThemChiTiet.setVisibility(View.VISIBLE);
            imgXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if(kiemtraxochitiet) {

                        // Sau khi mở chi tiết
                        txtThongTinSP.setText(sanPham.getTHONGTIN());
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);

                        if(sanPham.getMATHUONGHIEU() != 0) {
                            HienThiThongSoKyThuat(sanPham);
                        }

                    }else{
                        txtThongTinSP.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        txtThongTinSP.setText(sanPham.getTHONGTIN().substring(0,100)); // Giới hạn 100 kí tự
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }

        // Thêm sản phẩm vào danh sách đã xem
        SanPham_Room sanPham_room = new SanPham_Room();
        sanPham_room.setMasp(sanPham.getMASP());
        sanPham_room.setTensp(sanPham.getTENSP());
        sanPham_room.setGia(sanPham.getGIA());
        sanPham_room.setImage(TrangChuActivity.SERER + hinhs[0]);
        sanPham_room.setGiakm(giatien);

        // Add sản phẩm vào danh sách sản phẩm đã xem
        sanPhamViewModel = ViewModelProviders.of(this).get(SanPhamViewModel.class);
        sanPhamViewModel.themSanPham(sanPham_room);

        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);

    }

    private  void HienThiThongSoKyThuat(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();
        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSoKyThuat.setText("Thông số kỹ thuật");
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);

        if(chiTietSanPhamList.size()>0) {
            for (int i = 0; i < chiTietSanPhamList.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                TextView txtTenThongSo = new TextView(this);
                txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                txtTenThongSo.setText(chiTietSanPhamList.get(i).getTENCHITIET());

                TextView txtGiaTriThongSo = new TextView(this);
                txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                txtGiaTriThongSo.setText(chiTietSanPhamList.get(i).getGIATRI());


                linearLayout.addView(txtTenThongSo);
                linearLayout.addView(txtGiaTriThongSo);

                lnThongSoKyThuat.addView(linearLayout);
            }
        }
        else
        {

        }
    }

    private Drawable getHinhChiTiet(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT>21)
        {
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }
        else
        {
            drawable  = getResources().getDrawable(idDrawable);
        }
        return  drawable;
    }

    @Override
    public void HienThiSliderSanPham(String[] linkHinhSanPham) {

        fragmentList = new ArrayList<>();
        for(int i=0; i< linkHinhSanPham.length;i++)
        {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",TrangChuActivity.SERER + linkHinhSanPham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);
            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        ViewPagerSliderAdapter viewPagerSliderAdapter = new ViewPagerSliderAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(viewPagerSliderAdapter);
        viewPagerSliderAdapter.notifyDataSetChanged();

        viewPager.addOnPageChangeListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        // Show số lượng sản phẩm trên giỏ hàng
        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        MenuItem iFunction = menu.findItem(R.id.itemFunction);
        iFunction.setVisible(false);

        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                break;
            case  R.id.itHome:
                Intent iTrangChu = new Intent(this,TrangChuActivity.class);
                startActivity(iTrangChu);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void ThemDotSlider(int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for(int i=0; i<fragmentList.size();i++)
        {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
   }

    private int getIdColor(int idColor){
        int color = 0;

        if(Build.VERSION.SDK_INT>21)
        {
            color = ContextCompat.getColor(this,idColor);
        }
        else
        {
            color = getResources().getColor(idColor);
        }
        return  color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtVietDanhGia:
                DangNhapModel dangNhapModel = new DangNhapModel();
                String tennv =  dangNhapModel.LayCachedDangNhap(this);

                if(!tennv.trim().equals("")){
                    Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                    iThemDanhGia.putExtra("masp",masp);
                    iThemDanhGia.putExtra("hinhsp",hinhSanPham);
                    iThemDanhGia.putExtra("tensp",tenSanPham);
                    startActivity(iThemDanhGia);
                }else{
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }

                break;
            case R.id.txtXemTatCaNhanXet:
                Intent iDanhSachDanhGia = new Intent(this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp",masp);
                startActivity(iDanhSachDanhGia);
                break;
            case R.id.imThemGioHang:
                // Xử lý chuyển hình sản phẩm về dạng Byte[] để lưu lên SQLite bằng cách lấy ảnh trên fragment
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imgSlider);
                Bitmap bitmap =  ((BitmapDrawable) imageView.getDrawable()).getBitmap();    // Get Hình ảnh từ ImageView

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);

                sanPhamGioHang.setSOLUONG(1);

                sanPhamGioHang.setGIAKHUYENMAI(giatien);

                if(imageView!=null) {
                    // Truyền phantramkhuyen mãi để model kiểm tra xem sản phẩm đó có khuyến mãi hay ko r push giá vào cho giỏ hàng
                    presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this, phantramkm);
                }

                break;
            case R.id.imgYeuThich:
                // Xử lý chuyển hình sản phẩm về dạng Byte[] để lưu lên SQLite bằng cách lấy ảnh trên fragment
                Fragment fragment1 = fragmentList.get(0);
                View view1 = fragment1.getView();
                ImageView imageView1 = view1.findViewById(R.id.imgSlider);

                Bitmap bitmap1 =  ((BitmapDrawable) imageView1.getDrawable()).getBitmap();    // Get Hình ảnh từ ImageView

                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream1);

                byte[] hinhspyeuthich = byteArrayOutputStream1.toByteArray();

                sanPhamYeuThich.setHinhgiohang(hinhspyeuthich);

                if(imageView1 != null) {
                    presenterLogicChiTietSanPham.ThemSPYeuThich(sanPhamYeuThich, this, phantramkm);
                }
                break;
        }
    }


    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {

        float TongSoSao = 0;

        for(int i =0; i<danhGiaList.size(); i++)
        {
            TongSoSao += danhGiaList.get(i).getSOSAO();
        }

        rtbDanhGiaSanPham.setRating(TongSoSao/danhGiaList.size());

        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter(this,danhGiaList,3,R.layout.custom_layout_recycler_danhgia_chitiet);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);

        recyclerDanhGiaChiTiet.setAdapter(danhGiaAdapter);

        danhGiaAdapter.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toasty.success(this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT,true).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toasty.warning(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void ThemSPYeuThichThanhCong() {
        Toasty.success(this, "Sản phẩm đã được thêm vào danh sách yêu thích", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void ThemSPYeuThichThatBai() {
        Toasty.warning(this, "Sản phẩm đã có trong danh sách yêu thích", Toast.LENGTH_SHORT,true).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause == true) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSoLuongSanPhamCoTrongGioHang(this)));
        }
    }
}