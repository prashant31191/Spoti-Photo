package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the data of an Spotify's album
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:10 PM
 */
public class SpotifyAlbum {

  @SerializedName("id")
  private String id;
  @SerializedName("name")
  private String name;
  @SerializedName("images")
  private List<SpotifyAlbumImage> images = new ArrayList<>();

  public SpotifyAlbum() {
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

  public List<SpotifyAlbumImage> getImages() {
    return images;
  }

  public void setImages(List<SpotifyAlbumImage> images) {
    this.images = images;
  }
}
