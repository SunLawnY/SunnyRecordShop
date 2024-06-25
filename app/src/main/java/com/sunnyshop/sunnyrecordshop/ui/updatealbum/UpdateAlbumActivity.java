package com.sunnyshop.sunnyrecordshop.ui.updatealbum;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sunnyshop.sunnyrecordshop.R;
import com.sunnyshop.sunnyrecordshop.databinding.ActivityAddNewAlbumBinding;
import com.sunnyshop.sunnyrecordshop.databinding.ActivityUpdateAlbumBinding;
import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.ui.addalbum.AddAlbumClickHandlers;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {
    private ActivityUpdateAlbumBinding binding;
    private UpdateAlbumClickHandlers handler;
    private Album album;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use DataBindingUtil to set the content view with the correct layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);

        // Retrieve the album from the intent
        album = getIntent().getParcelableExtra(ALBUM_KEY);

        // Bind the album to the layout
        binding.setAlbum(album);

        // Initialize the ViewModel
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize the click handler with the album and viewModel
        handler = new UpdateAlbumClickHandlers(this, album, viewModel);

        // Set the click handler in the binding
        binding.setClickHandler(handler);
    }
}