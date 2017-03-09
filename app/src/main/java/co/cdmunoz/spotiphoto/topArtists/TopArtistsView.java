package co.cdmunoz.spotiphoto.topArtists;

import java.util.List;

import co.cdmunoz.spotiphoto.model.SpotifyArtistsItems;

/**
 * Interface for top artists
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 7:51 PM
 */
public interface TopArtistsView {

  void showProgress();

  void hideProgress();

  void showError(String message);

  void showList(List<SpotifyArtistsItems> topArtists);
}
