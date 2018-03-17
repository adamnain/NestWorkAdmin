package com.sebasaku.nestworkadmin.model;

/**
 * Created by adam on 2/27/18.
 */

public class Presensi {
    private String namaKaryawan;
    private String status;
    private String backlog;
    private String task;
    private String note;

    public Presensi(String namaKaryawan, String status, String backlog, String task, String note) {
        this.namaKaryawan = namaKaryawan;
        this.status = status;
        this.backlog = backlog;
        this.task = task;
        this.note = note;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public String getStatus() {
        return status;
    }

    public String getBacklog() {
        return backlog;
    }

    public String getTask() {
        return task;
    }

    public String getNote() {
        return note;
    }
}
