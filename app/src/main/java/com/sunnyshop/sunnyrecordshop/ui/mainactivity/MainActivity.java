package com.sunnyshop.sunnyrecordshop.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunnyshop.sunnyrecordshop.R;
import com.sunnyshop.sunnyrecordshop.databinding.ActivityMainBinding;
import com.sunnyshop.sunnyrecordshop.model.Album;
import com.sunnyshop.sunnyrecordshop.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity";
    private ArrayList<Album> albums;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private MainActivityClickHandler clickHandler;
    private static final String ALBUM_KEY = "album";
    //SearchView
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        albums = new ArrayList<>();

        clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        initRecyclerView();


        //Below start the searchView
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        getAllAlbums();
    }

    private void filterList(String newText){

        ArrayList<Album> filterAlbumList = new ArrayList<>();

        for(Album album : albums){
            if (album.getAlbumName().toLowerCase().contains(newText.toLowerCase())){
                filterAlbumList.add(album);
            }
        }

        if(filterAlbumList.isEmpty()){
            Toast.makeText(MainActivity.this, "No album found", Toast.LENGTH_SHORT).show();
        } else {
            albumAdapter.setFilterList(filterAlbumList);
        }
    }






    private void initRecyclerView() {
        albumAdapter = new AlbumAdapter(this, albums, this);
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);

        intent.putExtra(ALBUM_KEY, albums.get(position));

        startActivity(intent);
    }
}