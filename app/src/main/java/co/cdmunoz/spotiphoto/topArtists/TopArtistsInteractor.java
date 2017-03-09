package co.cdmunoz.spotiphoto.topArtists;

import co.cdmunoz.spotiphoto.model.SpotifyArtistsResponse;
import co.cdmunoz.spotiphoto.rest.ApiClient;
import co.cdmunoz.spotiphoto.rest.ApiInterface;
import rx.Observable;

/**
 * Action class of top ten artists
 * <p>
 * User: cdmunoz
 * Date: 3/6/17
 * Time: 10:11 PM
 */

public class TopArtistsInteractor {

  public Observable<SpotifyArtistsResponse> getTopArtists(String numberTopArtists, String countryTopArtists) {
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    return apiInterface.getTopTenArtists("year:0000-9999", "artist", countryTopArtists, Integer.parseInt(numberTopArtists));
  }
}
