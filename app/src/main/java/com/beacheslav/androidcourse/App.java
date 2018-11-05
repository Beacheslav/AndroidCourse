package com.beacheslav.androidcourse;

import android.app.Application;

import com.beacheslav.androidcourse.API.QuotesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static QuotesApi sQuotesApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.tronalddump.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sQuotesApi = mRetrofit.create(QuotesApi.class);
    }

    public static QuotesApi getApi() {
        return sQuotesApi;
    }
}
