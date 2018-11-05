package com.beacheslav.androidcourse.API;

import com.beacheslav.androidcourse.POJO.TrumpQuotes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuotesApi {
    @GET("search/quote")
    Call<TrumpQuotes> getData(@Query("query") String query);
}
