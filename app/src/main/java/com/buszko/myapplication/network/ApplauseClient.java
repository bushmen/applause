package com.buszko.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplauseClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
