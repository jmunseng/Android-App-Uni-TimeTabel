package com.example.ass2movieapp.Data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass2movieapp.Model.Movie;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHoder> {
    private Context context;
    private List<Movie> movieList;
    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
