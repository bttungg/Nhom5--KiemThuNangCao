package com.MMT_Shop.thongKe;

import com.MMT_Shop.EnTiTy.SanPham;
import com.MMT_Shop.EnTiTy.SanPhamCT;

import com.MMT_Shop.utility.DBcontext;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author DELL
 */
public class ThongKeService {

    public ArrayList<SanPhamCT> selectSanPhamCT() {
        ArrayList<SanPhamCT> data = new ArrayList<>();
        String selectAllSql = " select top 5 chiTietSanPhamID, count(chiTietSanPhamID),"
                + "CTSP.maCTSP as ma , SanPham.ten as ten , ThuongHieu.ten as thuonghieu,"
                + " KichThuoc.ten as kichthuoc,ChatLieu.ten as chatlieu, MauSac.ten as mausac,"
                + " HoaDonChiTiet.soLuong, HoaDonChiTiet.gia from HoaDonChiTiet\n"
                + "join CTSP on CTSP.id = HoaDonChiTiet.chiTietSanPhamID\n"
                + "join  ChatLieu on  CTSP.chatLieuID = ChatLieu.id\n"
                + "join KichThuoc on CTSP.kichThuocID = KichThuoc.id\n"
                + "join MauSac on CTSP.mauSacID = MauSac.id \n"
                + "join ThuongHieu  on CTSP.thuongHieuID = ThuongHieu.id\n"
                + "join SanPham on CTSP.sanPhamID = SanPham.id\n"
                + "group by chiTietSanPhamID,CTSP.maCTSP, SanPham.ma,SanPham.ten, "
                + " ThuongHieu.ten, KichThuoc.ten,ChatLieu.ten, MauSac.ten, HoaDonChiTiet.soLuong, HoaDonChiTiet.gia \n"
                + "order by chiTietSanPhamID desc";

        try (Connection cn = DBcontext.getConnection(); PreparedStatement ps = cn.prepareStatement(selectAllSql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPhamCT sanPhamCT = new SanPhamCT();
//                sanPhamCT.setMaSPCT(rs.getString("maCTSP"));
                sanPhamCT.setTenSP(rs.getString("ten"));
                sanPhamCT.setThuongHieu(rs.getString("thuongHieu"));
                sanPhamCT.setKichThuoc(rs.getString("kichThuoc"));
                sanPhamCT.setChatLieu(rs.getString("chatLieu"));
                sanPhamCT.setMauSac(rs.getString("MauSac"));
                sanPhamCT.setGia(rs.getFloat("gia"));
                sanPhamCT.setSoLuong(rs.getInt("soLuong"));

                data.add(sanPhamCT);
            }

        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<SanPham> SelectSanPham1() {
        ArrayList<SanPham> data = new ArrayList<>();
        String Selete_All_sql = "select SanPham.id as 'id',ma, ten,sum(CTSP.soLuong) as 'soLuong',\n"
                + "SanPham.Created_At,SanPham.Updated_At,SanPham.Created_By,\n"
                + "SanPham.Updated_By,SanPham.Deleted  from SanPham \n"
                + "left join CTSP on CTSP.sanPhamID=SanPham.id \n"
                + "group by sanPhamID, SanPham.id,ma,ten,SanPham.Created_At,SanPham.Updated_At,SanPham.Created_By,\n"
                + "SanPham.Updated_By,SanPham.Deleted\n";

        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(Selete_All_sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham h = new SanPham();
                h.setId(rs.getInt("id"));
                h.setMa(rs.getString("ma"));
                h.setTen(rs.getString("ten"));
                h.setSoLuong(rs.getInt("soLuong"));
                h.setCreated_At(rs.getString("Created_At"));
                h.setUpdated_At(rs.getString("Updated_At"));
                h.setCreated_By(rs.getString("Created_By"));
                h.setUpdated_By(rs.getString("Updated_By"));
                h.setDeleted(rs.getString("Deleted"));
                data.add(h);
            }

        } catch (Exception e) {
            System.out.print(e);
        }
        return data;
    }

    public ArrayList<SanPhamCT> SelectSanPhamCTTheoTT(String thuocTinh) {
        ArrayList<SanPhamCT> data = new ArrayList<>();
        String Selete_All_sql = "select  CTSP.id as 'id', maCTSP,SanPham.ten as'sanPham',\n"
                + "KichThuoc.ten as'kichThuoc',chatLieu.ten as'chatLieu',\n"
                + "MauSac.ten as 'mauSac',HinhAnh.ten as 'hinhAnh',\n"
                + "anhQRID,ThuongHieu.ten as 'thuongHieu',\n"
                + "KhungGiuong.ten as 'loaiKhung',gia,soLuong,trangThai,\n"
                + "CTSP.Created_At,CTSP.Updated_At,CTSP.Created_By,\n"
                + "CTSP.Updated_By,CTSP.Deleted  from CTSP \n"
                + "join SanPham on CTSP.sanPhamID = SanPham.id \n"
                + "join KichThuoc on CTSP.kichThuocID= KichThuoc.id\n"
                + "join ChatLieu on CTSP.chatLieuID= ChatLieu.id \n"
                + "join MauSac on CTSP.mauSacID = MauSac.id \n"
                + "join HinhAnh on CTSP.hinhAnhID = HinhAnh.id\n"
                + "join ThuongHieu on CTSP.thuongHieuID = ThuongHieu.id\n"
                + "join KhungGiuong on CTSP.loaiKhungID = KhungGiuong.id\n"
                + "where sanPhamID =(select id from SanPham where ten like ?) \n"
                + "or thuongHieuID = (select id from ThuongHieu where ten like  ?)\n"
                + "or loaiKhungID = (select id from KhungGiuong where ten like  ?)\n"
                + "or chatLieuID = (select id from ChatLieu where ten like ?)\n"
                + "or kichThuocID = (select id from KichThuoc where ten like  ?)\n"
                + "or mauSacID = (select id from MauSac where ten like  ?)\n"
                + "or maCTSP like ?";

        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(Selete_All_sql);
            ps.setString(1, thuocTinh);
            ps.setString(2, thuocTinh);
            ps.setString(3, thuocTinh);
            ps.setString(4, thuocTinh);
            ps.setString(5, thuocTinh);
            ps.setString(6, thuocTinh);
            ps.setString(7, thuocTinh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamCT h = new SanPhamCT();
                h.setId(rs.getInt("id"));
                h.setMaSPCT(rs.getString("maCTSP"));
                h.setTenSP(rs.getString("sanPham"));
                h.setKichThuoc(rs.getString("kichThuoc"));
                h.setChatLieu(rs.getString("chatLieu"));
                h.setMauSac(rs.getString("mauSac"));
                h.setHinhAnh(rs.getString("hinhAnh"));
                h.setAnhQR(rs.getString("anhQRID"));
                h.setThuongHieu(rs.getString("thuongHieu"));
                h.setLoaiKhung(rs.getString("loaiKhung"));
                h.setGia(rs.getFloat("gia"));
                h.setSoLuong(rs.getInt("soLuong"));
                h.setTrangThai(rs.getBoolean("trangThai"));
                h.setCreated_At(rs.getString("Created_At"));
                h.setUpdated_At(rs.getString("Updated_At"));
                h.setCreated_By(rs.getString("Created_By"));
                h.setUpdated_By(rs.getString("Updated_By"));
                h.setDeleted(rs.getString("Deleted"));
                data.add(h);
            }

        } catch (Exception e) {
            System.out.print(e);
        }
        return data;
    }

