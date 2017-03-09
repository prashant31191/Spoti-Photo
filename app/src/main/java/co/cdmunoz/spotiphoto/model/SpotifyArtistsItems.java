package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to encapsulate Spotify's artist information
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 7:55 PM
 */
public class SpotifyArtistsItems {

  @SerializedName("id")
  private String id;
  @SerializedName("name")
  private String name;
  @SerializedName("uri")
  private String uri;
  @SerializedName("images")
  private List<SpotifyArtistImage> images = new ArrayList<>();

  public SpotifyArtistsItems() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public List<SpotifyArtistImage> getImages() {
    return images;
  }

  public void setImages(List<SpotifyArtistImage> images) {
    this.images = images;
  }

  public static class Builder {

    private SpotifyArtistsItems artist = new SpotifyArtistsItems();

    public Builder withName(String name) {
      artist.setName(name);
      return this;
    }

    public SpotifyArtistsItems build() {
      return artist;
    }
  }
}
