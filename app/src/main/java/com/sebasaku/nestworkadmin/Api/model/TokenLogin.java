package com.sebasaku.nestworkadmin.Api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamnain on 3/16/18.
 */

public class TokenLogin {

    @SerializedName("token")
    @Expose
    private Token token;
    @SerializedName("user")
    @Expose
    private User user;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
