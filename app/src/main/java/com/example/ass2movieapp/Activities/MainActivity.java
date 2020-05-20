package com.example.ass2movieapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ass2movieapp.Data.MovieRecyclerViewAdapter;
import com.example.ass2movieapp.Model.Movie;
import com.example.ass2movieapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
private List<Movie> movies;
private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        //READ ME; KEEFU
    }

}
