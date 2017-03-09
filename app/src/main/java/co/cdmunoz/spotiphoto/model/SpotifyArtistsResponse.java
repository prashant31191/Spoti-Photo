package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class encapsulating Spotify's artists response
 * <p>
 * User: cmunoz
 * Date: 3/6/17
 * Time: 8:39 PM
 */

public class SpotifyArtistsResponse {

  @SerializedName("artists")
  private SpotifyArtists artists;

  public SpotifyArtistsResponse() {
  }

  public SpotifyArtists getArtists() {
    return artists;
  }

  public void setArtists(SpotifyArtists artists) {
    this.artists = artists;
  }
}
