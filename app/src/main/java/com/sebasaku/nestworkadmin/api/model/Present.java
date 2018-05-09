package com.sebasaku.nestworkadmin.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Present {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("backlog")
    @Expose
    private String backlog;
    @SerializedName("status_prs")
    @Expose
    private String statusPrs;
    @SerializedName("task")
    @Expose
    private String task;
    @SerializedName("note")
    @Expose
    private String note;
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
    public Present() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param v
     * @param backlog
     * @param statusPrs
     * @param email
     * @param createdAt
     * @param task
     * @param nama
     * @param note
     */
    public Present(String id, String email, String nama, String backlog, String statusPrs, String task, String note, String createdAt, String updatedAt, int v) {
        super();
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.backlog = backlog;
        this.statusPrs = statusPrs;
        this.task = task;
        this.note = note;
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

    public String getBacklog() {
        return backlog;
    }

    public void setBacklog(String backlog) {
        this.backlog = backlog;
    }

    public String getStatusPrs() {
        return statusPrs;
    }

    public void setStatusPrs(String statusPrs) {
        this.statusPrs = statusPrs;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
