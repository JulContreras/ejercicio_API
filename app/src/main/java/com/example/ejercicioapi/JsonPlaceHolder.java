package com.example.ejercicioapi;

import com.example.ejercicioapi.model.Data;
import com.example.ejercicioapi.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolder {

    @Headers("code-app:2022*01")
    @GET("api/users")
    Call<Post> getPost();

    @GET("api/users/2")
    Call<Data> getData();
}
