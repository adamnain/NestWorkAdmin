package com.sebasaku.nestworkadmin.api.service;

import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.model.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by adamnain on 3/15/18.
 */

public interface UserClient {

    @POST("api/auth/login")
    Call<TokenLogin> login(@Body Login login);

    @GET("api/users")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);


}
