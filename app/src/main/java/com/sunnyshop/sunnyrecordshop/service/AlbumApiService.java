package com.sunnyshop.sunnyrecordshop.service;

import com.sunnyshop.sunnyrecordshop.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {
    @GET("api/v1/albums")
    Call<List<Album>> getAllAlbums();

    @POST("api/v1/addnew")
    Call<Album> addAlbum (@Body Album album);

    @PUT("api/v1/update/{id}")
    Call<Album> updateAlbum(@Path("id") long id, @Body Album album);

    @DELETE("api/v1/delete/{id}")
    Call<Album> deleteAlbum(@Path("id") long id);
}
