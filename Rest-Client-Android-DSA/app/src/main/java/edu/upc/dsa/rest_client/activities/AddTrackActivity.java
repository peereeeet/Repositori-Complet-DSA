package edu.upc.dsa.rest_client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTrackActivity extends AppCompatActivity {
    private Api apiService;
    private TextInputEditText idText;
    private TextInputEditText titleText;
    private TextInputEditText singerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        apiService = RetrofitClient.getInstance().getMyApi();

        idText = findViewById(R.id.idAdd);
        titleText = findViewById(R.id.titleAdd);
        singerText = findViewById(R.id.singerAdd);
    }

    public void addTrack(View view) {
        String id = idText.getText().toString();
        String title = titleText.getText().toString();
        String singer = singerText.getText().toString();

        if (!id.isEmpty() && !title.isEmpty() && !singer.isEmpty()) {
            Call<Track> call = apiService.createTrack(new Track(id, title, singer));
            call.enqueue(new Callback<Track>() {
                @Override
                public void onResponse(Call<Track> call, Response<Track> response) {
                    // Handle the response if needed
                }

                @Override
                public void onFailure(Call<Track> call, Throwable t) {
                    // Handle failure if needed
                }
            });

            Intent intentTracks = new Intent(AddTrackActivity.this, TrackListActivity.class);
            startActivity(intentTracks);
        } else {
            Toast.makeText(AddTrackActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void returnTracks(View view) {
        Intent intentTracks = new Intent(AddTrackActivity.this, TrackListActivity.class);
        startActivity(intentTracks);
    }
}