    public int countKHbm(int thg) {
        int sl = 0;

        String sql = "select count(*) from KhachHang where month(Created_At)=?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, thg);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countKHby(int nam) {
        int sl = 0;

        String sql = "select count(*) from KhachHang where year(Created_At)=?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, nam);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countTCbm(int thg) {
        int sl = 0;

        String sql = "select count(*) from HoaDon where trangThai like N'Đã hoàn thành' and  month(Created_At)=?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, thg);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countTCby(int nam) {
        int sl = 0;

        String sql = "select count(*) from HoaDon where trangThai like N'Đã hoàn thành' and year(Created_At)=?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, nam);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countDTbm(int thg) {
        int sl = 0;

        String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE MONTH(Created_At) = ? and trangThai like N'Đã hoàn thành'";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, thg);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countDTby(int nam) {
        int sl = 0;

        String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE YEAR(Created_At) = ? and trangThai like N'Đã hoàn thành'";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, nam);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }
//

    public int countKHbt(String start, String end) {
        int sl = 0;

        String sql = "select count(*) from KhachHang where Created_At BETWEEN ? AND ?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, start);
            pstm.setString(2, end);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countDTbt(String start, String end) {
        int sl = 0;

        String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE Created_At BETWEEN ? AND ? and trangThai like N'Đã hoàn thành'";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, start);
            pstm.setString(2, end);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public int countTCbt(String start, String end) {
        int sl = 0;

        String sql = "select count(*) from HoaDon where trangThai like N'Đã hoàn thành' and Created_At BETWEEN ? AND ?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, start);
            pstm.setString(2, end);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sl = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sl;
    }

    public ArrayList<TK> BarChartTest(int nam) {
        ArrayList<TK> tk = new ArrayList<>();
        String sql = "SELECT SUM(tongTien) as 'TongTien', count(id) as SL, month(Created_At) as 'Thang'  FROM HoaDon\n"
                + " WHERE Year(Created_At) = ? and trangThai like N'Đã hoàn thành'\n"
                + "  GROUP BY Created_At\n"
                + "  ORDER BY Created_At";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, nam);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TK ntk = new TK(rs.getFloat(1), rs.getInt(2), rs.getInt(3));
                tk.add(ntk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tk;
    }

    public ArrayList<TK> BarChartTest2(int thg) {
        ArrayList<TK> tk = new ArrayList<>();
        String sql = "SELECT SUM(tongTien), count(HoaDon.ma), Day(Created_At) FROM HoaDon"
                + " WHERE Month(Created_At) = ? and trangThai like N'Đã hoàn thành'"
                + " GROUP BY Created_At"
                + " ORDER BY Created_At";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, thg);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TK ntk = new TK(rs.getFloat(1), rs.getInt(2), rs.getInt(3));
                tk.add(ntk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tk;
    }

    public ArrayList<TK> BarChartTest3(String Start, String End) {
        ArrayList<TK> tk = new ArrayList<>();
        String sql = "SELECT SUM(tongTien), count(id), month(Created_At) FROM HoaDon"
                + " WHERE Created_At BETWEEN ? AND ? and trangThai like N'Đã hoàn thành'"
                + " GROUP BY month(Created_At)";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, Start);
            pstm.setString(2, End);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TK ntk = new TK(rs.getFloat(1), rs.getInt(2), rs.getInt(3));
                tk.add(ntk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tk;
    }
}
