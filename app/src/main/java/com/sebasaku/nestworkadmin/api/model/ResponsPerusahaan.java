package com.sebasaku.nestworkadmin.api.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsPerusahaan {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("nama_perusahaan")
    @Expose
    public String namaPerusahaan;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("profil")
    @Expose
    public String profil;
    @SerializedName("peraturan")
    @Expose
    public String peraturan;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponsPerusahaan() {
    }

    /**
     *
     * @param id
     * @param namaPerusahaan
     * @param peraturan
     * @param email
     * @param profil
     */
    public ResponsPerusahaan(String id, String namaPerusahaan, String email, String profil, String peraturan) {
        super();
        this.id = id;
        this.namaPerusahaan = namaPerusahaan;
        this.email = email;
        this.profil = profil;
        this.peraturan = peraturan;
    }

}
