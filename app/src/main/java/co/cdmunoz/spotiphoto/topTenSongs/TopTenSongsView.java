package co.cdmunoz.spotiphoto.topTenSongs;

import java.util.List;

import co.cdmunoz.spotiphoto.model.SpotifyTrack;

/**
 * View for top ten songs of an Spotify's artist
 * <p>
 * User: cdmunoz
 * Date: 3/7/17
 * Time: 5:45 PM
 */
public interface TopTenSongsView {

  void showProgress();

  void hideProgress();

  void showError(String message);

  void showSongs(List<SpotifyTrack> tracks);
}
