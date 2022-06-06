package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class Home extends AppCompatActivity {
    SongsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView songs = findViewById(R.id.songs);
        adapter = new SongsAdapter(this);

        songs.setLayoutManager(new LinearLayoutManager(this));
        songs.setHasFixedSize(false);
        songs.setAdapter(adapter);

        adapter.getAllSongs();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search for song 🎵");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filterSongs(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    adapter.getAllSongs();
                }

                return false;
            }
        });

        return true;
    }
}