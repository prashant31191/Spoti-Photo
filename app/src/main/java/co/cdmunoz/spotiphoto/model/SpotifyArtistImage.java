package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class encapsulating an Spotify's image information
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 7:56 PM
 */
public class SpotifyArtistImage {

  @SerializedName("height")
  private int height;
  @SerializedName("width")
  private int width;
  @SerializedName("url")
  private String url;

  public SpotifyArtistImage() {
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
