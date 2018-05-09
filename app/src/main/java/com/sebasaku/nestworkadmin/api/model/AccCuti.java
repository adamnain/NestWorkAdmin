package com.sebasaku.nestworkadmin.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccCuti {

    @SerializedName("respons")
    @Expose
    public int respons;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("keterangan")
    @Expose
    public String keterangan;
    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("updatedAt")
    @Expose
    public String updatedAt;
    @SerializedName("__v")
    @Expose
    public int v;

}