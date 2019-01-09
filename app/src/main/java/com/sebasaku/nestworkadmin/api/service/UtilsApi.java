package com.sebasaku.nestworkadmin.api.service;

/**
 * Created by adamnain on 3/29/18.
 */
public class UtilsApi {

    public static final String BASE_URL_API = "http://10.0.2.2:3000/";
    //public static final String BASE_URL_API = "http://nestworkapi.herokuapp.com/";

    // Mendeklarasikan Interface BaseApiService
    public static EndPoints getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(EndPoints.class);
    }

}