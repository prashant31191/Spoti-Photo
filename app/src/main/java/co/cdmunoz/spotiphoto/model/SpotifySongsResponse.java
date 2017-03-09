package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the data of a spotify response of tracks
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 6:00 PM
 */

public class SpotifySongsResponse {

  @SerializedName("tracks")
  private List<SpotifyTrack> tracks = new ArrayList<>();

  public SpotifySongsResponse() {
  }

  public List<SpotifyTrack> getTracks() {
    return tracks;
  }

  public void setTracks(List<SpotifyTrack> tracks) {
    this.tracks = tracks;
  }
}
