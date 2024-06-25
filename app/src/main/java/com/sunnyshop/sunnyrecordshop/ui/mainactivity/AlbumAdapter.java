package com.sunnyshop.sunnyrecordshop.ui.mainactivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sunnyshop.sunnyrecordshop.R;
import com.sunnyshop.sunnyrecordshop.databinding.AlbumItemBinding;
import com.sunnyshop.sunnyrecordshop.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private Context context;

    private ArrayList<Album> albumList;

    private final RecyclerViewInterface recyclerViewInterface;


    public AlbumAdapter(Context context, ArrayList<Album> albumList, RecyclerViewInterface recyclerViewInterface) {
        this.albumList = albumList != null ? albumList : new ArrayList<>();
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AlbumItemBinding albumItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.album_item,
                parent,
                false);
        return new AlbumViewHolder(albumItemBinding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumItemBinding.setAlbum(album);
    }

    @Override
    public int getItemCount() {
        return albumList != null ? albumList.size() : 0;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final AlbumItemBinding albumItemBinding;
        private static final String TAG = "AlbumViewHolder";

        public AlbumViewHolder(AlbumItemBinding albumItemBinding, RecyclerViewInterface recyclerViewInterface) {
            super(albumItemBinding.getRoot());
            this.albumItemBinding = albumItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getBindingAdapterPosition();
                        Log.d(TAG, "Item clicked at position: " + position);

                        if(position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });

            albumItemBinding.updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getBindingAdapterPosition();
                        Log.d(TAG, "Update button clicked at position: " + position);
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position); // This should redirect to UpdateAlbumActivity
                        }
                    }
                }
            });
        }
    }
}
