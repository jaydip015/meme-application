package com.example.memeapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiCall {
    //https://meme-api.com/gimme
    @GET("gimme")
    Call<DataModel> getData();
}
