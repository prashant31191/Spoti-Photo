package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

/**
 * Encapsulates the data of an Spotify's album image
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:51 PM
 */
public class SpotifyAlbumImage {
  @SerializedName("height")
  private int height;
  @SerializedName("width")
  private int width;
  @SerializedName("url")
  private String url;

  public SpotifyAlbumImage() {
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
