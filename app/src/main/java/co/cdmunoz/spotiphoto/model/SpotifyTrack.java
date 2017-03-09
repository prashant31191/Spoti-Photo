package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

/**
 * This class encapsulates the data of a spotify's track
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:48 PM
 */
public class SpotifyTrack {

  @SerializedName("id")
  private String id;
  @SerializedName("name")
  private String name;
  @SerializedName("duration_ms")
  private int duration;
  @SerializedName("album")
  private SpotifyAlbum album;

  public SpotifyTrack() {
    this.album = new SpotifyAlbum();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public SpotifyAlbum getAlbum() {
    return album;
  }

  public void setAlbum(SpotifyAlbum album) {
    this.album = album;
  }
}
