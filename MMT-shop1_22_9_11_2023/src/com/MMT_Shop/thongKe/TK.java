/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.MMT_Shop.thongKe;

import com.MMT_Shop.EnTiTy.*;

/**
 *
 * @author DELL
 */
public class TK {
    private float Doanhthu;
    private int donTC;
    private int thang;

    public TK() {
    }

    public TK(float Doanhthu, int donTC, int thang) {
        this.Doanhthu = Doanhthu;
        this.donTC = donTC;
        this.thang = thang;
    }

    public float getDoanhthu() {
        return Doanhthu;
    }

    public void setDoanhthu(float Doanhthu) {
        this.Doanhthu = Doanhthu;
    }

    public int getDonTC() {
        return donTC;
    }

    public void setDonTC(int donTC) {
        this.donTC = donTC;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }
    
    
            
}
