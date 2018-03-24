package com.sebasaku.nestworkadmin.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adamnain on 3/17/18.
 */

public class HttpClient {
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1:3000/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    EndPoints endPoints = retrofit.create(EndPoints.class);
}
