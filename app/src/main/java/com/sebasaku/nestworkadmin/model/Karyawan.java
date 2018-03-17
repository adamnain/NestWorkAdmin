package com.sebasaku.nestworkadmin.model;

/**
 * Created by adam on 2/23/18.
 */

public class Karyawan {
    private String namaKaryawan;
    private int avaKaryawan;

    public Karyawan(String namaKaryawan, int avaKaryawan) {
        this.namaKaryawan = namaKaryawan;
        this.avaKaryawan = avaKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public int getAvaKaryawan() {
        return avaKaryawan;
    }
}
