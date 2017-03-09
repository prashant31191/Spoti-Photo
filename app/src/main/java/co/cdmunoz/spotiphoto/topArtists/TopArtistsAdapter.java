package co.cdmunoz.spotiphoto.topArtists;

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
import co.cdmunoz.spotiphoto.model.SpotifyArtistsItems;
import co.cdmunoz.spotiphoto.topTenSongs.TopTenSongsActivity;

/**
 * Adapter for Spotify's top artists
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 8:12 PM
 */

public class TopArtistsAdapter
        extends RecyclerView.Adapter<TopArtistsAdapter.TopArtistViewHolder> {

  private Activity activity;
  private final List<SpotifyArtistsItems> artists = new ArrayList<>();

  public TopArtistsAdapter(Activity activity, List<SpotifyArtistsItems> artists) {
    this.activity = activity;
    this.artists.addAll(artists);
  }

  @Override
  public TopArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.top_artists_cardview, parent, false);
    return new TopArtistViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(TopArtistViewHolder holder, int position) {
    final SpotifyArtistsItems artist = artists.get(position);
    holder.spotifyArtist(artist, activity);
  }

  @Override
  public int getItemCount() {
    return artists.size();
  }

  public static class TopArtistViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view_song_title)
    TextView cardTitle;
    @BindView(R.id.card_view_img)
    ImageView cardImage;

    public TopArtistViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void spotifyArtist(final SpotifyArtistsItems spotifyArtist, final Activity activity) {
      cardTitle.setText(spotifyArtist.getName());
      Picasso.with(cardImage.getContext())
              .load(spotifyArtist.getImages().get(0).getUrl())
              .into(cardImage);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = TopTenSongsActivity.getIntent(activity, spotifyArtist.getId(), spotifyArtist.getName());
          activity.startActivity(intent);
        }
      });
    }
  }
}
