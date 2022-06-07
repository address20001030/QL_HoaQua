package com.example.ql_hoaqua;

public class HoaQua {

    private int id;
    private String name;
    private String loai;
    private String dvt;
    private int dongia;
    private String nsx;

    public HoaQua() {
    }

    public HoaQua(String name, String loai, String dvt, int dongia, String nsx) {
        this.name = name;
        this.loai = loai;
        this.dvt = dvt;
        this.dongia = dongia;
        this.nsx = nsx;
    }

    public HoaQua(int id, String name, String loai, String dvt, int dongia, String nsx) {
        this.id = id;
        this.name = name;
        this.loai = loai;
        this.dvt = dvt;
        this.dongia = dongia;
        this.nsx = nsx;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }
}
