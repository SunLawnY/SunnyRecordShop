package com.sunnyshop.sunnyrecordshop.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivity;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivityViewModel;

import java.util.Objects;

public class UpdateAlbumClickHandlers {
    private Context context;
    private Album album;
    private MainActivityViewModel viewModel;
    private long albumId;
    private static final String TAG = "UpdateAlbumClickHandlers";

    public UpdateAlbumClickHandlers(Context context, Album album, MainActivityViewModel viewModel) {
        this.context = context;
        this.album = album;
        this.viewModel = viewModel;
    }

    public void onBackBtnClicked(View view){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onUpdateBtnClicked(View view){
        Album updateAlbum = new Album(
                album.getId(),
                album.getArtist(),
                album.getReleasedYear(),
                album.getGenre(),
                album.getAlbumName(),
                album.getStock(),
                album.getImageUrl()
        );

        if(Objects.equals(updateAlbum.getArtist(), "") ||
                Objects.equals(updateAlbum.getReleasedYear(), "") ||
                Objects.equals(updateAlbum.getGenre(), "") ||
                Objects.equals(updateAlbum.getAlbumName(), "") ||
                Objects.equals(updateAlbum.getStock(), "")) {
            Toast.makeText(context, "cannot be empty!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);

            albumId = album.getId();

            viewModel.updateAlbum(albumId, updateAlbum);

            context.startActivity(intent);
        }
    }

    public void onDeleteBtnClicked(View view){
        Intent intent = new Intent(context, MainActivity.class);
        albumId = album.getId();
        Log.d(TAG, "Deleting album with ID: " + albumId);
        viewModel.deleteAlbum(albumId);
        Log.d(TAG, "Album deletion initiated");
        context.startActivity(intent);
    }
}
