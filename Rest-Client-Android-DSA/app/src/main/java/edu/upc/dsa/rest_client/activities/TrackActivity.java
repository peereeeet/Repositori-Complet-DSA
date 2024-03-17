package edu.upc.dsa.rest_client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackActivity extends AppCompatActivity {
    private Api apiService;

    private String id;
    private String title;
    private String singer;

    private TextInputEditText titleInput;
    private TextInputEditText singerInput;

    private TextView idTxt;
    private TextView titleTxt;
    private TextView singerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        apiService = RetrofitClient.getInstance().getMyApi();

        titleInput = findViewById(R.id.titleInput);
        singerInput = findViewById(R.id.singerInput);
        idTxt = findViewById(R.id.idTxt);
        titleTxt = findViewById(R.id.titleTxt);
        singerTxt = findViewById(R.id.singerTxt);

        Bundle content = getIntent().getExtras();
        if (content != null) {
            this.id = content.getString("id");
            this.title = content.getString("title");
            this.singer = content.getString("singer");
            updateTextViews();
        }
    }

    public void applyChanges(View view) {
        if (!titleInput.getText().toString().isEmpty()) {
            title = titleInput.getText().toString();
        }
        if (!singerInput.getText().toString().isEmpty()) {
            singer = singerInput.getText().toString();
        }

        Call<Void> call = apiService.updateTrack(new Track(id, title, singer));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle the response if needed
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure if needed
            }
        });

        updateTextViews();
    }

    private void updateTextViews() {
        idTxt.setText(this.id);
        titleTxt.setText(this.title);
        singerTxt.setText(this.singer);
    }

    public void returnTracks(View view) {
        Intent intentTracks = new Intent(TrackActivity.this, TrackListActivity.class);
        startActivity(intentTracks);
    }
}

