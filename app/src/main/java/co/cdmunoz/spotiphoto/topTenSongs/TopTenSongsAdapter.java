package co.cdmunoz.spotiphoto.topTenSongs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.spotiphoto.R;
import co.cdmunoz.spotiphoto.common.CommonImageActivity;
import co.cdmunoz.spotiphoto.model.SpotifyTrack;

/**
 * Adapter for artist's top ten songs list
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:56 PM
 */

public class TopTenSongsAdapter
        extends RecyclerView.Adapter<TopTenSongsAdapter.SpotifySongsViewHolder> {

  private Activity activity;
  private final List<SpotifyTrack> songs = new ArrayList<>();

  public TopTenSongsAdapter(Activity activity, List<SpotifyTrack> songs) {
    this.activity = activity;
    this.songs.addAll(songs);
  }

  @Override
  public SpotifySongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.top_ten_songs_cardview, parent, false);
    return new SpotifySongsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(SpotifySongsViewHolder holder, int position) {
    final SpotifyTrack spotifyTrack = songs.get(position);
    holder.spotifyTrack(activity, spotifyTrack);
  }

  @Override
  public int getItemCount() {
    return songs.size();
  }

  public class SpotifySongsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view_song_image)
    ImageView songCover;
    @BindView(R.id.card_view_song_title)
    TextView songName;
    @BindView(R.id.card_view_song_album)
    TextView songAlbum;

    public SpotifySongsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void spotifyTrack(final Activity activity, final SpotifyTrack spotifyTrack) {
      Picasso.with(songCover.getContext())
              .load(spotifyTrack.getAlbum().getImages().get(0).getUrl())
              .into(songCover);
      songName.setText(spotifyTrack.getName());
      songAlbum.setText(spotifyTrack.getAlbum().getName());

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = CommonImageActivity.getIntent(activity, spotifyTrack.getAlbum().getImages().get(0).getUrl());
          activity.startActivity(intent);
        }
      });
    }
  }
}
