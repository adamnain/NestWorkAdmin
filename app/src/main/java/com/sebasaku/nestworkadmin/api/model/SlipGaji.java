package com.sebasaku.nestworkadmin.api.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlipGaji {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jumlahTask")
    @Expose
    private int jumlahTask;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("gaji")
    @Expose
    private int gaji;
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
    public SlipGaji() {
    }

    /**
     *
     * @param updatedAt
     * @param waktu
     * @param id
     * @param v
     * @param email
     * @param status
     * @param createdAt
     * @param gaji
     * @param jumlahTask
     */
    public SlipGaji(String status, int jumlahTask, String id, String email, String waktu, int gaji, String createdAt, String updatedAt, int v) {
        super();
        this.status = status;
        this.jumlahTask = jumlahTask;
        this.id = id;
        this.email = email;
        this.waktu = waktu;
        this.gaji = gaji;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJumlahTask() {
        return jumlahTask;
    }

    public void setJumlahTask(int jumlahTask) {
        this.jumlahTask = jumlahTask;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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

