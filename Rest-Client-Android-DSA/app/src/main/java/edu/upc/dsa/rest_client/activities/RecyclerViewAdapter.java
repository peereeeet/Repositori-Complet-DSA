package edu.upc.dsa.rest_client.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.models.Track;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<Track> tracks;
    private static RecyclerViewClickListener itemListener;

    public RecyclerViewAdapter(List<Track> tracks, RecyclerViewClickListener itemListener) {
        this.tracks = tracks;
        RecyclerViewAdapter.itemListener = itemListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id, title, singer;
        ImageView photoTrack;
        ImageView deleteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idTrack);
            title = itemView.findViewById(R.id.titleTrack);
            singer = itemView.findViewById(R.id.singerTrack);
            photoTrack = itemView.findViewById(R.id.imgTrack);
            deleteImage = itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track currentTrack = tracks.get(position);

        holder.id.setText(currentTrack.getId());
        holder.title.setText(currentTrack.getTitle());
        holder.singer.setText(currentTrack.getSinger());
        holder.photoTrack.setImageResource(R.drawable.track);
        holder.deleteImage.setTag(currentTrack.getId());
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void deleteTrack(String id) {
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getId().equals(id)) {
                tracks.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, tracks.size());
                break; // Once the item is found and removed, exit the loop
            }
        }
    }
}

