package com.sebasaku.nestworkadmin.api.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsPerusahaan {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nama_perusahaan")
    @Expose
    private String namaPerusahaan;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profil")
    @Expose
    private String profil;
    @SerializedName("peraturan")
    @Expose
    private String peraturan;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private int v;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponsPerusahaan() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param v
     * @param namaPerusahaan
     * @param peraturan
     * @param email
     * @param createdAt
     * @param profil
     */
    public ResponsPerusahaan(String id, String namaPerusahaan, String email, String profil, String peraturan, String createdAt, String updatedAt, int v) {
        super();
        this.id = id;
        this.namaPerusahaan = namaPerusahaan;
        this.email = email;
        this.profil = profil;
        this.peraturan = peraturan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getPeraturan() {
        return peraturan;
    }

    public void setPeraturan(String peraturan) {
        this.peraturan = peraturan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

}

