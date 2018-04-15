package com.sebasaku.nestworkadmin.api.model;

public class RiwayatCuti {
    private String awalCuti;
    private String akhirCuti;
    private String keterangan;

    public RiwayatCuti(String awalCuti, String akhirCuti, String keterangan) {
        this.awalCuti = awalCuti;
        this.akhirCuti = akhirCuti;
        this.keterangan = keterangan;
    }

    public String getAwalCuti() {
        return awalCuti;
    }

    public void setAwalCuti(String awalCuti) {
        this.awalCuti = awalCuti;
    }

    public String getAkhirCuti() {
        return akhirCuti;
    }

    public void setAkhirCuti(String akhirCuti) {
        this.akhirCuti = akhirCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
