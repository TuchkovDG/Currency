package com.example.qwert.merger;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class Retrofit {

    private static final String ENDPOINT = "http://api.fixer.io";
    private static ApiReferences apiReferences;

    static {
        initialize();
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiReferences = restAdapter.create(ApiReferences.class);
    }

    interface ApiReferences {
        @GET("/latest")
        void getCurrencies(@Query("base") String currency, Callback<Currency> callback);
    }

    public static void getCurrencies(Currency curr, Callback<Currency> callback) {
        apiReferences.getCurrencies(curr.base, callback);
    }

}
