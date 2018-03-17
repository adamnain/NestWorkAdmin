package com.sebasaku.nestworkadmin.api.model;

/**
 * Created by adamnain on 3/15/18.
 */

public class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
