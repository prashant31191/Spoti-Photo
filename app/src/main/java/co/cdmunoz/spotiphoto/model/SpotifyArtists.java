package co.cdmunoz.spotiphoto.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class encapsulating Spotify's artists information
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 8:40 PM
 */

public class SpotifyArtists {

  @SerializedName("href")
  private String href;
  @SerializedName("items")
  private List<SpotifyArtistsItems> items = new ArrayList<>();
  @SerializedName("limit")
  private int limit;
  @SerializedName("next")
  private String next;
  @SerializedName("offset")
  private int offset;
  @SerializedName("previous")
  private String previous;
  @SerializedName("total")
  private int total;

  public SpotifyArtists() {
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public List<SpotifyArtistsItems> getItems() {
    return items;
  }

  public void setItems(List<SpotifyArtistsItems> items) {
    this.items = items;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
