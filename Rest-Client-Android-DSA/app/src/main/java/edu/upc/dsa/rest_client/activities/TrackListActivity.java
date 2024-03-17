package edu.upc.dsa.rest_client.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackListActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private Api apiService;
    private RecyclerView recyclerViewTracks;
    private RecyclerViewAdapter adapterTracks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_list);

        // Use meaningful names for variables and initialize them
        recyclerViewTracks = findViewById(R.id.recyclerTrack);
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getInstance().getMyApi();

        // Initialize the adapter with an empty list, data will be loaded asynchronously
        adapterTracks = new RecyclerViewAdapter(new LinkedList<>(), this);
        recyclerViewTracks.setAdapter(adapterTracks);
    }

    public void deleteTrack(View view) {
        Call<Void> call = apiService.deleteTrack(view.getTag().toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

        adapterTracks.deleteTrack(view.getTag().toString());
    }

    @Override
    public void recyclerViewListClicked(int position) {
        Track track = adapterTracks.tracks.get(position);
        Intent intentTrack = new Intent(TrackListActivity.this, TrackActivity.class);
        Bundle adapterInfo = new Bundle();
        adapterInfo.putString("id", track.getId());
        adapterInfo.putString("title", track.getTitle());
        adapterInfo.putString("singer", track.getSinger());
        intentTrack.putExtras(adapterInfo);
        TrackListActivity.this.startActivity(intentTrack);
    }

    public void returnMain(View view){
        Intent intentMain = new Intent(TrackListActivity.this, MainActivity.class);
        TrackListActivity.this.startActivity(intentMain);
    }

    public void addTrack(View view) {
        Intent intentAddTrack = new Intent(TrackListActivity.this, AddTrackActivity.class);
        TrackListActivity.this.startActivity(intentAddTrack);
    }
}
