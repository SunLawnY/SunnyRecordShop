package com.sunnyshop.sunnyrecordshop.ui.updatealbum;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sunnyshop.sunnyrecordshop.R;
import com.sunnyshop.sunnyrecordshop.databinding.ActivityUpdateAlbumBinding;
import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {
    private ActivityUpdateAlbumBinding binding;
    private UpdateAlbumClickHandlers handler;
    private Album album;
    private MainActivityViewModel viewModel;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);

        album = getIntent().getParcelableExtra(ALBUM_KEY);

        binding.setAlbum(album);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        handler = new UpdateAlbumClickHandlers(this, album, viewModel);

        binding.setClickHandler(handler);

        setupGenreSpinner();
    }

    private void setupGenreSpinner() {
        Spinner genreSpinner = findViewById(R.id.spinnerGenre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedGenre = (String) parentView.getItemAtPosition(position);
                album.setGenre(selectedGenre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No action needed
            }
        });

        // Set the spinner selection to the current genre of the album
        if (album != null && album.getGenre() != null) {
            int spinnerPosition = adapter.getPosition(album.getGenre());
            genreSpinner.setSelection(spinnerPosition);
        }
    }
}
