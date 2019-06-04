<?php	
	include_once("config.php");
	define("IP","http://192.168.1.142:8080/weblazada/webadmin");
	// if(isset($_GET["tendangnhap"])) // isset dùng để kiểm tra xem get xuống có dữ liệu hay không
	// {
	// 	echo $_GET["tendangnhap"];
	// }

	$ham = $_POST["ham"];
	switch ($ham) {
		case 'LayDanhSachMenu':
			LayDanhSachMenu();
			break;
		case 'DangKyThanhVien':
			DangKyThanhVien();
			break;
		case 'KiemTraDangNhap':
			KiemTraDangNhap();
			break;
		case 'LayDanhSachCacThuongHieuLon':
			LayDanhSachCacThuongHieuLon();
			break;
		case 'LayDanhSachTopDienThoaiVaMayTinhBang':
			LayDanhSachTopDienThoaiVaMayTinhBang();
			break;
		case 'LayDanhSachTopPhuKien':
			LayDanhSachTopPhuKien();
			break;
		case 'LayDanhSachPhuKien': 	
			LayDanhSachPhuKien();
			break;
		case 'LayLogoThuongHieuLon':
			LayLogoThuongHieuLon();
			break;
		case 'LayDanhSachSanPhamMoiVe':
			LayDanhSachSanPhamMoiVe();
			break;
		case 'LayDanhSachSanPhamTheoMaLoaiDanhMuc':
			LayDanhSachSanPhamTheoMaLoaiDanhMuc();
			break;
		case 'LayDanhSachSanPhamTheoMaThuongHieu':
			LayDanhSachSanPhamTheoMaThuongHieu();
			break;
		case 'LaySanPhamVschiTietTheoMaSP':
			LaySanPhamVschiTietTheoMaSP();
			break;
		case 'ThemDanhGia':
			ThemDanhGia();
			break;
		case 'LayDanhSachDanhGiaTheoMaSP':
			LayDanhSachDanhGiaTheoMaSP();
			break;
		case 'ThemHoaDon':
			ThemHoaDon();
			break;
		case 'LayDanhSachLoaiSanPhamNhaCuaVaDoiSong':
			LayDanhSachLoaiSanPhamNhaCuaVaDoiSong();
			break;
		case 'LayDanhSachSanPhamThongQuaLoaiSanPhamNhaCuaVaDoiSong':
			LayDanhSachSanPhamThongQuaLoaiSanPhamNhaCuaVaDoiSong();
			break;
		case 'LayDanhSachTatCaSanPhamTheoMaLoaiDanhMuc':
			LayDanhSachTatCaSanPhamTheoMaLoaiDanhMuc();
			break;
		case 'LayDanhSachTatCaSanPhamTheoMaThuongHieu':
			LayDanhSachTatCaSanPhamTheoMaThuongHieu();
			break;
		case 'KiemTraCoPhaiLaThuongHieuLon':
			KiemTraCoPhaiLaThuongHieuLon();
			break;
		case 'LayDanhSachKhuyenMai':
			LayDanhSachKhuyenMai();
			break;
		case 'LayDanhSachHoaDon':
			LayDanhSachHoaDon();
			break;	
		case 'LaySanPhamChiTietHoaDon':
			LaySanPhamChiTietHoaDon();
			break;	
		case 'LayThongTinNhanVienBangMaNV':
			LayThongTinNhanVienBangMaNV();
			break;
		case 'KiemTraThanhVienDaTonTai':
			KiemTraThanhVienDaTonTai();
			break;
		case 'LayThongTinNhanVienBangIdNV':
			LayThongTinNhanVienBangIdNV();
			break;
		case 'CapNhatThongTinNhanVien':
			CapNhatThongTinNhanVien();
			break;
		case 'CapNhatThongTinNhanvienBangId':
			CapNhatThongTinNhanvienBangId();
			break;
		case 'LayDanhSachHoaDonTheoTrangThai':
			LayDanhSachHoaDonTheoTrangThai();
			break;
		case 'HuyDonHang':
			HuyDonHang();
			break;
		case 'LayDanhSachDanhGiaTheoMaNV':
			LayDanhSachDanhGiaTheoMaNV();
			break;
		case 'KiemTraSanPhamKhuyenMai':
			KiemTraSanPhamKhuyenMai();
			break;
		case 'KiemTraHoaDonCoKhuyenMai':
			KiemTraHoaDonCoKhuyenMai();
			break;
		case 'KiemTraMaCodeKhuyenMai':
			KiemTraMaCodeKhuyenMai();
			break;
		case 'KiemTraEmailQuenMatKhau':
			KiemTraEmailQuenMatKhau();
			break;
		case 'CapNhatMatKhau':
			CapNhatMatKhau();
			break;
		case 'TimKiemSanPhamTheoTenSanPham':
			TimKiemSanPhamTheoTenSanPham();
			break;
		case 'ThemSanPham':
			ThemSanPham();
			break;
		case 'ThemChiTietSanPham':
			ThemChiTietSanPham();
			break;
		case 'LayMaSanPhamMoiNhat':
			LayMaSanPhamMoiNhat();
			break;
		case 'LayDanhSachSanPhamDaDang':
			LayDanhSachSanPhamDaDang();
			break;
		case 'CapNhatSanPhamDaDang':
			CapNhatSanPhamDaDang();
			break;
		case 'XoaSanPham':
			XoaSanPham();
			break;	
		case 'CapNhatLuotMuaChoSanPham':
			CapNhatLuotMuaChoSanPham();
			break;	
		}

		function CapNhatLuotMuaChoSanPham(){
			global $conn;
			if(isset($_POST["danhsachmasp"]))
			{
				$danhsachmasp = $_POST["danhsachmasp"];
			}

			$chuoijsonandroid = json_decode($danhsachmasp); // parse từ chuối json về dạng mảng json
			$arrayDanhSachMaSanPham = $chuoijsonandroid->DANHSACHMASP;
			$dem = count($arrayDanhSachMaSanPham);

			for($i=0; $i<$dem; $i++)
			{
				$jsonObject = $arrayDanhSachMaSanPham[$i];

				$masp = $jsonObject->masp;

				$truyvansanpham = "SELECT * FROM sanpham WHERE MASP = ".$masp;
				
				$ketquatruyvan = mysqli_query($conn,$truyvansanpham);
				if($ketquatruyvan)
				{
					while ($dong = mysqli_fetch_array($ketquatruyvan)) {
						$luotmua = $dong["LUOTMUA"] + 1;
						$soluong = $dong["SOLUONG"] - 1;
						$truyvan = "UPDATE sanpham SET LUOTMUA = ".$luotmua." ,SOLUONG = ".$soluong."  WHERE MASP = ".$masp;

						$ketqua = mysqli_query($conn,$truyvan);
					}
				}	
			}
		}

		function XoaSanPham(){
			global $conn;

			if(isset($_POST["masp"]))
			{
				$masp = $_POST["masp"];
			}

			$truyvan = "DELETE FROM  sanpham WHERE MASP = ".$masp;

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				echo "{ketqua:true}";
			}else{
				echo "{ketqua:false}".mysqli_error($conn);
			}

		}

		function CapNhatSanPhamDaDang(){
			global $conn;
			if(isset($_POST["masp"]) || isset($_POST["tensp"]) || isset($_POST["gia"]) || isset($_POST["thongtin"]) || isset($_POST["soluong"]))
			{
				$masp = $_POST["masp"];
				$tensp = $_POST["tensp"];
				$gia = $_POST["gia"];
				$thongtin = $_POST["thongtin"];
				$soluong = $_POST["soluong"];
			}

			$truyvan = "UPDATE sanpham SET TENSP ='".$tensp."', GIA =".$gia.", THONGTIN = '".$thongtin."', SOLUONG = ".$soluong." WHERE MASP = ".$masp;

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				echo "{ketqua:true}";
			}else{
				echo "{ketqua:false}";
			}

		}

		function LayDanhSachSanPhamDaDang(){
			global $conn;

			if(isset($_POST["manv"]))
			{
				$manv = $_POST["manv"];
			}

			$truyvan = "SELECT * FROM sanpham WHERE MANV =".$manv;

			$chuoijson = array();

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACCHSANPHAM\":";	

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson, array("MASP"=>$dong["MASP"],"TENSP"=>$dong["TENSP"],"GIA"=>$dong["GIA"],"HINHLON"=>IP.$dong["HINHLON"]));
				}	

				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);

				echo "}";
			}else{
				echo "{ketqua:false}";
			}
		}

		function LayMaSanPhamMoiNhat(){
			global $conn;

			$truyvan = "SELECT MASP FROM `sanpham`ORDER BY MASP DESC LIMIT 1";

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				$masp =0;
				while ($dong = mysqli_fetch_array($ketqua)) {
					$masp = $dong["MASP"];
				}
					
				echo "{ketqua:".$masp."}";
			}
		}

		function ThemChiTietSanPham(){
			global $conn;
			if(isset($_POST["danhsachthongso"])|| isset($_POST["masp"]))
			{
				$danhsachthongso = $_POST["danhsachthongso"];
				$masp = $_POST["masp"];
			}

			$chuoijsonandroid = json_decode($danhsachthongso); // parse từ chuối json về dạng mảng json
			$arrayDanhSachThongSo = $chuoijsonandroid->DANHSACHTHONGSO;
			$dem = count($arrayDanhSachThongSo);
		

			// Lấy ra từng đối tượng trong  arrayDanhSachSanPham
			for($i=0; $i<$dem; $i++)
			{

				$jsonObject = $arrayDanhSachThongSo[$i];

				$tenchitiet = $jsonObject->tenchitiet;
				$giatri = $jsonObject->giatri;
				

				$truyvan = "INSERT INTO chitietsanpham (MASP,TENCHITIET,GIATRI) VALUES (".$masp.",'".$tenchitiet."', '".$giatri."')";
				$ketqua1 = mysqli_query($conn,$truyvan);
				if($ketqua1)
				{
						echo "{ketqua:true}" ;
				}	
				else{
						echo "{ketqua:false}".mysqli_error($conn);
				}
			}


		}

		function ThemSanPham(){
			global $conn;
			$Defaule = 0;

			if(isset($_POST["tensp"]) || isset($_POST["gia"]) || isset($_POST["thongtin"]) ||  isset($_POST["soluong"]) || isset($_POST["maloaisp"]) || isset($_POST["manv"]) || isset($_POST["mathuonghieu"]))
			{
				$tensp = $_POST["tensp"];
				$gia = $_POST["gia"];
				$thongtin = $_POST["thongtin"];
				$soluong = $_POST["soluong"];
				$maloaisp = $_POST["maloaisp"];
				$manv = $_POST["manv"];
				$mathuonghieu = $_POST["mathuonghieu"];
			
			}

			$ImageData1 = $_POST['image_data1'];
			$ImageData2 = $_POST['image_data2'];
			$ImageData3 = $_POST['image_data3'];
	 
			$ImageName1 = $_POST['image_tag1'];
			$ImageName2 = $_POST['image_tag2'];
			$ImageName3 = $_POST['image_tag3'];
			 
			if($ImageData1 != "1" && $ImageData2 == "1" && $ImageData3 == "1")
			{
				$ImagePath1 = "hinhsanpham/$ImageName1.jpg";
				
				$ServerURL1 = "/$ImagePath1";
				$ServerURL3 = "/$ImagePath1";
			} 

			if($ImageData2 != "1" && $ImageData3 == "1")
			{
				$ImagePath1 = "hinhsanpham/$ImageName1.jpg";
				$ImagePath2 = "hinhsanpham/$ImageName2.jpg";

				$ServerURL1 = "/$ImagePath1";
				$ServerURL2 = "/$ImagePath2";
				$ServerURL3 = "/$ImagePath1,/$ImagePath2";
			}
			if($ImageData3 != "1" )
			{
				$ImagePath1 = "hinhsanpham/$ImageName1.jpg";
				$ImagePath2 = "hinhsanpham/$ImageName2.jpg";
				$ImagePath3 = "hinhsanpham/$ImageName3.jpg";
				 
				$ServerURL1 = "/$ImagePath1";
				$ServerURL2 = "/$ImagePath2";
				$ServerURL3 = "/$ImagePath1,/$ImagePath2,/$ImagePath3";
			}

			$truyvan = "INSERT INTO sanpham (TENSP,GIA,HINHLON,HINHNHO,THONGTIN,SOLUONG,MALOAISP,MANV,MATH,LUOTMUA) values('".$tensp."',".$gia.",'".$ServerURL1."','".$ServerURL3."','".$thongtin."',".$soluong.",".$maloaisp.",".$manv.",".$mathuonghieu.",0)";

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{

				if($ImageData1 != "1" && $ImageData2 == "1" && $ImageData3 == "1")
					{
						file_put_contents($ImagePath1,base64_decode($ImageData1));
					}

				if($ImageData2 != "1" && $ImageData3 == "1")
				{
					file_put_contents($ImagePath1,base64_decode($ImageData1));
			 		file_put_contents($ImagePath2,base64_decode($ImageData2));
				}
				if($ImageData3 != "1")
				{
				 	file_put_contents($ImagePath1,base64_decode($ImageData1));
				 	file_put_contents($ImagePath2,base64_decode($ImageData2));
				 	file_put_contents($ImagePath3,base64_decode($ImageData3));
			 	}
				echo "{ketqua:true}";
			}else{
				echo "{ketqua : false}".mysqli_error($conn);
			}
		}

		function TimKiemSanPhamTheoTenSanPham(){
			global $conn;

			if(isset($_POST["tensp"]) || isset($_POST["limit"]))
			{
				$tensp = $_POST["tensp"];
				$limit = $_POST["limit"];
			}

			$chuoijson = array();

			$truyvan = "SELECT * FROM sanpham WHERE TENSP like '%".$tensp."%' ORDER BY MASP LIMIT ".$limit.",10";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACCHSANPHAM\":";	

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson, array("MASP"=>$dong["MASP"], "TENSP"=>$dong["TENSP"], "GIATIEN"=>$dong["GIA"]
							,"HINHSANPHAM"=>IP.$dong["HINHLON"],"HINHSANPHAMNHO"=>$dong["HINHNHO"],"MANV"=>$dong["MANV"]));
				}
			}
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

		function CapNhatMatKhau(){
			global $conn;
			if(isset($_POST["matkhau"]) || isset($_POST["manv"]))
			{
				$matkhau = $_POST["matkhau"];
				$manv = $_POST["manv"];
			}

			$truyvan = "UPDATE nhanvien SET MATKHAU = '".$matkhau."' WHERE MANV = ".$manv;

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				echo "{ketqua:true}";
			}else{
				echo "{ketqua:false}";
			}

		}

		function KiemTraEmailQuenMatKhau(){
			global $conn;

			if (isset($_POST["email"])) {
				$email = $_POST['email'];
			}

			$chuoijson = array();

			$truyvan = "SELECT * FROM nhanvien WHERE TENDN = '".$email."'";

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
					$manv =0;
					while ($dong = mysqli_fetch_array($ketqua)) {
						$manv = $dong["MANV"];
					}
					echo "{ ketqua : ".$manv."}";
			}

		}

		function KiemTraMaCodeKhuyenMai(){
			global $conn;
			if(isset($_POST["macode"]) )
			{
				$macode = $_POST["macode"];
			
			}

			$chuoijson = array();

			$ngayhientai = date("Y-m-d");


			$truyvan = "SELECT *, DATEDIFF(codekhuyenmai.NGAYKETTHUC,'".$ngayhientai."') as THOIGIANKM FROM codekhuyenmai WHERE CODEKM = '".$macode."'";
			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				$sotiengiam  = 0;
				$sotientoithieu = 0; 
				while ($dong = mysqli_fetch_array($ketqua)) {

					$thoigiankm = $dong["THOIGIANKM"];
			
					if($thoigiankm>=0)
					{
						$sotiengiam = $dong["SOTIENGIAM"];
						$sotientoithieu = $dong["GIATRIDONHANGMIN"];
					}
				}

				echo "{sotiengiam : ".$sotiengiam.", sotientoithieu : ".$sotientoithieu."}" ;

			}


		}

		function KiemTraHoaDonCoKhuyenMai(){
			global $conn;

			if(isset($_POST["ngaymua"]) || isset($_POST["masp"]))
			{
				$ngaymua = $_POST["ngaymua"];
				$masp = $_POST["masp"];
			}

			$chuoijson = array();

			$truyvan = "SELECT *,DATEDIFF(km.NGAYKETTHUC,'".$ngaymua."') as THOIGIANKM FROM `chitietkhuyenmai` ctkm, `khuyenmai` km WHERE ctkm.MASP = ".$masp." AND ctkm.MAKM = km.MAKM";

			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua)
			{
				$phantramkm =0;

				while ($dong = mysqli_fetch_array($ketqua)) {

					$thoigiankm = $dong["THOIGIANKM"];
					if($thoigiankm>=0)
					{
						$phantramkm = $dong["PHANTRAMKM"];
					}
				}
				echo "{ ketqua: ".$phantramkm." }";
			}		

		}	


		function KiemTraSanPhamKhuyenMai(){
			global $conn;

			if(isset($_POST["masp"]))
			{
				$masp = $_POST["masp"];
			}

			$ngayhientai = date("Y-m-d");

			$truyvan = "SELECT *,DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') as GIOIHANKM FROM chitietkhuyenmai ctkm, khuyenmai km WHERE ctkm.MASP =".$masp." AND ctkm.MAKM = km.MAKM";

			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua)
			{
				$phantramkm = 0;
				while ($dong = mysqli_fetch_array($ketqua)) {
					$gioihankm = $dong["GIOIHANKM"];
					if($gioihankm>=0)
					{
						$phantramkm = $dong["PHANTRAMKM"];
					}
				}

				echo "{ketqua : ".$phantramkm."}";
			}
			
		}

		function LayDanhSachDanhGiaTheoMaNV(){
			global $conn;
			if(isset($_POST["manv"]))
			{
				$manv = $_POST["manv"];
			}

			$chuoijson = array();
			$truyvan = "SELECT * FROM danhgia,sanpham WHERE danhgia.MANV =".$manv." AND danhgia.MASP = sanpham.MASP";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHDANHGIA\":";
			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson, array("TENNGUOIDG"=>$dong["TENNGUOIDG"],"TIEUDE"=>$dong["TIEUDE"],"NOIDUNG"=>$dong["NOIDUNG"],"SOSAO"=>$dong["SOSAO"],"NGAYDANHGIA"=>$dong["NGAYDANHGIA"],"TENSP"=>$dong["TENSP"],"HINHLON"=>IP.$dong["HINHLON"]));
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}
			echo "}";
		}

		function HuyDonHang(){
			global $conn;

			if(isset($_POST["mahd"]))
			{
				$mahd = $_POST["mahd"];
			}

			$trangthai = "dahuy";

			$truyvan = "UPDATE hoadon SET TRANGTHAI ='".$trangthai."' WHERE MAHD =".$mahd;
			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua)
			{
				echo "{ ketqua : true }";
			}else{
				echo "{ ketqua : false }".mysqli_error($conn);
			}

		}

		function LayDanhSachHoaDonTheoTrangThai(){
			global $conn;

			if(isset($_POST["trangthai"]) || isset($_POST["manguoinhan"]))
			{
				$trangthai = $_POST["trangthai"];
				$manguoinhan = $_POST["manguoinhan"];
			}

			$chuoijson = array();

			$truyvan = "SELECT * FROM hoadon WHERE TRANGTHAI = '".$trangthai."' AND MANGUOINHAN = '".$manguoinhan."'";

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHHOADON\":";

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson, array("MAHD"=>$dong["MAHD"],"NGAYGIAO"=>$dong["NGAYGIAO"],"NGAYMUA"=>$dong["NGAYMUA"],"TRANGTHAI"=>$dong["TRANGTHAI"],"TENNGUOINHAN"=>$dong["TENNGUOINHAN"],"SODT"=>$dong["SODT"],"DIACHI"=>$dong["DIACHI"],"EMAIL"=>$dong["EMAIL"],"SOTIEN"=>$dong["SOTIEN"],"MANGUOINHAN"=>$dong["MANGUOINHAN"]));
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}

			echo "}";
		}

		function CapNhatThongTinNhanvienBangId(){
			global $conn;
			if(isset($_POST["id"]) ||isset($_POST["tennv"]) ||isset($_POST["diachi"]) || isset($_POST["ngaysinh"]) || isset($_POST["sodt"]) || isset($_POST["gioitinh"]) )
			{
				$id = $_POST["id"];
				$tennv = $_POST["tennv"];
				$diachi = $_POST["diachi"];
				$ngaysinh = $_POST["ngaysinh"];
				$sodt = $_POST["sodt"];
				$gioitinh = $_POST["gioitinh"];
				
			}

			$truyvan = "UPDATE nhanvien SET TENNV ='".$tennv."', DIACHI ='".$diachi."', NGAYSINH ='".$ngaysinh."', SODT ='".$sodt."', GIOITINH =".$gioitinh."' WHERE IDDANGNHAP ='".$id."'";
			$ketqua = mysqli_query($conn,$truyvan);
		
			if($ketqua)
			{
				echo "{ ketqua : true }";
			}else{
				echo "{ ketqua : false }".mysqli_error($conn);
			}
		}

		function CapNhatThongTinNhanVien(){
			global $conn;
			if(isset($_POST["manv"]) ||isset($_POST["tennv"]) || isset($_POST["diachi"]) || isset($_POST["ngaysinh"]) || isset($_POST["sodt"]) || isset($_POST["gioitinh"]) )
			{
				$manv = $_POST["manv"];
				$tennv = $_POST["tennv"];
				$diachi = $_POST["diachi"];
				$ngaysinh = $_POST["ngaysinh"];
				$sodt = $_POST["sodt"];
				$gioitinh = $_POST["gioitinh"];
				
			}

			$truyvan = "UPDATE nhanvien SET TENNV ='".$tennv."', DIACHI ='".$diachi."', NGAYSINH ='".$ngaysinh."', SODT ='".$sodt."', GIOITINH =".$gioitinh." WHERE MANV =".$manv;
			$ketqua = mysqli_query($conn,$truyvan);
		
			if($ketqua)
			{
				echo "{ ketqua : true }";
			}else{
				echo "{ ketqua : false }".mysqli_error($conn);
			}
			
		}

		function LayThongTinNhanVienBangIdNV(){
			global $conn;
			if(isset($_POST["id"]))
			{
				$id = $_POST["id"];
			}

			$chuoijson = array();
			$truyvan = "SELECT * FROM nhanvien WHERE IDDANGNHAP ='".$id."'";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"THONGTINNHANVIEN\":";

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					$chuoijson[] = $dong;
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}

			echo "}";

		}

		function LayThongTinNhanVienBangMaNV(){
			global $conn;
			if(isset($_POST["manv"]))
			{
				$manv = $_POST["manv"];
			}

			$chuoijson = array();
			$truyvan = "SELECT * FROM nhanvien WHERE MANV =".$manv;
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"THONGTINNHANVIEN\":";

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					$chuoijson[] = $dong;
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}

			echo "}";

		}

		function LaySanPhamChiTietHoaDon(){
			global $conn;
			if(isset($_POST["mahd"]))
			{
				$mahd = $_POST["mahd"];
			}

			$chuoijson = array();
			$truyvan = "SELECT cthd.MASP,cthd.SOLUONG,sp.TENSP,sp.GIA,sp.HINHLON FROM chitiethoadon cthd, sanpham sp WHERE MAHD = ".$mahd." AND cthd.MASP = sp.MASP";

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHSANPHAMCHITIETHOADON\":";

			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson, array("MASP"=>$dong["MASP"],"SOLUONG"=>$dong["SOLUONG"],"TENSP"=>$dong["TENSP"],"GIA"=>$dong["GIA"],"HINHLON"=>IP.$dong["HINHLON"]));
				}
			}
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";

		}


		function LayDanhSachHoaDon(){
			global $conn;
			if(isset($_POST["manguoinhan"]))
			{
				$manguoinhan = $_POST["manguoinhan"];
			}

			$chuoijson = array();

			$truyvan = "SELECT * FROM hoadon WHERE MANGUOINHAN = '".$manguoinhan."'";

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHHOADON\":";

			if($ketqua){
					while ($dong = mysqli_fetch_array($ketqua)) {
							//$chuoijson[] = $dong;

						array_push($chuoijson, array("MAHD"=>$dong["MAHD"],"NGAYMUA"=>$dong["NGAYMUA"],"TRANGTHAI"=>$dong["TRANGTHAI"],"TENNGUOINHAN"=>$dong["TENNGUOINHAN"],"SODT"=>$dong["SODT"],"DIACHI"=>$dong["DIACHI"],"EMAIL"=>$dong["EMAIL"],"SOTIEN"=>$dong["SOTIEN"],"MANGUOINHAN"=>$dong["MANGUOINHAN"]));

				}
			}
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			echo "}";

		}


		function LayDanhSachKhuyenMai(){
			global $conn;
			$chuoijson = array();

			$ngayhientai = date("Y/m/d");
			$truyvan = "SELECT * FROM khuyenmai km,loaisanpham lsp WHERE DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') >=0  AND km.MALOAISP = lsp.MALOAISP";

			echo "{";
			echo "\"DANHSACHKHUYENMAI\":";

			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					
					$truyvanchitietkhuyenmai = "SELECT * FROM chitietkhuyenmai ctkm,sanpham sp WHERE ctkm.MAKM = '".$dong["MAKM"]."' AND  ctkm.MASP = sp.MASP";
					$ketquachitietkhuyenmai = mysqli_query($conn,$truyvanchitietkhuyenmai);
					$chuoijsondanhsachsanpham = array();
					if($ketquachitietkhuyenmai)
					{
						while ($dongkhuyenmai = mysqli_fetch_array($ketquachitietkhuyenmai)){

							//$chuoijsondanhsachsanpham[] = $dongkhuyenmai;

							array_push($chuoijsondanhsachsanpham,array("MASP"=> $dongkhuyenmai["MASP"],"TENSP" => $dongkhuyenmai["TENSP"],"GIA" => $dongkhuyenmai["GIA"],"HINHLON" => $dongkhuyenmai["HINHLON"],"HINHNHO"=>$dongkhuyenmai["HINHNHO"],"SOLUONG"=> $dongkhuyenmai["SOLUONG"],"MALOAISP"=>$dongkhuyenmai["MALOAISP"],"MATH"=>$dongkhuyenmai["MATH"],"MANV"=>$dongkhuyenmai["MANV"],"LUOTMUA"=>$dongkhuyenmai["LUOTMUA"],"PHANTRAMKM"=>$dongkhuyenmai["PHANTRAMKM"]));

						}
					}

					array_push($chuoijson, array("MAKM" => $dong["MAKM"],"TENLOAISP"=> $dong["TENLOAISP"],"TENKM" => $dong["TENKM"],"HINHKHUYENMAI" => $dong["HINHKHUYENMAI"],"DANHSACHSANPHAM"=>$chuoijsondanhsachsanpham));

				}

				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}

			echo "}";

		}

		function KiemTraCoPhaiLaThuongHieuLon(){
			global $conn;
			$chuoijson = array();

			if(isset($_POST["math"]) || isset($_POST["tenth"]))
			{
				$math = $_POST["math"];
				$tenth = $_POST["tenth"];
			}

			$truyvan = "SELECT * FROM  thuonghieu WHERE MATH = ".$math." and TENTH = '".$tenth."'";

			echo "{";
			echo "\"THUONGHIEULON\" :";

			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					$chuoijson[] = $dong;
				}

				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}

			echo "}";

		}

		function LayDanhSachSanPhamThongQuaLoaiSanPhamNhaCuaVaDoiSong(){
			global $conn;

			if(isset($_POST["maloaisanpham"]))
			{
				$maloaisanpham = $_POST["maloaisanpham"];
			}

			$truyvan = "SELECT * FROM sanpham WHERE MALOAISP = " .$maloaisanpham;
			$ketqua = mysqli_query($conn,$truyvan);
			$chuoijson = array();

			echo "{";
			echo "\"DANHSACHSANPHAMNHACUAVADOISONG\" :";
			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					//$chuoijson[] = $dong;

					array_push($chuoijson, array("MASP" => $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"],"HINHLON" => IP.$dong["HINHLON"],"HINHNHO" => IP.$dong["HINHNHO"],"THONGTIN" => $dong["THONGTIN"],"MALOAISP" => $dong["MALOAISP"],"LUOTMUA" =>$dong["LUOTMUA"]));

				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}
			echo "}";

		}

		function LayDanhSachLoaiSanPhamNhaCuaVaDoiSong(){
			global $conn;
			if(isset($_POST["maloaicha"]))
			{
				$maloaicha = $_POST["maloaicha"];
			}
			$truyvan = "SELECT * From loaisanpham WHERE MALOAI_CHA = " .$maloaicha; 
			$ketqua = mysqli_query($conn,$truyvan);
			$chuoijson = array();
			echo "{";
			echo "\"DANHSACHLOAISANPHAMNHACUAVADOISONG\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			if($ketqua)
			{
				while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
				 {

				 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
						$chuoijson[] = $dong;
				 
					//array_push($chuoijson, array("TENLOAISP" => $dong["TENLOAISP"],"MALOAISP" => $dong["MALOAISP"])); //chuyển các dữ liệu về dạng JSON chuẩn
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			}
			echo "}";

		}

		function ThemHoaDon(){
			global $conn;

			if(isset($_POST["danhsachsanpham"]) || isset($_POST["tennguoinhan"]) || isset($_POST["sodt"]) || isset($_POST["diachi"]) || isset($_POST["chuyenkhoan"]) || isset($_POST["email"]) || isset($_POST["sotien"]) || isset($_POST["manguoinhan"])){
				$danhsachsanpham = $_POST["danhsachsanpham"];
				$tennguoinhan = $_POST["tennguoinhan"];
				$sodt = $_POST["sodt"];
				$diachi = $_POST["diachi"];
				$chuyenkhoan = $_POST["chuyenkhoan"];
				$email = $_POST["email"];
				$sotien = $_POST["sotien"];
				$manguoinhan = $_POST["manguoinhan"];
			
			}

			$ngayhientai = date("Y-m-d"); // get ngày hiện tại

			//  format để cộng ngày hiện tại lên 3 ngày để thành ngày giao hàng
			$ngaygiao = date_create($ngayhientai);
			$ngaygiao = date_modify($ngaygiao,"+3 days");
			$ngaygiao = date_format($ngaygiao,"Y-m-d");
			
			$trangthai = "chokiemduyet";

			$truyvan = "INSERT INTO hoadon (NGAYMUA,NGAYGIAO,TRANGTHAI,TENNGUOINHAN,SODT,DIACHI,CHUYENKHOAN,EMAIL,SOTIEN,MANGUOINHAN) VALUES ('".$ngayhientai."', '".$ngaygiao."', '".$trangthai."', '".$tennguoinhan."', '".$sodt."', '".$diachi."', '".$chuyenkhoan."', '".$email."', '".$sotien."', '".$manguoinhan."')";
			$ketqua = mysqli_query($conn,$truyvan);


			if($ketqua)
			{
				$mahd = mysqli_insert_id($conn); // Get Id vừa mới được tự động tăng (Hàm chỉ hoạt động cho các trường tự động tăng)
				$chuoijsonandroid = json_decode($danhsachsanpham); // parse từ chuối json về dạng mảng json
				$arrayDanhSachSanPham = $chuoijsonandroid ->DANHSACHSANPHAM;
				$dem = count($arrayDanhSachSanPham);


				// Lấy ra từng đối tượng trong  arrayDanhSachSanPham
				for($i=0; $i<$dem; $i++)
				{
					$jsonObject = $arrayDanhSachSanPham[$i];

					$masp = $jsonObject->masp;
					$soluong = $jsonObject->soluong;
					$truyvan = "INSERT INTO chitiethoadon (MAHD,MASP,SOLUONG) VALUES ('".$mahd."', '".$masp."', '".$soluong."')";
					$ketqua1 = mysqli_query($conn,$truyvan);
				}


				echo "{ketqua:true}" ;

			}
			else{
				echo "{ketqua:false}";	
			}

		}

		function LayDanhSachDanhGiaTheoMaSP(){
				global $conn;

				$chuoijson = array();

				if(isset($_POST["masp"]) || isset($_POST["limit"]))
				{ 
					$masp = $_POST["masp"];
					$limit = $_POST["limit"];
				}

				$truyvan = "SELECT * FROM danhgia WHERE MASP = ".$masp."  ORDER BY NGAYDANHGIA LIMIT  ".$limit.",10";

				$ketqua = mysqli_query($conn,$truyvan);

				echo "{";
				echo "\"DANHSACHDANHGIA\":";

				if($ketqua){
					while ($dong = mysqli_fetch_array($ketqua)) {
							$chuoijson[] = $dong;
					}
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
				echo "}";
		}


		function ThemDanhGia(){
			global $conn;
	
			if(isset($_POST["madg"]) || isset($_POST["masp"]) || isset($_POST["tennguoidanhgia"]) || isset($_POST["tieude"]) || isset($_POST["noidung"]) || isset($_POST["sosao"]) || isset($_POST["manv"])){
				$madg = $_POST["madg"];
				$masp = $_POST["masp"];
				$tenthietbi = $_POST["tennguoidanhgia"];
				$tieude = $_POST["tieude"];
				$noidung = $_POST["noidung"];
				$sosao = $_POST["sosao"];
				$manv = $_POST["manv"];
			}


			$ngaydang = date("d/m/Y");

			$truyvan = "INSERT INTO danhgia (MADG,MASP,TENNGUOIDG,TIEUDE,NOIDUNG,SOSAO,NGAYDANHGIA,MANV) VALUES ('".$madg."', '".$masp."', '".$tenthietbi."', '".$tieude."', '".$noidung."', '".$sosao."', '".$ngaydang."',".$manv." )";

			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua)
			{
				echo "{ketqua:true}";
			}
			else
			{
				echo "{ketqua:false}".mysqli_error($conn);
			}
		}

		function LaySanPhamVschiTietTheoMaSP(){
			global $conn;

			$chuoijson = array();
			$chuoijsonchitiet = array(); // lấy các thông số sản phẩm

			if(isset($_POST["masp"]))
			{
				$masp = $_POST["masp"];
			
			}


			$truyvan = "SELECT * FROM sanpham sp , nhanvien nv WHERE MASP = ".$masp." AND sp.MANV = nv.MANV" ;

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"CHITIETSANPHAM\" :";

			$truyvanchitiet = "SELECT * FROM chitietsanpham WHERE MASP = ".$masp ;

			$ketquachitiet = mysqli_query($conn,$truyvanchitiet);


			if($ketquachitiet)
			{
				while ($dongchitiet = mysqli_fetch_array($ketquachitiet)) {
					
						array_push($chuoijsonchitiet,array($dongchitiet["TENCHITIET"] => $dongchitiet["GIATRI"]));

				}
			}


			if($ketqua)
			{
				while ($dong = mysqli_fetch_array($ketqua)) {
					array_push($chuoijson,array("MASP"=>$dong["MASP"], "TENSP"=>$dong["TENSP"], "GIATIEN"=>$dong["GIA"], "SOLUONG"=>$dong["SOLUONG"]
						,"ANHNHO"=>$dong["HINHNHO"], "THONGTIN"=>$dong["THONGTIN"], "MALOAISP"=>$dong["MALOAISP"], "MATHUONGHIEU"=>$dong["MATH"]
						,"MANV"=>$dong["MANV"],"TENNV" => $dong["TENNV"] ,"LUOTMUA"=>$dong["LUOTMUA"],"THONGSOKYTHUAT"=>$chuoijsonchitiet));
				}
			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			echo "}";


		}

		// Lấy danh sách tất cả sản phẩm theo mã thương hiệu

		function LayDanhSachTatCaSanPhamTheoMaThuongHieu(){
			global $conn;
			$chuoijson = array();

			if(isset($_POST["maloaisp"]))
			{
				$maloai = $_POST["maloaisp"];
			}

			$truyvan = "SELECT * FROM sanpham WHERE MATH = " .$maloai;

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHSANPHAM\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			if($ketqua){
				while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
				 {

				 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
					//	$chuoijson[] = $dong;
				 	

					array_push($chuoijson, array("MASP" => $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"],"HINHLON" => IP.$dong["HINHLON"],"HINHNHO" => IP.$dong["HINHNHO"],"THONGTIN" => $dong["THONGTIN"],"MALOAISP" => $dong["MALOAISP"],"LUOTMUA" =>$dong["LUOTMUA"]));
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			}
			echo "}";

		}

		function LayDanhSachSanPhamTheoMaThuongHieu(){
			global $conn;
			$chuoijson = array();

			if(isset($_POST["maloaisp"]) || $_POST["limit"])
			{
				$maloai = $_POST["maloaisp"];
				$limit = $_POST["limit"];
			}

			$chuoijson =  LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloai,$chuoijson,$limit);

			echo "{";
			echo "\"DANHSACHSANPHAM\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			echo "}";

		}


		// Lấy tất cả sán phẩm theo danh sach phụ kiện
		function LayDanhSachTatCaSanPhamTheoMaLoaiDanhMuc(){
			global $conn;
			$chuoijson = array();

			if(isset($_POST["maloaisp"]))
			{
				$maloai = $_POST["maloaisp"];
			}

			$truyvan = "SELECT * FROM sanpham WHERE MALOAISP = " .$maloai;

			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHSANPHAM\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			if($ketqua){
				while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
				 {

				 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
					//	$chuoijson[] = $dong;
				 	

					array_push($chuoijson, array("MASP" => $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"],"HINHLON" => IP.$dong["HINHLON"],"HINHNHO" => IP.$dong["HINHNHO"],"THONGTIN" => $dong["THONGTIN"],"MALOAISP" => $dong["MALOAISP"],"LUOTMUA" =>$dong["LUOTMUA"]));
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			}
			echo "}";

		}


		// Danh sách các phụ kiện
		function LayDanhSachSanPhamTheoMaLoaiDanhMuc(){
			global $conn;
			$chuoijson = array();

			if(isset($_POST["maloaisp"]) || $_POST["limit"])
			{
				$maloai = $_POST["maloaisp"];
				$limit = $_POST["limit"];
			}

			echo "{";
			echo "\"DANHSACHSANPHAM\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			$chuoijson = LayDanhSachSanPhamTheoMaLoai($conn,$maloai,$chuoijson,$limit);
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			echo "}";

		}

		//lay danh sach san pham theo thuong hieu
		function LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloaith,$chuoijson,$limit){
			
			$truyvantienich = "SELECT *  FROM thuonghieu th, sanpham sp WHERE th.MATH = ".$maloaith." AND th.MATH = sp.MATH ORDER BY sp.LUOTMUA DESC LIMIT ".$limit.",20"; // lấy ra 20 dòng bắt đầu từ dòng (limit +1)																							
			
			$ketquacon = mysqli_query($conn,$truyvantienich);	
					
			if($ketquacon){
				while ($dongtienich = mysqli_fetch_array($ketquacon)) {
					array_push($chuoijson, array("MASP"=>$dongtienich["MASP"],'TENSP' => $dongtienich["TENSP"],'GIATIEN'=>$dongtienich["GIA"],'HINHSANPHAM'=>IP.$dongtienich["HINHLON"],'HINHSANPHAMNHO'=>IP.$dongtienich["HINHNHO"]));
					
				}
			}

			return $chuoijson;

		}



		function LayDanhSachSanPhamTheoMaLoai($conn,$maloaisp,$chuoijson,$limit){
			
			$truyvantienich = "SELECT *  FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAISP = ".$maloaisp." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT ".$limit.",20";
			
			$ketquacon = mysqli_query($conn,$truyvantienich);	
					
			if($ketquacon){
				while ($dongtienich = mysqli_fetch_array($ketquacon)) {
					array_push($chuoijson, array("MASP"=>$dongtienich["MASP"],'TENSP' => $dongtienich["TENSP"],'GIATIEN'=>$dongtienich["GIA"],'HINHSANPHAM'=>IP.$dongtienich["HINHLON"],'HINHSANPHAMNHO'=>IP.$dongtienich["HINHNHO"]));
					
				}
			}

			return $chuoijson;

		}


		function LayDanhSachSanPhamMoiVe(){
				global $conn;

				$truyvancha = "SELECT * FROM sanpham  ORDER BY MASP DESC LIMIT 20";
				$ketqua = mysqli_query($conn,$truyvancha);
				$chuoijson = array();
				echo "{";
				echo "\"DANHSACHSANPHAMMOIVE\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
				if($ketqua){
					while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
					 {

					 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
						//	$chuoijson[] = $dong;
					 	

						array_push($chuoijson, array("MASP" => $dong["MASP"],"TENSP" => $dong["TENSP"],"GIATIEN" => $dong["GIA"],"HINHSANPHAM" => IP.$dong["HINHLON"])); //chuyển các dữ liệu về dạng JSON chuẩn
					}
					echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
				}
				echo "}";

		}


		function LayLogoThuongHieuLon(){
			global $conn;

		$truyvancha = "SELECT * FROM thuonghieu";
		$ketqua = mysqli_query($conn,$truyvancha);
		$chuoijson = array();
		echo "{";
		echo "\"DANHSACHLOGOTHUONGHIEU\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
		if($ketqua){
			while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
			 {

			 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
				//	$chuoijson[] = $dong;
			 	

				array_push($chuoijson, array("MASP" => $dong["MATH"],"TENSP" => $dong["TENTH"],"HINHSANPHAM" => IP.$dong["HINHTHUONGHIEU"])); //chuyển các dữ liệu về dạng JSON chuẩn
			}
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
		}
		echo "}";
		}


		function LayDanhSachPhuKien(){
			global $conn;

			//lấy danh sách phụ kiện cha
			$truyvancha = "SELECT *  FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%'";
			$ketqua = mysqli_query($conn,$truyvancha);
			$chuoijson = array();

			echo "{";
			echo "\"DANHSACHPHUKIEN\":";
			if($ketqua){
				while ($dong=mysqli_fetch_array($ketqua)) {
					
					//Lấy danh sách phụ kiện con
					$truyvanphukiencon = "SELECT *  FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAI_CHA = ".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
			
					$ketquacon = mysqli_query($conn,$truyvanphukiencon);	
					
					if($ketquacon){
						while ($dongphukiencon = mysqli_fetch_array($ketquacon)) {
							array_push($chuoijson, array("MASP"=>$dongphukiencon["MALOAISP"],'TENSP' => $dongphukiencon["TENLOAISP"],'GIATIEN'=>$dongphukiencon["GIA"],'HINHSANPHAM'=>IP.$dongphukiencon["HINHLON"]));
					
						}
					}
					
				}
			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

		function LayDanhSachTopPhuKien(){
			global $conn;

			//lấy danh sách phụ kiện cha
			$truyvancha = "SELECT *  FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%'";
			$ketqua = mysqli_query($conn,$truyvancha);
			$chuoijson = array();

			echo "{";
			echo "\"TOPPHUKIEN\":";
			if($ketqua){
				while ($dong=mysqli_fetch_array($ketqua)) {
					
					//Lấy danh sách phụ kiện con
					$truyvanphukiencon = "SELECT *  FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAI_CHA = ".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10 ";
			
					$ketquacon = mysqli_query($conn,$truyvanphukiencon);	
					
					if($ketquacon){
						while ($dongphukiencon = mysqli_fetch_array($ketquacon)) {
							array_push($chuoijson, array("MASP"=>$dongphukiencon["MASP"],'TENSP' => $dongphukiencon["TENSP"],'GIATIEN'=>$dongphukiencon["GIA"],'HINHSANPHAM'=>IP.$dongphukiencon["HINHLON"]));
					
						}
					}
					
				}

				
			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}


	function LayDanhSachTopDienThoaiVaMayTinhBang(){
		global $conn;

		// Truy vấn điện thoại

		$truyvancha = "SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.TENLOAISP LIKE 'điện thoại%' AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
		$ketqua = mysqli_query($conn,$truyvancha);
		$chuoijson = array();
		echo "{";
		echo "\"DANHSACHTOPSANPHAMVAMAYTINHBANG\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
		if($ketqua){
			while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
			 {

			 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
				//	$chuoijson[] = $dong;
			 	 

				array_push($chuoijson, array("MASP" => $dong["MASP"],"TENSP" => $dong["TENSP"],"GIATIEN" => $dong["GIA"],"HINHSANPHAM" => IP.$dong["HINHLON"])); //chuyển các dữ liệu về dạng JSON chuẩn
			}
			

		// Truy vấn các sản phẩm là máy tính bảng
		$truyvancha = "SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.TENLOAISP LIKE 'máy tính bảng%' AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
		$ketquamtb = mysqli_query($conn,$truyvancha);
		if($ketquamtb){
			while ( $dongmtb = mysqli_fetch_array($ketquamtb))  // lấy danh sách từng thành phần trong mảng
			 {

			 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
				//	$chuoijson[] = $dong;
			 	

				array_push($chuoijson, array("MASP" => $dongmtb["MATH"],"TENSP" => $dongmtb["TENTH"],"GIATIEN" => $dongmtb["GIA"],"HINHSANPHAM" =>IP.$dongmtb["HINHLON"])); //chuyển các dữ liệu về dạng JSON chuẩn
			}

			
		}
		echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
		echo "}";
	}
	}


	function LayDanhSachCacThuongHieuLon(){
		global $conn;

		$truyvancha = "SELECT * FROM thuonghieu th,chitietthuonghieu ctth WHERE th.MATH = ctth.MATH";
		$ketqua = mysqli_query($conn,$truyvancha);
		$chuoijson = array();
		echo "{";
		echo "\"DANHSACHTHUONGHIEU\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
		if($ketqua){
			while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
			 {

			 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
				//	$chuoijson[] = $dong;
			 	

				array_push($chuoijson, array("MASP" => $dong["MATH"],"TENSP" => $dong["TENTH"],"HINHSANPHAM" =>IP.$dong["HINHLOAISPTH"])); //chuyển các dữ liệu về dạng JSON chuẩn
			}
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
		}
		echo "}";

	}


	 function KiemTraDangNhap(){
	 	global $conn;
	 	if(isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]))
	 	{
	 		$tendangnhap = $_POST["tendangnhap"];
			$matkhau = $_POST["matkhau"];
	 	}
	 	$truyvan ="SELECT * FROM nhanvien WHERE TENDN = '".$tendangnhap."' AND MATKHAU = '".$matkhau."'";
	 	$ketqua = mysqli_query($conn,$truyvan);
	 	$demdong = mysqli_num_rows($ketqua); // dem' so' dong`
	 	if($demdong>=1)
	 	{
	 		$tenNV;
	 		$soDT;
	 		$maNV;
	 		while ($dong = mysqli_fetch_array($ketqua)) {
	 			$tenNV = $dong["TENNV"];
	 			$soDT  = $dong["SODT"];
	 			$maNV = $dong["MANV"];
	 			$diaChi = $dong["DIACHI"];
	 		}echo "{ ketqua : true, tennv : \"".$tenNV."\", sodt : \"".$soDT."\", manv : \"".$maNV."\", diachi :\"".$diaChi."\"}";
	 			
	 	}
	 	else
	 	{
	 		echo "{ ketqua : false }".mysqli_error($conn);
	 	}

	 }	

	 function KiemTraThanhVienDaTonTai(){
	 	global $conn;
	 	if(isset($_POST["id"]))
	 	{
	 		$id = $_POST["id"];
	 	}
	 	$truyvan = "SELECT * FROM nhanvien WHERE IDDANGNHAP = '".$id."'";
	 	$ketqua = mysqli_query($conn,$truyvan);
	 	$demdong = mysqli_num_rows($ketqua); //
	 	if($demdong>=1)
	 	{
	 		$tenNV;
	 		$soDT;
	 		$maNV;
	 		while ($dong = mysqli_fetch_array($ketqua)) {
	 			$tenNV = $dong["TENNV"];
	 			$soDT  = $dong["SODT"];
	 			$maNV = $dong["MANV"];
	 		}
	 		echo "{ ketqua : true, tennv : \"".$tenNV."\" }";	
	 	}
	 	else
	 	{
	 		echo "{ ketqua : false }".mysqli_error($conn);
	 	}


	 }

	function DangKyThanhVien(){
				global $conn;
				if(isset($_POST["tennv"]) || isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]) || isset($_POST["maloainv"]) || isset($_POST["emaildocquyen"]) || isset($_POST["sodt"]) || isset($_POST["gioitinh"]) || isset($_POST["id"])){
					$tennv = $_POST["tennv"];
					$tendangnhap = $_POST["tendangnhap"];
					$matkhau = $_POST["matkhau"];
					$maloainv = $_POST["maloainv"];
					$emaildocquyen = $_POST["emaildocquyen"];
					$sodt = $_POST["sodt"];
					$gioitinh = $_POST["gioitinh"];
					$id = $_POST["id"];
				}
				
				$truyvan = "INSERT INTO nhanvien (TENNV,TENDN,MATKHAU,SODT,MALOAINV,EMAILDOCQUYEN,GIOITINH,IDDANGNHAP) VALUES ('".$tennv."','".$tendangnhap."','".$matkhau."','".$sodt."','".$maloainv."','".$emaildocquyen."',".$gioitinh.",'".$id."')";

				if(mysqli_query($conn,$truyvan)){
					echo "{ ketqua : true }";
				}else{
					echo "{ ketqua : false }".mysqli_error($conn);
					
				}

				mysqli_close($conn);

			}

	function LayDanhSachMenu(){

		global $conn;

		$maloaicha = $_POST["maloaicha"]; // get là lấy trực tiếp , post là truyền ngầm

			$truyvancha = "SELECT * FROM loaisanpham WHERE MALOAI_CHA = " .$maloaicha;
			$ketqua = mysqli_query($conn,$truyvancha);
			$chuoijson = array();
			echo "{";
			echo "\"LOAISANPHAM\" :"; // \"\" dùng để thêm dấu "" cho tên của mảng
			if($ketqua){
				while ( $dong = mysqli_fetch_array($ketqua))  // lấy danh sách từng thành phần trong mảng
				 {

				 	// Cách 1 để get và lấy dữ liệu từ mysql và đưa về dạng json
						$chuoijson[] = $dong;
				 	

					//array_push($chuoijson, array("TENLOAISP" => $dong["TENLOAISP"],"MALOAISP" => $dong["MALOAISP"])); //chuyển các dữ liệu về dạng JSON chuẩn
				}
				echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE); // Để get được dữ liệu dạng String
			}
			echo "}";
			//mysql_close($conn);
	}

?>