package com.MMT_Shop.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class ChuyenDoi {

    // public static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public static String layNgayString(Date ngay) {
        return formatDate.format(ngay);
    }

    public static Date layNgayDate(String ngay) {
        try {
            return formatDate.parse(ngay);
        } catch (Exception e) {
            System.out.println("lỗi chuyển đổi String sang ngày");
            //System.out.print(e);
            return null;
        }
    }

    public static LocalDate layNgayLocalDate(Date date) {
        LocalDate a;
        try {
            return a = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            System.out.println("lỗi chuyển đổi Date sang LocalDate");
            return null;
        }

    }

    public static String SoSangTien(String tien) {
        String formattedCurrency;
        try {
            double number = Double.parseDouble(tien);
            Locale locale = new Locale("vi", "VN"); // Định dạng tiền tệ Việt Nam
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            return formattedCurrency = currencyFormatter.format(number);
        } catch (Exception e) {
            System.out.println("lỗi chuyển đổi tiền");
            return null;
        }

    }

    public static Double TienSangSo(String tien) {
        double amount = 0;
        try {
            Locale locale = new Locale("vi", "VN"); // Định dạng tiền tệ Việt Nam
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            return amount = currencyFormatter.parse(tien).doubleValue();
        } catch (ParseException e) {
            System.out.println("Lỗi chuyển đổi tiền");
            return null;
        }
    }
}
