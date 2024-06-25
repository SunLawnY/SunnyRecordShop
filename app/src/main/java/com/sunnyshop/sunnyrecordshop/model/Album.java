package com.sunnyshop.sunnyrecordshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.sunnyshop.sunnyrecordshop.BR;

public class Album extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private Long id;
    @SerializedName("artist")
    private String artist;
    @SerializedName("releasedYear")
    private Integer releasedYear;
    @SerializedName("genre")
    private String genre;
    @SerializedName("albumName")
    private String albumName;
    @SerializedName("stock")
    private Integer stock;

    public Album() {
    }

    public Album(Long id, String artist, Integer releasedYear, String genre, String albumName, Integer stock) {
        this.id = id;
        this.artist = artist;
        this.releasedYear = releasedYear;
        this.genre = genre;
        this.albumName = albumName;
        this.stock = stock;
    }

    protected Album(Parcel in){
        id = in.readLong();
        artist = in.readString();
        releasedYear = in.readInt();
        genre = in.readString();
        albumName = in.readString();
        stock = in.readInt();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Integer getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Integer releasedYear) {
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
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(artist);
        dest.writeInt(releasedYear);
        dest.writeString(genre);
        dest.writeString(albumName);
        dest.writeInt(stock);
    }
}
