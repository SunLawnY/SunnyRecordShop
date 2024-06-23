package com.sunnyshop.sunnyrecordshop.ui.mainactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sunnyshop.sunnyrecordshop.R;
import com.sunnyshop.sunnyrecordshop.databinding.ActivityMainBinding;
import com.sunnyshop.sunnyrecordshop.model.Album;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Album> albums = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        initRecyclerView();
        getAllAlbums();
    }

    private void initRecyclerView() {
        albumAdapter = new AlbumAdapter(albums, this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(albumAdapter);
        binding.recyclerView.setHasFixedSize(true);
    }

    private void getAllAlbums() {
        viewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                if (albumsFromLiveData != null) {
                    Log.d(TAG, "Data received: " + albumsFromLiveData.toString());
                    albums.clear();
                    albums.addAll(albumsFromLiveData);
                    albumAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "No data received");
                }
            }
        });
    }
}