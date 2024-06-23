package com.sunnyshop.sunnyrecordshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.sunnyshop.sunnyrecordshop.BR;

public class Album extends BaseObservable {
    @SerializedName("id")
    private int id;
    @SerializedName("artist")
    private String artist;
    @SerializedName("releasedYear")
    private int releasedYear;
    @SerializedName("genre")
    private String genre;
    @SerializedName("albumName")
    private String albumName;
    @SerializedName("stock")
    private int stock;

    public Album() {
    }

    public Album(int id, String artist, int releasedYear, String genre, String albumName, int stock) {
        this.id = id;
        this.artist = artist;
        this.releasedYear = releasedYear;
        this.genre = genre;
        this.albumName = albumName;
        this.stock = stock;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
        notifyPropertyChanged(BR.releasedYear);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
        notifyPropertyChanged(BR.albumName);
    }

    @Bindable
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }
}
