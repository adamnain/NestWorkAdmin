package com.sebasaku.nestworkadmin.api.service;

import com.sebasaku.nestworkadmin.api.model.AccCuti;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.DecCuti;
import com.sebasaku.nestworkadmin.api.model.Present;
import com.sebasaku.nestworkadmin.api.model.RegisUser;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.model.ResponsPerusahaan;
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.model.Login;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("api/cuti/accCuti/{id}")
    Call<AccCuti> accCuti(@Header("Authorization") String authToken, @Path("id") String id);

    @PUT("api/cuti/tolakCuti/{id}")
    Call<DecCuti> decCuti(@Header("Authorization") String authToken, @Path("id") String id);

    @GET("api/present/getAllPresent")
    Call<List<Present>> getAllPresent(@Header("Authorization") String authToken);

    @PUT("api/company/companyprofil")
    Call<ResponsPerusahaan> editProfil(@Header("Authorization") String authToken, @Body ResponsPerusahaan responsPerusahaan);

    @GET("api/slipGaji/getAllSlipGaji")
    Call<List<SlipGaji>> getAllSlip(@Header("Authorization") String authToken);

    @PUT("api/slipGaji/updateSlipGaji/{id}")
    Call<List<SlipGaji>> updateSlipKaryawan(@Header("Authorization") String authToken, @Path("id") String id, @Body SlipGaji slipGaji);



}
