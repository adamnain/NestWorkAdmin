package com.sebasaku.nestworkadmin.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllUser {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("posisi")
    @Expose
    private String posisi;
    @SerializedName("noHp")
    @Expose
    private String noHp;
    @SerializedName("gaji")
    @Expose
    private int gaji;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public AllUser(String id, String email, String nama, String posisi, String noHp, int gaji, String createdAt) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.posisi = posisi;
        this.noHp = noHp;
        this.gaji = gaji;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
