package com.sunnyshop.sunnyrecordshop.ui.mainactivity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private AlbumRepository albumRepository;

    public MainActivityViewModel(Application application) {
        super(application);
        albumRepository = new AlbumRepository(application);
    }

    public LiveData<List<Album>> getAllAlbums() {
        return albumRepository.getMutableLiveData();
    }

    public void addAlbum(Album album){
        albumRepository.addMutableLiveData(album);
    }

    public void updateAlbum(long id, Album album) {
        albumRepository.updateAlbum(id, album);
    }

    public void deleteAlbum(long id){
        albumRepository.deleteAlbum(id);
    }
}