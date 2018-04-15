package com.sebasaku.nestworkadmin.api.model;

public class RegisUser {
    String email;
    String password;
    String nama;
    String posisi;
    String tanggalLahir;
    String noHp;
    String level;
    int gaji;

    public RegisUser(String email, String password, String nama, String posisi, String tanggalLahir, String noHp, String level, int gaji) {
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.posisi = posisi;
        this.tanggalLahir = tanggalLahir;
        this.noHp = noHp;
        this.level = level;
        this.gaji = gaji;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getLevel() {
        return level;
    }

    public int getGaji() {
        return gaji;
    }
}
