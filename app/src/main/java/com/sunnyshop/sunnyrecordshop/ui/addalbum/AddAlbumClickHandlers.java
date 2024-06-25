package com.sunnyshop.sunnyrecordshop.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivity;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

    Album album;
    Context context;
    MainActivityViewModel mainActivityViewModel;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel mainActivityViewModel) {
        this.album = album;
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onSubmitBtnClicked(View view){
        if(album.getArtist() == null ||
                album.getAlbumName() == null ||
                album.getGenre() == null ||
                album.getReleasedYear() == null){
            Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);

            Album newAlbum = new Album(
                    album.getId(),
                    album.getArtist(),
                    album.getReleasedYear(),
                    album.getGenre(),
                    album.getAlbumName(),
                    album.getStock(),
                    album.getImageUrl()
            );

            mainActivityViewModel.addAlbum(newAlbum);
            context.startActivity(intent);
        }
    }

    public void onBackBtnClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
