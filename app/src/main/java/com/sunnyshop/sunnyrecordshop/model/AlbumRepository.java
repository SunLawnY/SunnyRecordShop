package com.sunnyshop.sunnyrecordshop.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.sunnyshop.sunnyrecordshop.service.AlbumApiService;
import com.sunnyshop.sunnyrecordshop.service.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {
    private static final String TAG = "AlbumRepository";
    private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Album>> getMutableLiveData() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Data received: " + response.body().toString());
                    mutableLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e(TAG, "Failure: " + t.getMessage(), t);
            }
        });

        return mutableLiveData;
    }
    
    public void addMutableLiveData(Album album) {
        
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Album> call = albumApiService.addAlbum(album);
        
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(application.getApplicationContext(), "Album added to Database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Uable to add album to DataBase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAlbum(long id, Album album) {

        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Album> call = albumApiService.updateAlbum(id, album);

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(application.getApplicationContext(), "Album updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Uable to update album to DataBase", Toast.LENGTH_SHORT).show();
                Log.e("Update Book", t.getMessage());
            }
        });
    }

    public void deleteAlbum(long id) {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Album> call = albumApiService.deleteAlbum(id);

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(), "Album deleted", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Album deleted successfully: " + response.body());
                } else {
                    // Print the detailed error message
                    try {
                        String errorResponse = response.errorBody().string();
                        Log.e(TAG, "Error deleting album: " + errorResponse);
                        Toast.makeText(application.getApplicationContext(), "Error deleting album: " + errorResponse, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e(TAG, "Error reading error response", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Unable to delete album from Database", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Failure deleting album: " + t.getMessage(), t);
            }
        });
    }

}