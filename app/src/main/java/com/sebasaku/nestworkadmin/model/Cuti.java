package com.sebasaku.nestworkadmin.model;

/**
 * Created by adamnain on 3/15/18.
 */

public class Cuti {
    private String namaKaryawan;
    private String mulaiCuti;
    private String akhirCuti;
    private String keterangan;
    private String statusCuti;
    private int avaKaryawan;


    public Cuti(String namaKaryawan, String mulaiCuti, String akhirCuti, String keterangan, String statusCuti, int avaKaryawan) {
        this.namaKaryawan = namaKaryawan;
        this.mulaiCuti = mulaiCuti;
        this.akhirCuti = akhirCuti;
        this.keterangan = keterangan;
        this.statusCuti = statusCuti;
        this.avaKaryawan = avaKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public String getMulaiCuti() {
        return mulaiCuti;
    }

    public String getAkhirCuti() {
        return akhirCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getStatusCuti() {
        return statusCuti;
    }

    public int getAvaKaryawan() {
        return avaKaryawan;
    }
}
