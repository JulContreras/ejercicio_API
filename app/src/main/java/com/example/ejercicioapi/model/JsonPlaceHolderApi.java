package com.example.ejercicioapi.model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolderApi {

    @Headers("code-app:2022*01")
    @GET("api/users")
    Call <InfoData> getData();
}
