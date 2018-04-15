package com.sebasaku.nestworkadmin.api.service;

import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.RegisUser;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adamnain on 3/15/18.
 */

public interface EndPoints {

    @POST("api/auth/login")
    Call<TokenLogin> login(@Body Login login);

    @POST("api/auth/register")
    Call<TokenLogin> regisUser(@Body RegisUser regisUser);

    @GET("api/users")
    Call<List<AllUser>> getAllUser(@Header("Authorization") String authToken);

    @GET("api/cuti/findByRespons/{respons}")
    Call<List<ResponsCuti>> findByRespons(@Header("Authorization") String authToken, @Path("respons") int respons);


}
