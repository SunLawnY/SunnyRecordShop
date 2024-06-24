package com.sunnyshop.sunnyrecordshop.service;

import com.sunnyshop.sunnyrecordshop.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlbumApiService {
    @GET("api/v1/albums")
    Call<List<Album>> getAllAlbums();

    @POST("api/v1/addNew")
    Call<Album> addAlbum (@Body Album album);
}
